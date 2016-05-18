#!/bin/bash

docker stop jenkins java mysql mysqlprod tomcat
docker rm jenkins java mysql mysqlprod tomcat
