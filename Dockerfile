FROM openjdk:14-alpine

ADD target/Geometry.jar Geometry.jar
ADD src/main/resources/db db

ENTRYPOINT ["java","-jar","Geometry.jar" ]

EXPOSE 8080