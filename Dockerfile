FROM openjdk:latest
ARG JAR_FILE=target/spring-boot-c71-0.0.1-SNAPSHOT.jar
RUN mkdir /c71
WORKDIR /jars
COPY ${JAR_FILE} /c71
ENTRYPOINT java -jar /c71/spring-boot-c71-0.0.1-SNAPSHOT.jar


