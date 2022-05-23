#!/usr/bin/env bash

# Use this file to run the container locally

PORT=""
DB_URI=""
DB_NAME=""
LOCAL_PORT=""
IMAGE_VERSION=0.1.0
ENVIRONMENT=local

docker run -d --name javeke-ws -e SERVER_PORT=$PORT -e SPRING_DATA_MONGODB_URI=$DB_URI \
  -e SPRING_DATA_MONGODB_DATABASE=$DB_NAME -p $LOCAL_PORT:$PORT \
  -e SPRINGDOC_SWAGGER-UI_PATH=/docs \
  -e SPRING_PROFILES_ACTIVE=$ENVIRONMENT \
  javeke/javeke-ws:$IMAGE_VERSION