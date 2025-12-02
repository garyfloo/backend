# Etapa 1: Construcción del proyecto con Gradle
FROM gradle:7.6-jdk17 AS build

WORKDIR /app
COPY . .

RUN gradle clean build -x test

# Etapa 2: Ejecutar la app
FROM eclipse-temurin:17

WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

