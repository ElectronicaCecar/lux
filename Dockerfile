Gradle FatJar-Task:
task fatJar(type: Jar) {
    manifest {
        attributes 'Main-Class': mainClassName
    }
    from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }
    with jar
}

Dockerfile:
FROM openjdk:11-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update
RUN apt-get install -y dos2unix
RUN dos2unix gradlew

RUN bash gradlew fatJar

WORKDIR /run
RUN cp /src/build/libs/*.jar /run/server.jar

EXPOSE 8081

CMD java -jar /run/server.jar