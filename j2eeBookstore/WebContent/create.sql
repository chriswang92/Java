create database userdatabase;  
use userdatabase;  
create table user(  
userid int primary key auto_increment,  
name varchar(30) NOT NULL,  
password varchar(30) NOT NULL,
province varchar(30) NOT NULL,
city varchar(30) NOT NULL,
sex varchar(30) NOT NULL,
hobby varchar(30) NOT NULL,
user_describe varchar(30) NOT NULL,
display int NOT NULL DEFAULT 1
);  
insert into user(userid,name,password) values (null,'chris','1234','ontario','toronto','male','basketball','');