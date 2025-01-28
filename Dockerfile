# Stage 1: build
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

COPY ./pom.xml ./
COPY ./.mvn ./.mvn
COPY ./mvnw ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean install -DskipTests

# Stage 2: run
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/MedicalAPI.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]