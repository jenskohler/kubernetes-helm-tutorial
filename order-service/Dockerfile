FROM openjdk:11.0.10-jre-slim
COPY target/order-service-0.0.1-SNAPSHOT.jar .
CMD java -Xmx300m -Xms300m -jar order-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080