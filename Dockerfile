# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR (assuming it's in the target directory after building)
COPY target/NiRi-0.0.1-SNAPSHOT-jar-with-dependencies.jar .

# Specify the command to run on container startup
CMD ["java", "-jar", "NiRi-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
