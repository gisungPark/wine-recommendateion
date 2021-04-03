FROM rtfpessoa/ubuntu-jdk8
ADD build/libs/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080

#FROM adoptopenjdk:8-jdk-hotspot AS builder
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#RUN chmod +x ./gradlew
#RUN ./gradlew bootJar

#FROM adoptopenjdk:8-jdk-hotspot
#COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]