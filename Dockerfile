FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Usa el wrapper de Gradle
RUN ./gradlew buildFatJar --no-daemon

FROM openjdk:17
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/lux.jar
ENTRYPOINT ["java", "-jar", "/app/lux.jar"]