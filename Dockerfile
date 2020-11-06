FROM openjdk:11

# Copy the Jar created from target and create application Jar with proper name 
COPY target/url-shortner-*.jar url-shortner-service-app.jar

# Port to be exposed for Application access
EXPOSE 7979

# Operating system environment variable “egd” setting 
# To speed up Tomcat startup by giving it a faster source of entropy for session keys
ENTRYPOINT exec java $JVM_OPTS -Djava.security.egd=file:/dev/./urandom -jar url-shortner-service-app.jar