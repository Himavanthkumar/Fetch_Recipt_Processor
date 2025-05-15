FROM openjdk:23
WORKDIR /app
COPY target/receipt_processor-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]