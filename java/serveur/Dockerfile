FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/serveur-1.0-jar-with-dependencies.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT java -jar server.jar