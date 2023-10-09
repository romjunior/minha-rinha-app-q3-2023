#!/bin/bash



RM_IMAGE_CODE=$(docker images minha-rinha-app -q)

echo "returned image: \'$RM_IMAGE_CODE\'"

if [ -n "$RM_IMAGE_CODE" ]
then
    echo "Image not removed!"
    docker rmi minha-rinha-app
    echo "Image removed!"
fi

echo "Creating new image..."
./gradlew clean build
docker build -t minha-rinha-app .
echo "Image created!"