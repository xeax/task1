FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=build/libs/task.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar","app.jar"]