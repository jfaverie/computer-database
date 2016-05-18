FROM mysql:5.6

ENV MYSQL_DATABASE computer-database-db
ENV MYSQL_ROOT_PASSWORD root

COPY SCHEMA.sql /docker-entrypoint-initdb.d/SCHEMA.sql
COPY PRIVILEGE.sql /docker-entrypoint-initdb.d/PRIVILEGE.sql
COPY insertCompany.sql /docker-entrypoint-initdb.d/insertCompany.sql
COPY insertComputer.sql /docker-entrypoint-initdb.d/insertComputer.sql
