# Signing plugin slowdown

* Run `./gradlew publishToMavenLocal` to see the performance of the default signing configuration (property-based)
* Run `./gradlew publishToMavenLocal -PpublishingMode=in-memory` to see the performance of signing with the in-memory 
 key.
