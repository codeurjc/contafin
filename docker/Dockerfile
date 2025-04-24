FROM openjdk:17-jdk-alpine

COPY ./contafin/target/contafin-0.0.1-SNAPSHOT.jar java-app.jar

ENTRYPOINT ["java", "-jar", "java-app.jar"]