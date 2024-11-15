FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "TibaApp.jar"]