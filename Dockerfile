# ---------- Build Stage ----------
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set workdir to keep it clean
WORKDIR /app

# Copy all source files
COPY . .

# Build the project and skip tests for faster deployment
RUN mvn clean package -DskipTests


# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk-jammy

# Set working directory (optional but recommended)
WORKDIR /app

# Copy built JAR file from build stage
COPY --from=build /app/target/SpringStarter-0.0.1-SNAPSHOT.jar app.jar

# Expose the Spring Boot default port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
