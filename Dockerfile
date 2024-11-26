
FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar

#EXPOSE 5050
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "TibaApp.jar"]