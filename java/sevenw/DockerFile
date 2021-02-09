FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/sevenw-1.0-jar-with-dependencies.jar
COPY ${JAR_FILE} sevenw.jar
ENTRYPOINT java -jar sevenw.jar false 20000 3 true