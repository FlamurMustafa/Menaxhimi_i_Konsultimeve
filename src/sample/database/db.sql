
CREATE DATABASE db;
USE db;

CREATE TABLE users(
id int not null,
username varchar(100) not null,
password varchar(100) not null,
primary key(id)
);

INSERT INTO users(id,username,password)
VALUES('190714100077','1111');
INSERT INTO users(id,username,password)
VALUES('190714100013','1111');
INSERT INTO users(id,username,password)
VALUES('190714100055','1111');
INSERT INTO users(id,username,password)
VALUES('190714100050','1111');
INSERT INTO users(id,username,password)
VALUES('190714100045','1111');
