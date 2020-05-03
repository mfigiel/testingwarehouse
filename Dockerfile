FROM openjdk:8u191-jdk-alpine3.9
ADD target/warehouse-0.0.1-SNAPSHOT.jar .
EXPOSE 8089
CMD java -jar warehouse-0.0.1-SNAPSHOT.jar