https://github.com/gradle/gradle/issues/30606

Run:
```
./gradlew run
```

Observe:
```
1 problem was found storing the configuration cache.
- Gradle runtime: cannot serialize object of type 'org.gradle.groovy.scripts.internal.DefaultScriptCompilationHandler$1', a subtype of 'java.lang.ClassLoader', as these are not supported with the configuration cache.
  See https://docs.gradle.org/8.9/userguide/configuration_cache.html#config_cache:requirements:disallowed_types
```