# Use OpenJDK as the base image
FROM openjdk:21

# Add metadata
LABEL authors="user"

# Define the JAR_FILE argument
ARG JAR_FILE=target/*.jar

# Copy the application JAR
COPY ../../../../../../target/tiba-0.0.1-SNAPSHOT.jar tiba.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the entrypoint to run the Java application
ENTRYPOINT ["java", "-jar", "tiba.jar"]