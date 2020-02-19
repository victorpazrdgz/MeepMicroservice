FROM openjdk:8
# Add Maintainer Info
LABEL maintainer="victorpazrdgz@gmail.com"
EXPOSE 8080

RUN apt-get update && apt-get install -y maven
COPY . /meep
RUN  cd /meep && mvn package

#run the spring boot application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dblabla", "-jar","/meep/target/meep-0.0.1-SNAPSHOT.jar"]