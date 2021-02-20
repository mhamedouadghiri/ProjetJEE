# build env
FROM maven:3.6.3-jdk-8-slim as build

ARG DB_IP=172.20.0.30:3306

COPY pom.xml /tmp/
COPY src /tmp/src/

RUN sed -i "s/localhost:3306/${DB_IP}/" /tmp/src/main/resources/application.properties

WORKDIR /tmp

RUN mvn package -DskipTests

# custom glassfish
FROM openjdk:8-jre-alpine

RUN apk add --update bash expect && rm -rf /var/cache/apk/* && \
    mkdir -p /opt/app/bin && mkdir -p /opt/app/deploy && \
    wget https://mirrors.xmission.com/eclipse/glassfish/glassfish-5.1.0.zip && \
    unzip glassfish-5.1.0.zip -d /opt && \
    rm glassfish-5.1.0.zip

ENV PATH /opt/glassfish5/bin:/opt/app/bin:$PATH

COPY start-glass.sh /opt/app/bin/start-glass.sh

RUN chmod +x /opt/app/bin/*.sh

# prod env
COPY --from=build /tmp/target/ProjetJEE-1.0-SNAPSHOT.war /opt/app/deploy

EXPOSE 8080

ENTRYPOINT ["/opt/app/bin/start-glass.sh"]

