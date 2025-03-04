package com.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;

public abstract class MyTask extends DefaultTask {
    @Internal
    abstract Property<String> getValue();

    @TaskAction
    public void action() {
        System.out.println("value = " + getValue().getOrNull());
    }
}
