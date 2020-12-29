# 3xampleJavaMicronaut
Basic example project in Java with Micronaut

## Requirements
* Java
* Micronaut
* Postgres 12

## Create project
```console
cd 3xampleJavaMicronaut
```
## Install requirements
```console
```

## Create database
### GNU/Linux
```console
sudo su - postgres
createdb ejavamicronaut
createuser jojelu
psql
grant all privileges on database ejavamicronaut to jojelu;
alter user jojelu with encrypted password 'j';
```

### MacOS with Postgres.app
```console
createdb ejavamicronaut
createuser jojelu
psql
grant all privileges on database ejavamicronaut to jojelu;
alter user jojelu with encrypted password 'j';
```

### Migrate to database
```console
dotnet ef migrations add init
dotnet ef database update
```

## Run
```console
dotnet run
```

## Test
Use HTTPie
https://httpie.io/
### POST
```console
http --verify no POST https://localhost:5001/api/rest/v1/books < files/new_book.json
```
### GET
```console
http --verify no https://localhost:5001/api/rest/v1/books
```
### PUT
```console
http --verify no PUT https://localhost:5001/api/rest/v1/books/1 < files/update_book.json
```
### GET
```console
http --verify no https://localhost:5001/api/rest/v1/books/1
```
### DELETE
```console
http --verify no DELETE https://localhost:5001/api/rest/v1/books/2
```

## Swagger
http://localhost:8080/swagger/views/swagger-ui/