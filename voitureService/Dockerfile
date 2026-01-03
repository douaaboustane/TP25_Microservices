FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
# Copy parent POM and install it
COPY pom.xml ./pom.xml
RUN mvn install -N -DskipTests
# Copy module POM and source
COPY voitureService/pom.xml ./voitureService/pom.xml
COPY voitureService/src ./voitureService/src
WORKDIR /app/voitureService
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=builder /app/voitureService/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
