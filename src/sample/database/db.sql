
CREATE DATABASE db;
USE db;

CREATE TABLE users(
id int unsinged not null auto_increment,
username varchar(30) not null,
password varchar(30) not null,
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
