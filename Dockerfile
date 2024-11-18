# Use a specific JDK version for better stability
FROM eclipse-temurin:22-jdk-slim

# Create a non-root user for security
RUN groupadd -r spring && useradd -r -g spring spring

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar app.jar

# Set ownership to non-root user
RUN chown spring:spring /app/app.jar

# Switch to non-root user
USER spring:spring

# The port will be provided by Cloud Run via PORT env variable
EXPOSE ${PORT:-5050}

# Use CMD instead of ENTRYPOINT for better flexibility
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-5050}"]



#FROM openjdk:22-jdk-slim
#
#WORKDIR /app
#
#COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar
#
#EXPOSE 5050
#
#ENTRYPOINT ["java", "-jar", "TibaApp.jar"]