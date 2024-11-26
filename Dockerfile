# Build stage
FROM maven:3.8-openjdk-22-slim AS builder

# Set working directory
WORKDIR /build

# Copy the POM file separately to cache dependencies
COPY pom.xml .
# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:22-jdk-slim

# Add a non-root user
RUN useradd -r -u 1001 -g root springuser

# Set working directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=builder /build/target/TibaApp-0.0.1-SNAPSHOT.jar app.jar

# Set ownership to non-root user
RUN chown springuser:root /app \
    && chmod 750 /app \
    && chown springuser:root app.jar

# Use non-root user
USER 1001

# Add health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:${PORT}/health || exit 1

# Set environment variables
ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:InitialRAMPercentage=50.0 \
               -XX:+OptimizeStringConcat \
               -XX:+UseStringDeduplication \
               -Djava.security.egd=file:/dev/./urandom \
               -Dserver.port=${PORT}"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

#FROM openjdk:22-jdk-slim
#
#WORKDIR /app
#
#COPY ./target/TibaApp-0.0.1-SNAPSHOT.jar TibaApp.jar
#
## Remove EXPOSE as it's not needed for Cloud Run
## EXPOSE 5050
#
## Add the PORT environment variable to the ENTRYPOINT
#ENTRYPOINT ["java", "-jar", "TibaApp.jar"]

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