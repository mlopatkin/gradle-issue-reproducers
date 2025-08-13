#!/bin/sh

./gradlew --write-verification-metadata pgp,sha256  --refresh-keys --export-keys help

file gradle/verification-keyring.keys gradle/verification-metadata.xml
