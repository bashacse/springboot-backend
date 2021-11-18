FROM openjdk:8u111-jdk-alpine  
COPY target/springboot-crud-example.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]