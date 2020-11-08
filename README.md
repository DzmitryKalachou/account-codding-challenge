# Account Codding Challenge

### Supported use cases:

 - Deposit money into a specified bank account
 - Transfer some money across two bank accounts
 - Show current balance of the specific bank account
 - Filter accounts by account type
 - Request account information by account type (could be multiple)
 - Show a transaction history, filtered by IBAN
 - Account creation
 - Account locking/unlocking
 
### Local launch

The application needs a postgres database to store accounts and transactions data.
The folder `deployments/local` has a docker-compose script to launch a postgres database instance.
Also, this folder has a `launch.sh` script that can be used to launch the application locally(script has explanation notes inside). 


### API Documentation

The application has the API documentation which was generated with a swagger.

http://localhost:8080/v2/api-docs
http://localhost:8080/swagger-ui.html

### Database schema

The database schema migration files are placed in the `sql/migration` folder. 
The Gradle build script has the Flyway Gradle plugin configuration that can be used to apply sql migration files to required database and to control database versions as well.

### Actuator

The application has the connected Spring Boot actuator with a health-check endpoint which validates the ability to connect to the database and a disk space availability on each request.

URL: http://localhost:8080/actuator/health

### Deployment

The file `deployments/remote/deployment.sh` has a script with the application deployment steps.   
This file doesn't have implementation of all required steps and was written rather for a demonstration purpose, i.e. to show all possible preparational/deployment steps. 
Please note that for the real production-ready deployment, it would be much better to have a proper CI configuration that will take care of deployment and database migration instead launching these steps from the shell script

### Running configuration

A running application Spring Boot application can be configured with an environment variables.
Thus, It should has access to environment variables with url and credentials for a database that will be used to store accounts and transactions:
  
- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD

Optionally, there can be a JAVA_OPTS environment variable with JVM configuration options.

 
 