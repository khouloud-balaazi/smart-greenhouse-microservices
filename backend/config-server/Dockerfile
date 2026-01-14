# Base image Java 17
FROM eclipse-temurin:17-jdk-alpine

# Copier le jar compilé
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Exposer le port du config server
EXPOSE 8888

# Lancer l'application
ENTRYPOINT ["java","-jar","/app.jar"]
