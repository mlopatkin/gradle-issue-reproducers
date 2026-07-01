# Secure dependency resolution testbed

A testbed for resolving Gradle dependencies from a **protected Maven repository**
(GitHub Packages). It contains two independent Gradle builds sitting side by side:

| Build | Coordinates | Role |
|-------|-------------|------|
| [`foo/`](foo/) | `com.example:foo:1.0.0` | Stub `java-library`. `Foo.hello()` prints a message. Published to GitHub Packages. |
| [`app/`](app/) | `com.example:app` | `application` that calls `Foo.hello()` from `main`. Consumes `foo` from GitHub Packages. |

Neither build includes the other in its `settings.gradle.kts`. The two are wired
together only on demand, via the command-line `--include-build` flag (see below).

The protected repository is
`https://maven.pkg.github.com/mlopatkin/gradle-issue-reproducers`.

## Prerequisites

GitHub Packages requires authentication **even to read**, so a token is needed for
both publishing and consuming.

1. Create a **classic** Personal Access Token at
   <https://github.com/settings/tokens> with scopes:
   - `write:packages` — to publish `foo`
   - `read:packages` — to consume from `app`
2. Add the credentials to `~/.gradle/gradle.properties`:
   ```properties
   GitHubPackagesUsername=<your-github-username>
   GitHubPackagesPassword=<ghp_your_token>
   ```

Both builds read these two properties via Gradle's `PasswordCredentials`
convention. Credentials are looked up **lazily** (`providers.gradleProperty(...).orNull`),
so they are only required when the protected repo is actually contacted over the
network — the local `--include-build` path works with no credentials at all.

## The two resolution modes

Run from `app/`:

| Command | What it exercises |
|---------|-------------------|
| `./gradlew run` | Resolves `com.example:foo:1.0.0` from the **protected GitHub Packages repo** (uses your token). This is the path under test. |
| `./gradlew run --include-build ../foo` | Substitutes the **local `foo` source build** for the dependency — no network, no credentials. The fast dev loop. |

Expected output either way:

```
Hello from com.example:foo!
```

## Publishing `foo`

From `foo/`:

```bash
./gradlew publish
```

To publish a distinct version, bump `version` in [`foo/build.gradle.kts`](foo/build.gradle.kts)
and update the dependency version in [`app/build.gradle.kts`](app/build.gradle.kts).

## Exercising failure modes

- **Force re-resolution** (bypass caches): add `--refresh-dependencies`.
- **See where `foo` resolves from**:
  ```bash
  ./gradlew dependencyInsight --configuration runtimeClasspath --dependency com.example:foo
  ```
- **Simulate missing / bad credentials**: override per run without touching
  `gradle.properties`, e.g. `./gradlew run -PGitHubPackagesPassword=wrong` — the
  repo returns `401` and resolution fails. The `--include-build` path keeps working
  regardless, since it never touches the repo.
