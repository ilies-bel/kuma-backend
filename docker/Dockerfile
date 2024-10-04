FROM amazoncorretto:23-jdk as build

WORKDIR ../

COPY . .


RUN gradle build

FROM amzoncoretto:23-alpine

COPY --from=build build/*.jar app.jar

EXPOSE 8080

ENTRYPOINT exec java app.jar
