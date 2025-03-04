pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
    }
}
val numProjects = System.getenv("NUM_PROJECTS")?.toInt() ?: 10

for (i in 1..numProjects) {
    include(createProject(i))
}

fun createProject(index: Int): String {
    val projectName = "classpath$index"
    val projectDir = file(projectName)
    projectDir.mkdirs()

    File(projectDir, "build.gradle.kts").writeText("""
        plugins {
             kotlin("jvm")
        }

        repositories {
            mavenCentral()
            maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        }

        group = "org.example"
        version = "1.0"

        tasks.compileTestKotlin {
            onlyIf { false }  // We don't need to actually run this task, just serialize its state into CC
        }
        
        tasks.register("sanityCheck") {
            dependsOn(tasks.compileTestKotlin)
        }
    """.trimIndent())
    File(projectDir, "src/main/kotlin/org/example/classpath$index").also { srcDir ->
        srcDir.mkdirs()
        File(srcDir, "Classpath$index.kt").writeText("""
            package com.example.classpath$index
            class Classpath$index
        """.trimIndent())
    }
    File(projectDir, "src/test/kotlin/org/example/classpath$index").also { srcDir ->
        srcDir.mkdirs()
        File(srcDir, "Classpath$index.kt").writeText("""
            package com.example.classpath$index
            class Classpath${index}Test
            """.trimIndent())
    }

    return projectName
}