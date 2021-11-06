FROM tomcat:8.0-alpine

ADD ./build/sample.war /usr/local/tomcat/webapps/

EXPOSE 8282

CMD [“catalina.sh”, “run”]
