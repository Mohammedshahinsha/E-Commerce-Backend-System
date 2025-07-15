# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built JAR file into container
COPY target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
