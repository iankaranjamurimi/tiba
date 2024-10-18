FROM ubuntu:latest
LABEL authors="user"

#FROM openjdk:21

ARG JAR_FILE=target/*.jar

COPY ./target/tiba-0.0.1-SNAPSHOT.jar tiba.jar

#ENTRYPOINT ["java", "-jar", "tiba.jar"]

EXPOSE 8080

ENTRYPOINT ["top", "-b"]