import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform") version "1.9.0"
    id("maven-publish")
    id("signing")
}

kotlin {
    jvm()
    js { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    watchosSimulatorArm64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
    macosX64()
    macosArm64()
    linuxX64()
    linuxArm64()
}

repositories {
    mavenCentral()
}

group = "com.example"
version = "0.0.1"

val publishingMode = providers.gradleProperty("publishingMode").orElse("default")
val keyPassword = providers.gradleProperty("signing.password")
val signingKey = providers.fileContents(layout.projectDirectory.file("gradle/secret.key"))

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Example")
            description.set("Example")
        }
    }

    signing {
        when (publishingMode.get()) {
            "in-memory" -> {
                useInMemoryPgpKeys(signingKey.asText.get(), keyPassword.get())
            }
            "default" -> {
                // Do nothing
            }
        }

        sign(publications)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}
