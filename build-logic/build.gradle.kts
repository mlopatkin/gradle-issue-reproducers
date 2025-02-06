plugins {
    `java-gradle-plugin`
    kotlin("jvm") version (System.getenv("KOTLIN_VERSION") ?: "2.0.21") apply false
}

repositories {
    mavenCentral()
}

val numProjects = System.getenv("NUM_PROJECTS")?.toInt() ?: 10

dependencies {
    for (i in 1..numProjects) {
        implementation(project(":classpath$i"))
    }
}

gradlePlugin {
    plugins {
        create("examplePlugin") {
            id = "org.example.example-plugin"
            implementationClass = "org.example.ExamplePlugin"
        }
    }
}

tasks.register("sanityCheck") {
    for (i in 1..numProjects) {
        dependsOn(":classpath$i:sanityCheck")
    }
}