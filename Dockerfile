FROM openjdk:11
COPY target/url-shortner-*.jar url-shortner-service-app.jar

EXPOSE 7979
#operating system environment variable “egd” setting is to speed up Tomcat startup by giving it a faster source of entropy for session keys
ENTRYPOINT exec java $JVM_OPTS -Djava.security.egd=file:/dev/./urandom -jar url-shortner-service-app.jar