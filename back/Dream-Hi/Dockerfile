FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod", "-Dspring.config.location=classpath:/application.yml,/secrets/application-dbconfig.yml,/secrets/application-jwt.yml,/secrets/application-security.yml","-jar","/app.jar"]

