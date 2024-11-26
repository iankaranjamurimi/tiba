FROM openjdk:22-jdk-slim

WORKDIR /app

COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar

# Remove EXPOSE as it's not needed for Cloud Run
# EXPOSE 5050

# Add the PORT environment variable to the ENTRYPOINT
ENTRYPOINT ["java", "-jar", "TibaApp.jar"]

#
#FROM openjdk:22-jdk-slim
#
#WORKDIR /app
#
#COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar
#
#EXPOSE 5050
#
#ENTRYPOINT ["java", "-jar", "TibaApp.jar"]