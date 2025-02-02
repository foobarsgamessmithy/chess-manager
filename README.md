# A chess manager

A small spring boot application which can manage chess games for demonstration purposes.

## Usage

A collection of request to interact with the application is in the [docs](./doc/bruno/chess%20manager). Use [Bruno](https://www.usebruno.com/downloads) client to send requests. When application runs with authentication, use the auth request first. All other request will use the auth token form the auth request.

## Development

### Available Profiles

- test: Profile is used for unit test execution
- dev: Enable authentication but also h2 console is available
- insecure: Disables authentication

### Keycloak 

For now [local](https://www.keycloak.org/downloads) keycloak installation is used. Using docker image is also possible. 

Keycloak is used with oauth2 like in is this [example](https://www.baeldung.com/spring-boot-keycloak).

Realm configuration must be imported via [realm.json](./infrastucture/keycloak/realm-export.json). Test users are also included.

#### Roles
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

### Logging 

For logging [slf4j](https://www.slf4j.org/manual.html) ist used. The logger can be declared as a static import by the logger factory like this:

`
private static final Logger LOG = LoggerFactory.getLogger(Foo.class);
`

To log information there are different methods for every log level. It is possible to use placeholder to write the content of objects into the log. 

`
LOG.debug("Logging the output of my variable: {}.", variable);
`

For the output medium a [logback.xml](./src/main/resources/log/logback.xml) is configured. There is an appender for console when the app is started in development mode and there is an appender for logging into a file in production mode.

The path to the logback.xml is set in the application.properties like this:

`
logging.config=classpath:/log/logback.xml
`

Also, a [banner.txt](./src/main/resources/log/banner.txt) is configured in the application.properties like this:

`
spring.banner.location=classpath:/log/banner.txt
`