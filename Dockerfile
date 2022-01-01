FROM openjdk:17
COPY /target/SPRING-1.0.jar SPRING-1.0.jar
WORKDIR .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "SPRING-1.0.jar"]