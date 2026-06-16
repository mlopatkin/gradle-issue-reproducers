import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "2.3.21"
    application
}

val testJavaVersion: String = (findProperty("testJavaVersion") as String?) ?: "25"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(testJavaVersion.toInt())
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
        freeCompilerArgs.add("-Xjdk-release=17")
    }
}

application {
    mainClass.set("example.MainKt")
}

tasks.named<JavaExec>("run") {
    isIgnoreExitValue = true
}
