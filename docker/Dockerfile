FROM openjdk:8u121-jdk-alpine

COPY ./contafin-0.0.1-SNAPSHOT.jar /home/contafin-0.0.1-SNAPSHOT.jar
COPY ./img home/img
COPY ./wait-for /home
WORKDIR /home


ENTRYPOINT ["java", "-jar", "contafin-0.0.1-SNAPSHOT.jar"]