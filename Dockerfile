FROM openjdk:8-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} javeke.jar

ENTRYPOINT ["java", "-jar", "javeke.jar"]