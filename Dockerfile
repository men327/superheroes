FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/superheroes-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]