FROM openjdk:11-jdk-slim
RUN mkdir -p /opt/jokr/statsServer
RUN mkdir -p /opt/jokr/statsServer/serveur/output/partie_unique
RUN mkdir -p /opt/jokr/statsServer/serveur/output/partie_multiple
ARG JAR_FILE=/target/serveur-1.0-jar-with-dependencies.jar
COPY ${JAR_FILE} /opt/jokr/statsServer/server.jar
ENTRYPOINT java -jar /opt/jokr/statsServer/server.jar