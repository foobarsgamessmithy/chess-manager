# A chess manager

A small spring boot application which can manage chess games.

## Usage

A collection of request to interact with the application is in the [docs](./doc/bruno/chess%20manager). Use [Bruno](https://www.usebruno.com/downloads) client to send requests.

## Development

When application is running with h2 db the database is accessible via [browser](http://localhost:8080/h2). Credentials can be taken from `spring.datasource` in the [application.properties](./src/main/resources/application.properties)