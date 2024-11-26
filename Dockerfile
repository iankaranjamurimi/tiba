
FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "TibaApp.jar"]