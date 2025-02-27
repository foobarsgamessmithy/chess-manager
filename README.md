# A chess manager

A small spring boot application which can manage chess games for demonstration purposes.

## Usage

A collection of request to interact with the application is in the [docs](./doc/bruno/chess%20manager). Use [Bruno](https://www.usebruno.com/downloads) client to send requests. When application runs with authentication, use the auth request first. All other request will use the auth token form the auth request.

### Authentication request

When the application is started with authentication enabled, you first have to authenticate at the keycloak server. Therefore, you obtain a jwt token which is used in all other requests. The configuration of this request can be found withing the [bruno file](./doc/bruno/chess%20manager/keycloak/Auth.bru). Change username and password to use other test users. Client id and client secret are defined in the [realm](./infrastucture/keycloak/realm-export.json).

## Development

### Available Profiles

- test: Profile is used for unit test execution
- dev: Enable authentication but also h2 console is available
- insecure: Disables authentication

### Keycloak 

To use keycloak you can use the [local](https://www.keycloak.org/downloads) installation or the docker image which is configured in the [docker-compose](./infrastucture/docker/docker-compose.yml) file.

The realm configuration must be imported via [realm.json](./infrastucture/keycloak/realm-export.json). Test users are also included in the end of the file. Also, the client secrets are set in the file. Of course this secrets should not be used for production. 

The connection from keycloak to the chessmanager app is done with oauth2 like in is this [example](https://www.baeldung.com/spring-boot-keycloak).


###
Roles are not used in application now.

| role    | description |
|---------|-------------|
| user    |             |
| manager |             |


#### Test user

This test user are predefined.

| User name | password | role    |
|-----------|----------|---------|
| foobar    | foobar         | manager |
| drdrunkenstein    | drdrunkenstein         | user    |


### DB Access

When application is running with dev profile the database is accessible via [browser](http://localhost:8081/h2). Credentials can be taken from `spring.datasource` in the [application.properties](./src/main/resources/application.properties)