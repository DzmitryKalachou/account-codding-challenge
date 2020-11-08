FROM openjdk:8-jre

ADD account*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar
