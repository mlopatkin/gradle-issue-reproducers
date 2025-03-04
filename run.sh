#!/bin/sh

# Default values
KOTLIN_VERSION=${1:-2.0.21}
NUM_PROJECTS=${2:-64}
GRADLE_BIN=${GRADLE_BIN:-./gradlew}

echo "Running with NUM_PROJECTS=$NUM_PROJECTS and KOTLIN_VERSION=$KOTLIN_VERSION"

while rm -rf .gradle && $GRADLE_BIN --stop && NUM_PROJECTS=$NUM_PROJECTS KOTLIN_VERSION=$KOTLIN_VERSION $GRADLE_BIN --dry-run --rerun-tasks --stacktrace sanityCheck; do
  echo "sanityCheck passed, repeating..."
done

echo "sanityCheck failed, stopping."

