FROM eclipse-temurin:17-jre-alpine
RUN mkdir /opt/app
COPY ./build/libs/minha-rinha-app-0.0.1.jar /opt/app
RUN echo $(ls /opt/app)
CMD ["java", "-jar", "/opt/app/minha-rinha-app-0.0.1.jar"]