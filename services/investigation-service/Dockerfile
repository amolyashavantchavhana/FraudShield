# Build the JAR first:  mvn -pl services/investigation-service -am clean package
FROM eclipse-temurin:21-jre
WORKDIR /app
RUN addgroup --system app && adduser --system --ingroup app app
COPY target/investigation-service-*.jar app.jar
USER app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
