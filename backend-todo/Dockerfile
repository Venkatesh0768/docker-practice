FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8081
CMD ["java", "-jar", "/app/target/backend-todo-0.0.1-SNAPSHOT.jar"]

