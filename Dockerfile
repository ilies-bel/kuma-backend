# Stage 1: Build the application
FROM gradle:8.5-jdk21 AS builder

# Set working directory
WORKDIR /app

# Copy gradle files first to cache dependencies
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle

# Download dependencies
RUN gradle dependencies --no-daemon --quiet

# Copy source code
COPY src src

# Build the application
RUN gradle build --no-daemon -x test

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport \
               -XX:MaxRAMPercentage=75.0 \
               -XX:InitialRAMPercentage=50.0 \
               -Djava.security.egd=file:/dev/./urandom \
               -Dfile.encoding=UTF-8"

# Start the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]