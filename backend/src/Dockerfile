FROM maven: 3.9.4-eclipse-temurin-21 AS builder
WORKIDR /app
COPY . .
RUN mvn clean package -DskpTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/targer/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]