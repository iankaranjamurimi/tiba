# Build stage with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy the POM file and source code
COPY pom.xml ./
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Runtime stage
java -jar target/tiba-0.0.1-SNAPSHOT.jarFROM openjdk:21

LABEL authors="user"

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/target/tiba-0.0.1-SNAPSHOT.jar tiba.jar

EXPOSE 8080

ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-jar", \
            "tiba.jar"]