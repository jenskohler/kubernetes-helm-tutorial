# Showcase for JWT Auth

This module shows an exemplified implementation of JWT authn and authz. 

## Local Start of Auth Service

Start the service with 

```cmd
java -jar -Dspring.profiles.active=local auth-service-0.0.1-SNAPSHOT.jar
```
The auth service is started on port 8180 and initially uses a MariaDB database to persist
users and attach roles to the users. 

Provision the database with

```sql
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

Register a new user with

```cmd
curl --location --request POST 'http://localhost:8180/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "jens",
    "email": "vielleicht@web.de",
    "password": "jenskohler",
    "role": ["mod", "user"]
}'
```

Sign in with the just registered user to receive a Bearer token

```cmd
curl --location --request POST 'http://localhost:8180/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "jens",
    "password": "jenskohler"
}'
```

This request returns a Bearer token, which then can be used to authenticate against the 
controllers' service calls. 

```cmd
curl --location --request GET 'http://localhost:8180/auth/showcase/all' \
--header 'Authorization: Bearer <BEARER TOKEN> '

curl --location --request GET 'http://localhost:8180/auth/showcase/user' \
--header 'Authorization: Bearer <BEARER TOKEN> '

curl --location --request GET 'http://localhost:8180/auth/showcase/mod' \
--header 'Authorization: Bearer <BEARER TOKEN> '

curl --location --request GET 'http://localhost:8180/auth/showcase/admin' \
--header 'Authorization: Bearer <BEARER TOKEN> '

```

Example was mainly derived from <https://www.bezkoder.com/spring-boot-jwt-authentication/>