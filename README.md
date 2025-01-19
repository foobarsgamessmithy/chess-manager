# A chess manager

A small spring boot application which can manage chess games.

## Usage

A collection of request to interact with the application is in the [docs](./doc/bruno/chess%20manager). Use [Bruno](https://www.usebruno.com/downloads) client to send requests.

## Development

### Available Profiles

- test: Profile is used for unit test execution
- dev: Enable authentication but also h2 console is available
- insecure: Disables authentication

### Keycloak 

Keycloak is used with oauth2 like in is this [example](https://www.baeldung.com/spring-boot-keycloak).

Realm configuration must be imported via [realm.json](./infrastucture/keycloak/realm-export.json)

### DB Access

When application is running with dev profile db the database is accessible via [browser](http://localhost:8081/h2). Credentials can be taken from `spring.datasource` in the [application.properties](./src/main/resources/application.properties)