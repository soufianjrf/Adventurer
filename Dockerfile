FROM gradle:latest AS builder

# Set the working directory
WORKDIR /app
# Copy only the Gradle files
COPY app/build.gradle settings.gradle ./

# Copy the source code
COPY app/src/ src/
COPY app/carte.txt .

# Build the application
RUN gradle build

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
# ARG JAR_FILE=app/build/libs/*.jar

# RUN gradlew build
# COPY ${JAR_FILE} app.jar
COPY --from=builder app/carte.txt .

ENTRYPOINT ["java","-jar","app.jar"]