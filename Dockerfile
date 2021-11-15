FROM openjdk:14-alpine

ADD target/Geometry.jar Geometry.jar

ENTRYPOINT ["java","-jar","Geometry.jar" ]

EXPOSE 8080