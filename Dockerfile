FROM openjdk:11
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app.jar"]