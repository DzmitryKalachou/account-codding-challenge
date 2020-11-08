#!/usr/bin/env bash
set -e

#launch postgres instance in docker
docker-compose up -d

cd ../../

# 1. apply flyway migration scripts on local postgres instance
# 2. generate jooq classes based on the db schema
# 3. compile and build application jar file
./gradlew clean flywayMigrate generateAccountJooqSchemaSource build

tag=$(date|md5)
repository=com.example.account

# build docker image with application
docker build -t ${repository}:${tag} -t ${repository}:latest -f deployments/remote/docker/Dockerfile  build/libs

# apply db migrations to database that will be used by application after deployment
#./gradlew flywayMigrate -Pdatasource_url=${DATABASE_URL} -Pdatasource_username=${DATABASE_USERNAME} -Pdatasource_password=${DATABASE_PASSWORD}

#docker push ${repository}:${tag}
#docker push ${repository}:${latest}

#trigger start of deployment script to launch deployment to Kubernetes, ECS etc.


cd deployments/remote

#disable localy launched postgres
docker-compose down
