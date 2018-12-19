FROM openjdk:8
EXPOSE 8080
COPY ./build/libs/SpringBootApplication.jar SpringBootApplication.jar
ENTRYPOINT ["sh", "-c", "java -jar SpringBootApplication.jar"]
