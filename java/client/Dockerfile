FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/client-1.0-exec.jar
COPY ${JAR_FILE} client.jar
ENTRYPOINT java -jar client.jar autoconnect $ENGINE_IP ${NAMES[$((RANDOM % ${#NAMES[*]}))]}
#ENTRYPOINT java -jar client.jar autoconnect $ENGINE_IP $NAME