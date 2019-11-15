# Start with a base image containing Java runtime:
FROM openjdk:8-jdk-alpine

# Add Maintainer info:
MAINTAINER stchen1012 <stchen1012@gmail.com>

# Add a volume pointing to /tmp:
VOLUME /tmp

# Make port 8082 available to the world outside this container:
EXPOSE 8082

# The application's JAR file:
ARG JAR_FILE=target/post-api-0.0.1-SNAPSHOT.jar

# Add the application's JAR to the container:
ADD ${JAR_FILE} post-api.jar

# Run the jar file:
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/post-api.jar"]
