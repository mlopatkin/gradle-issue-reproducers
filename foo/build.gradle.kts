plugins {
    `java-library`
    `maven-publish`
}

// Publishes as com.example:foo:1.0.0
group = "com.example"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            // The repo name "GitHubPackages" makes Gradle look up credentials from
            // the properties GitHubPackagesUsername / GitHubPackagesPassword
            // (or env ORG_GRADLE_PROJECT_GitHubPackagesUsername / ...Password).
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mlopatkin/gradle-issue-reproducers")
            credentials {
                username = providers.gradleProperty("GitHubPackagesUsername").orNull
                password = providers.gradleProperty("GitHubPackagesPassword").orNull
            }
        }
    }
}
