# ==============================================================================
# Multi-Stage Production Dockerfile for BakchodBrain Trivia Engine
# ==============================================================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# ==============================================================================
# Minimal JRE 21 Runtime
# ==============================================================================
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="Akshat Jaiswal <ialexjx>"
LABEL description="BakchodBrain — Savage Infinite Roast Quiz Platform"

WORKDIR /app

RUN addgroup -S quizgroup && adduser -S quizuser -G quizgroup

COPY --from=builder /build/target/*.jar app.jar
RUN chown -R quizuser:quizgroup /app

USER quizuser

ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT ["sh", "-c", "java -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom -jar app.jar --server.port=${PORT}"]
