FROM openjdk:14
ARG JAR_FILE
COPY build/libs/alpha-01.jar alpha-01.jar
RUN bash -c "touch /alpha-01.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "alpha-01.jar"]