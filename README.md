# dropwizard-hk-async

How to start the dropwizard-hk-async application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-hk-async-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running visit [http://localhost:8080/swagger](http://localhost:8080/swagger)

Health Check
---

To see your applications health visit [http://localhost:8081/](http://localhost:8081/)

Data
---

Create keyspace:
```sql
CREATE KEYSPACE library WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
```
Create table:
```sql
CREATE TABLE library.books (
    title text,
    id uuid,
    author text,
    PRIMARY KEY (title, id)
) WITH CLUSTERING ORDER BY (id DESC);
```
Insert some data:
```sql
insert into books (title, id, author) values ('Animal Farm', uuid(), 'George Orwell');
insert into books (title, id, author) values ('The Ragged-Trousered Philanthropists', uuid(), 'Robert Tressell');
insert into books (title, id, author) values ('For Whom the Bell Tolls', uuid(), 'Ernest Hemmingway');
insert into books (title, id, author) values ('Oliver Twist', uuid(), 'Charles Dickens');
```