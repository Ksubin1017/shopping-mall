FROM openjdk:17-jdk

# jar 복사
COPY build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=application.yml"]