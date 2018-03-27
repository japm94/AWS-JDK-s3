FROM openjdk:8-jre-alpine

MAINTAINER Julio Melo <julio.alexandre.melo@accenture.com>

ADD "target/jpbi-exporter-jar-with-dependencies.jar" "app/jpbi-exporter-jar-with-dependencies.jar"
ADD "Hello.txt" "Hello.txt"

ENTRYPOINT ["java", "-jar", "app/jpbi-exporter-jar-with-dependencies.jar"]
