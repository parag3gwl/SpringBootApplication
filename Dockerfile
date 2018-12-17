FROM docker.target.com/tap/alpine-jre
EXPOSE 8080
COPY ./build/libs/SpringBootApplication.jar SpringBootApplication.jar
ENTRYPOINT ["sh", "-c", "java -jar SpringBootApplication.jar"]