FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY ./build/libs/SpringBootApplication.jar SpringBootApplication.jar
ENTRYPOINT ["sh", "-c", "java -jar SpringBootApplication.jar"]
