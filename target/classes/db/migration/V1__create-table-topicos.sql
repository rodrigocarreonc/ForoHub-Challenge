create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fechaCreacion varchar(6) not null,
    idUsuario bigint not null,
    nombreCurso varchar(100) not null,
    activo tinyint,

    primary key(id)
);