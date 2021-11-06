FROM tomcat:8.0-jre8

ADD ./build/libs/client-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/client-service.war

EXPOSE 8080
CMD ["catalina.sh", "run"]

