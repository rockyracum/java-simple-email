FROM maven:3.8.5-openjdk-17
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY pom.xml /opt/app/
RUN mvn install
COPY src /opt/app/src
RUN mvn package
EXPOSE 8080
ENTRYPOINT ["mvn", "exec:java"]