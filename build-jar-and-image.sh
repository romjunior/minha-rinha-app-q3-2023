#!/bin/bash

./gradlew clean build
docker build -t minha-rinha-app .