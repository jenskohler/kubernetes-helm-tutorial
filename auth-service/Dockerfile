FROM openjdk:11.0.10-jre-slim
COPY target/auth-service-0.0.1-SNAPSHOT.jar .
CMD java -Xmx300m -Xms300m -jar auth-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080