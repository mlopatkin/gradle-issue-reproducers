package com.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.TaskAction;

public abstract class FileTask extends DefaultTask {
    @Internal
    public abstract RegularFileProperty getFile();

    @TaskAction
    public void action() {
        System.out.println("file = " + getFile().getOrNull());
    }
}
