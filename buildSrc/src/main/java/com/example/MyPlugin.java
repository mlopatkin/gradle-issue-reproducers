package com.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project p) {
        var environment = p.getProviders().systemProperty("some.property").orElse("/NA");
        p.getTasks().register("run", MyTask.class, task -> {
            var value = task.getValue();
            value.set(environment.map(v -> v + System.identityHashCode(value)));
        });

        p.getTasks().register("runFile", FileTask.class, task -> {
            var value = task.getFile();
            value.fileProvider(environment.map(File::new));
        });
    }
}
