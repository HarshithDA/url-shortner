FROM openjdk:11
COPY target/url-shortner-*.jar url-shortner-service-app.jar

EXPOSE 7979

# Comment this line if you are testing locally
ENTRYPOINT exec java $JVM_OPTS -Djava.security.egd=file:/dev/./urandom -jar url-shortner-service-app.jar