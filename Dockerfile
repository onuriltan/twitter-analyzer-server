FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/twitteranalyzer-core-0.0.1-SNAPSHOT.jar target/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","target/app.jar"]
EXPOSE 8080
