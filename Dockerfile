FROM gradle:8.2.1-jdk17 AS build

WORKDIR /home/gradle/project
COPY --chown=gradle:gradle gradlew .
COPY --chown=gradle:gradle gradle gradle
COPY --chown=gradle:gradle build.gradle .
COPY --chown=gradle:gradle settings.gradle* .

COPY --chown=gradle:gradle src src

RUN ./gradlew build -x test

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

