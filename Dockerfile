FROM gradle:jdk17-alpine AS gradle
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 as runtime
WORKDIR /app
ENV PORT 8080
ENV SPRING_PROFILE production
COPY --from=gradle /app/build/libs/*.jar /app/app.jar
RUN chown -R 1000:1000 /app
USER 1000:1000
ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}","-Dspring.profiles.active=${SPRING_PROFILE}","app.jar"]
