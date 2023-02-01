FROM openjdk:17-alpine
VOLUME /tmp
COPY build/libs/useditem-0.0.1-SNAPSHOT.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker", "api.jar"]