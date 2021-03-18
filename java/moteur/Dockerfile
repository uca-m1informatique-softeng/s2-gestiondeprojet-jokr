FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/moteur-1.0.jar
COPY ${JAR_FILE} moteur.jar
ENTRYPOINT java -jar moteur.jar false 3 3 true $SERVER_IP