FROM openjdk:11
RUN mkdir -p deploy
WORKDIR /deploy
COPY ./build/libs/api-1.0.0.jar api.jar
ENTRYPOINT ["java", "-jar", "/deploy/api.jar", "--server.port=8081"]