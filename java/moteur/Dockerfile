FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/moteur-1.0.jar
COPY ${JAR_FILE} moteur.jar
ENTRYPOINT java -jar moteur.jar true 1 3 false $SERVER_IP