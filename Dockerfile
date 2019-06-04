#
# Step 1: Build the app within a Docker container
#
FROM gradle:5.4.1-jdk11 AS builder
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle ./ ./
RUN gradle build --info

#
# Step 2: Copy the jar to a new container to minimize the the size
#
FROM adoptopenjdk:11.0.3_7-jre-hotspot
WORKDIR /app
COPY --from=builder /home/gradle/project/build/libs/*.jar streaming-sample.jar
EXPOSE 9080
CMD java  -XX:+UseContainerSupport -noverify ${JAVA_OPTS} -jar streaming-sample.jar

