FROM openjdk:8-jdk-alpine

# Working directory for docker image
WORKDIR /app

# Folder inside alpine
COPY ./docker/contafin-0.0.1-SNAPSHOT.jar app.jar

# Expose PORT
EXPOSE 8080

# Start image
ENTRYPOINT ["java", "-jar", "app.jar"]