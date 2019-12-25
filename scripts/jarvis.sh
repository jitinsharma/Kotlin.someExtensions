#!/usr/bin/env bash

echo "Building Jarvis Jar"
./gradlew :jarvis:clean
./gradlew :jarvis:jar

echo "Running Jarvis"
java -jar jarvis/build/libs/jarvis-1.0-SNAPSHOT.jar