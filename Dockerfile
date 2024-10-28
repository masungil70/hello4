FROM eclipse-temurin:17-jdk-jammy
COPY build/libs/hello4*.war app.war
EXPOSE 8090
ENTRYPOINT [ "java", "-jar", "-Dserver.port=8090", "app.war" ]
