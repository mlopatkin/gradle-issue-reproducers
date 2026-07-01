plugins {
    application
}

repositories {
    // The protected repo we are testing dependency resolution against.
    // Credentials are read lazily from GitHubPackagesUsername / GitHubPackagesPassword
    // and are only required when this repo is actually contacted.
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/mlopatkin/gradle-issue-reproducers")
        credentials {
            username = providers.gradleProperty("GitHubPackagesUsername").orNull
            password = providers.gradleProperty("GitHubPackagesPassword").orNull
        }
    }
}

dependencies {
    // Resolved from GitHub Packages, OR substituted by the local ../foo build
    // when running with:  ./gradlew run --include-build ../foo
    implementation("com.example:foo:1.0.0")
}

application {
    mainClass = "com.example.app.App"
}
