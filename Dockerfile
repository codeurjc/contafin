FROM openjdk:17-jdk-alpine

COPY ./contafin/target/contafin-0.0.1-SNAPSHOT.jar java-app.jar
COPY ./contafin/img /img
ENTRYPOINT ["java", "-jar", "java-app.jar"]

EXPOSE 8080