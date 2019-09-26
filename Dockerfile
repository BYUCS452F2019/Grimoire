FROM registry.gitlab.com/byuhbll/apps/openj9-jdk11

ARG JAR_FILE=target/grimoire.jar
COPY ${JAR_FILE} /srv/app.jar
