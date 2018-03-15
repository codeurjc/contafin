FROM openjdk:8u121-jdk-alpine

COPY ./script.sh /home/script.sh
COPY ./contafin-0.0.1-SNAPSHOT.JAR /home/contafin-0.0.1-SNAPSHOT.jar
COPY ./img /home/img

EXPOSE 8080

CMD ["sh", "/home/script.sh"]