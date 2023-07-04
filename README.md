Fails with `java.lang.NoClassDefFoundError: com/gradle/scan/plugin/BuildScanExtension`.

Reproduce failure with:
```
./gradlew -I init.gradle -Pkotlin.build.report.output=BUILD_SCAN compileKotlin  --rerun-tasks -S --scan
```

Or
```
./gradlew -I init.gradle -Pkotlin.build.report.output=BUILD_SCAN compileKotlin --configuration-cache  --rerun-tasks  -S --scan
```
