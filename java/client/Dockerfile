FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/client-1.0.jar
COPY ${JAR_FILE} client.jar
CMD echo ${NAMES[$RANDOM % ${#NAMES[@]}+1]}
ENTRYPOINT java -jar client.jar autoconnect $ENGINE_IP ${NAMES[$RANDOM % ${#NAMES[@]} + 1]}