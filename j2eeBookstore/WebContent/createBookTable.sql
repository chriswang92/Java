use example;  
create table book(  
id int primary key auto_increment,  
name varchar(30) NOT NULL,  
author varchar(30) NOT NULL,
price double NOT NULL,
description varchar(30) NOT NULL,
display int NOT NULL DEFAULT 1
);  
insert into user(id,name,author,price,description,display) values (null,'三国演义','罗贯中',32.99,'将三国的历史书',1);