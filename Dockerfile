FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} atm-challenge-1.0.jar
ENTRYPOINT ["java","-jar","/atm-challenge-1.0.jar"]