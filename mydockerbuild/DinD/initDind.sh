#!/bin/bash

docker network create --driver bridge network2
docker pull jfaverie/mysql
docker pull jfaverie/java
docker run -dt --name java --net network2 jfaverie/java
docker run -dt --name mysql --net network2 jfaverie/mysql
docker exec java mvn -f /cdb/ clean install 
