# Pull base image
FROM tomcat:9-jdk8-corretto

#RUN yum update -y
#RUN yum update ca-certificates -y

RUN rm -rf /usr/local/tomcat/webapps/*
#RUN rm -rf /usr/local/tomcat/conf/server.xml

# Copy war and required files to image
COPY springboot-backend.war /usr/local/tomcat/webapps/ROOT.war
#COPY server.xml /usr/local/tomcat/conf/server.xml
#COPY setenv.sh /usr/local/tomcat/bin/setenv.sh
#COPY .keystore /root/
#COPY stage.properties /root/
