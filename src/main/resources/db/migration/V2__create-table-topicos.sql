create table topicos(

id bigint not null auto_increment,
titulo varchar(100)not null unique,
mensaje varchar(700)not null unique,
fecha datetime not null,
estado varchar(20)not null,
author_id bigint not null,
curso varchar(100)not null ,

primary key(id)

);