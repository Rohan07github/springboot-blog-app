# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS build
# Copy 
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-jammy

COPY --from=build /target/SpringStarter-0.0.1-SNAPSHOT.jar /spring-starter.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-starter.jar"]