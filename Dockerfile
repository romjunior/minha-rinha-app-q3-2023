FROM eclipse-temurin:17-jre-alpine
RUN mkdir /opt/app
RUN apk update; apk add curl
COPY ./build/libs/minha-rinha-app-0.0.1.jar /opt/app
CMD ["java", "-jar", "/opt/app/minha-rinha-app-0.0.1.jar"]