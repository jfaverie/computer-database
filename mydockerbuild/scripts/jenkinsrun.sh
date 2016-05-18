#!/bin/bash

docker build -t jfaverie/jenkins /home/excilys/workspace/computer-database/mydockerbuild/jenkins
docker build -t jfaverie/tomcat /home/excilys/workspace/computer-database/mydockerbuild/tomcat
docker build -t jfaverie/mysqlprod /home/excilys/workspace/computer-database/mydockerbuild/mysqlprod
docker build -t jfaverie/mysql /home/excilys/workspace/computer-database/mydockerbuild/mysql
docker build -t jfaverie/java /home/excilys/workspace/computer-database/mydockerbuild/java

docker network create --driver bridge network1
docker network create --driver bridge network2

docker run -dti --name java --net network1 jfaverie/java
docker run -dti --name mysql --net network1 jfaverie/mysql

docker run -dti --name mysqlprod --net network2 jfaverie/mysqlprod
docker run -dti --name tomcat --net network2 jfaverie/tomcat

docker run -v /var/run/docker.sock:/var/run/docker.sock -v /home/excilys/jenkins:/var/jenkins_home --name jenkins -p 8080:8080 -dti jfaverie/jenkins
