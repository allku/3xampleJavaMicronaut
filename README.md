# 3xampleJavaMicronaut
Basic example project in Java with Micronaut Framework

## Software
* Java 11
* Micronaut
* Postgres 12 or above
* Gradle 7.2

### Create database on GNU/Linux and MacOS with Postgres.app
```
createdb ejavamicronaut
```
```
createuser jorgeluis
```
```
psql
```
or
```
psql -d database -U user -W
```
```
grant all privileges on database ejavamicronaut to jorgeluis;
```
```
alter user jorgeluis with encrypted password 'j';
```
### Run
```
gradle run 
```
### Run Hot Reload
```
gradle run --continuous
```
## Test
Use HTTPie
https://httpie.io/
### GET
```
http http://localhost:8080/rest/v1/beers
```
### GET
```
http http://localhost:8080/rest/v1/beer/1
```
### POST
```
http POST http://localhost:8080/rest/v1/beer <<< '{
    "brand": "Anheuser-Busch Inbev",
    "dateReleased": "2000-01-01",
    "ingredients": [
        {
            "name": "Malta"
        },
        {
            "name": "Agua"
        }
    ],
    "name": "Cusqueña",
    "origin": "Perú"
}'
```
### PUT
```
http PUT http://localhost:8080/rest/v1/beer/3 <<< '{
    "brand": "Other",
    "dateReleased": "2001-01-01",
    "name": "Cusqueñita",
    "origin": "Perú - Ecuador"
}'
```
### DELETE
```
http DELETE http://localhost:8080/rest/v1/beer/3
```

## Micronaut 3.1.1 Documentation

- [User Guide](https://docs.micronaut.io/3.1.1/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.1.1/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.1.1/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Feature jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Feature testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

## Feature hibernate-jpa documentation

- [Micronaut Hibernate JPA documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#hibernate)

