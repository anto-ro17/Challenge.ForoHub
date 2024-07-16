alter table usuarios add activo tinyint;
update usuarios set activo=1;
alter table topicos add activo tinyint;
update topicos set activo=1;
alter table respuestas add activo tinyint;
update respuestas set activo=1;