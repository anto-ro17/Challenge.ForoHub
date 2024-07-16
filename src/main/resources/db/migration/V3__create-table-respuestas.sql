create table respuestas(

id bigint not null auto_increment,
solucion varchar(1000)not null,
autor int not null,
topico int not null,
fechaDeCreacion datetime not null,

primary key(id)
);