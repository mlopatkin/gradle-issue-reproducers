plugins {
    id("org.example.example-plugin")
}

tasks.register("sanityCheck") {
    gradle.includedBuilds.forEach { includedBuild ->
        dependsOn(includedBuild.task(":sanityCheck"))
    }
}