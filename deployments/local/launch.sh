#!/usr/bin/env bash
set -e

#launch local postgres instance with docker compose
docker-compose up -d

cd ../../

# 1. apply flyway migration scripts
# 2. generate jooq classes based on the db schema
# 3. compile application and run in locally
./gradlew clean flywayMigrate generateAccountJooqSchemaSource bootRun