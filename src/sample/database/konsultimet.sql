create database konsultimet_test;
use konsultimet_test;

truncate table users;

drop table konsultimet;
create table konsultimet (
	Profesori varchar(255),
    Studenti varchar(255),
    Lenda varchar(255),
    Koha_fillimi datetime,
    primary key(Profesori, Koha_fillimi)
);

select * from konsultimet;

create table Profesoret (
	ID varchar(255),
    name varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(255) not null unique,
    phone varchar(255) unique,
    website varchar(255),
    primary key(ID)
);

CREATE TABLE users(
id bigint not null,
username varchar(100) not null,
password varchar(100) not null,
primary key(id)
);


INSERT INTO users(id,username,password)
VALUES('190714100077','fisnik','1111');
INSERT INTO users(id,username,password)
VALUES('190714100013','florian','1111');
INSERT INTO users(id,username,password)
VALUES('190714100055','flamur','1111');
INSERT INTO users(id,username,password)
VALUES('190714100050','fortesa','1111');
INSERT INTO users(id,username,password)
VALUES('190714100045','erona','1111');
INSERT INTO users(id,username,password)
VALUES('190714100046','ilir','1111');

select * from konsultimet;

insert into Profesoret values (12003847981, "Blerim Rexha", "blerim.rexha", "blera123",  "blerim.rexha@uni-pr.edu", "044121212", "blerimrexha.uni-pr.edu");
insert into Profesoret values (13012800981, "Valon Raca", "valon.raca", "abstenime12",  "valon.raca@uni-pr.edu", "044321123", "valonraca.uni-pr.edu");
insert into Profesoret values (12010047923, "Isak Shabani",  "isak.shabani", "caki123", "isak.shabani@uni-pr.edu", "049998877", "isakshabani.uni-pr.edu");
insert into Profesoret values (14022866974, "Lule Ahmedi",  "lule.ahmedi", "freiburg12", "lule.ahmedi@uni-pr.edu", "044267228", "luleahmedi.uni-pr.edu");
insert into Profesoret values (15503641122, "Vigan Abdurrahmani", "vigan.abdurrahmani", "vigi123", "vigan.abdurrahmani@uni-pr.edu", "049125212", "viganabdurrahmani.uni-pr.edu");


insert into konsultimet values ("Blerim Rexha", "Fisnik Mustafa", "Rrjeta Kompjuterike", "2021-07-09 11:00:00");
insert into konsultimet values ("Blerim Rexha", "Florian Halimi", "Rrjeta Kompjuterike", "2021-07-09 11:30:00");
insert into konsultimet values ("Blerim Rexha", "Flamur Mustafa", "Rrjeta Kompjuterike", "2021-07-10 11:00:00");
insert into konsultimet values ("Blerim Rexha", "Fortesa Mujaj", "Siguria e te dhenave", "2021-07-12 11:30:00");
insert into konsultimet values ("Blerim Rexha", "Erona Syla", "Siguria e te dhenave", "2021-07-18 11:00:00");
insert into konsultimet values ("Blerim Rexha", "Ilir Jasharaj", "Rrjeta Kompjuterike", "2021-07-27 11:30:00");


insert into konsultimet values ("Blerim Rexha", "Fidan Hoxha", "Rrjeta Kompjuterike", "2021-07-26 11:30:00");
insert into konsultimet values ("Blerim Rexha", "Fisnik Mustafa", "Siguria e te dhenave", "2021-07-25 11:00:00");
insert into konsultimet values ("Blerim Rexha", "Festim Braha", "Rrjeta Kompjuterike", "2021-07-22 11:30:00");
insert into konsultimet values ("Blerim Rexha", "Endrit Shaqiri", "Siguria e te dhenave", "2021-07-19 11:00:00");
insert into konsultimet values ("Blerim Rexha", "Gentrim Canolli", "Siguria e te dhenave", "2021-07-25 11:30:00");


insert into konsultimet values ("Valon Raca", "Fidan Hoxha", "Arkitektura e Kompjutereve", "2021-07-15 13:30:00");
insert into konsultimet values ("Valon Raca", "Fisnik Mustafa", "Programimi ne Internet", "2021-07-16 13:00:00");
insert into konsultimet values ("Valon Raca", "Fjoralba Krasniqi", "Arkitektura e Kompjutereve", "2021-07-18 13:30:00");
insert into konsultimet values ("Valon Raca", "Era Tahiri", "Programimi ne Internet", "2021-07-22 13:00:00");
insert into konsultimet values ("Valon Raca", "Gentrit Hyseni", "Arkitektura e Kompjutereve", "2021-07-24 13:30:00");
insert into konsultimet values ("Valon Raca", "Endrit Shaqiri", "Programimi ne Internet", "2021-07-26 13:00:00");

insert into konsultimet values ("Vigan Abdurrahmani", "Fisnik Mustafa", "Komunikimi Njeri-Kompjuter", "2021-07-09 12:00:00");
insert into konsultimet values ("Vigan Abdurrahmani", "Florian Halimi", "Programimi i Orientuar ne Objekte", "2021-07-09 12:30:00");
insert into konsultimet values ("Vigan Abdurrahmani", "Flamur Mustafa", "Programimi i Orientuar ne Objekte", "2021-07-10 12:00:00");
insert into konsultimet values ("Vigan Abdurrahmani", "Fortesa Mujaj", "Komunikimi Njeri-Kompjuter", "2021-07-12 12:30:00");
insert into konsultimet values ("Vigan Abdurrahmani", "Erona Syla", "Programimi i Orientuar ne Objekte", "2021-07-18 12:00:00");
insert into konsultimet values ("Vigan Abdurrahmani", "Ilir Jasharaj", "Programimi i Orientuar ne Objekte", "2021-07-27 12:30:00");
