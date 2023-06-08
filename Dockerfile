FROM openjdk:17-alpine
WORKDIR /tmp
ADD target/ToxicDating-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","app.jar"]