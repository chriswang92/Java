create table USER (
   user_id BIGINT NOT NULL AUTO_INCREMENT,
   username  VARCHAR(45) NOT NULL, 
   password  VARCHAR(20) NOT NULL, 
   PRIMARY KEY (user_id)
);
 
create table BLOG (
   blog_id BIGINT NOT NULL AUTO_INCREMENT,
   user_id BIGINT NOT NULL,
   title VARCHAR(50) NOT NULL,
   content  VARCHAR(500) NOT NULL,
   section    VARCHAR(30) NOT NULL,
   PRIMARY KEY (blog_id),
   CONSTRAINT blog_user FOREIGN KEY (user_id) REFERENCES USER (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);