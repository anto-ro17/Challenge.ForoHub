create table usuarios(

id bigint not null auto_increment,
login varchar(50)not null unique,
contrasena varchar(20)not null,
nombre varchar(100)not null,
email varchar(50)not null unique,

primary key(id)

);