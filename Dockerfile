FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./target/tiba-0.0.1-SNAPSHOT.jar tiba.jar

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "tiba.jar"]