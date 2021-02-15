FROM openjdk:11-jdk-slim
RUN mkdir -p /opt/jokr/statsServer
WORKDIR /opt/jokr/statsServer
RUN mkdir -p serveur/output/partie_unique
RUN mkdir -p serveur/output/partie_multiple
ARG JAR_FILE=/target/serveur-1.0-jar-with-dependencies.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT java -jar server.jar