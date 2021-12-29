DROP DATABASE IF EXISTS primaria;
CREATE DATABASE primaria;
USE primaria;
create table catPerfil(
	idPerfil int AUTO_INCREMENT primary key,
	perfil nvarchar (250) not null
);
create table catMateria(
	idMateria int AUTO_INCREMENT primary key,
	materia nvarchar(400) not null,
	grado int,
	horas int
);
create table catSalon(
	idSalon int AUTO_INCREMENT primary key,
	nomSalon nvarchar(150) not null unique,
	cupo int not null,
	piso nvarchar(100)
);
create table catTurno(
	idTurno int AUTO_INCREMENT primary key,
	turno nvarchar(100) not null unique
);
create table catGrupo(
	idGrupo int AUTO_INCREMENT primary key,
	grupo nvarchar(150) unique not null,
	idTurno int,
	foreign key (idTurno) references catTurno(idTurno)
);
create table catCalificacion(
	idCalificacion int AUTO_INCREMENT primary key,
	parcial nvarchar(150) unique not null
);
create table Datos(
	idDatos int AUTO_INCREMENT primary key,
	nombre nvarchar(255) not null,
	apPat nvarchar(255),
	apMat nvarchar(255),
	fechNac date not null,
	direccion nvarchar(1000) not null,
	sexo nvarchar(15) not null,
	curp nvarchar(18) unique not null
);
create table Alumno(
	idAlumno int primary key,
	fechaInscripcion date not null,
	promedio decimal(2,2),
	idDatos int, 
	foreign key (idDatos) references Datos(idDatos)
);
create table Personal(
	idPersonal int AUTO_INCREMENT primary key,
	sueldoHora decimal(13,2) not null,
	rfc nvarchar(13) not null unique,
	email nvarchar(200) not null unique,
	idSalon int,
	idDatos int,
	idPerfil int,
	foreign key (idSalon) references catSalon(idSalon),
	foreign key (idDatos) references Datos(idDatos),
	foreign key (idPerfil) references catPerfil(idPerfil)
);
create table Horario(
	idHorario int AUTO_INCREMENT primary key,
	horaInicio time not null,
	horaFin time not null,
	dia nvarchar(30) not null,
	idMateria int,
	idPersonal int,
	idSalon int,
	foreign key (idMateria) references catMateria(idMateria),
	foreign key (idPersonal) references Personal(idPersonal),
	foreign key (idSalon) references catSalon(idSalon)
);
create table Inscripcion(
	idInscripcion int AUTO_INCREMENT primary key,
	idGrupo int,
	idHorario int,
	foreign key (idGrupo) references catGrupo(idGrupo),
	foreign key (idHorario) references Horario(idHorario)
);
create table GrupoAlumno(
	idInsAlumno int AUTO_INCREMENT primary key,
	idAlumno int,
	idInscripcion int,
	foreign key (idAlumno) references Alumno(idAlumno),
	foreign key (idInscripcion) references Inscripcion(idInscripcion)
);
create table CalAlumno(
	idCalAlumno int AUTO_INCREMENT primary key,
	idInsAlumno int,
	idCalificacion int,
	calificacion int,
	foreign key (idInsAlumno) references GrupoAlumno(idInsAlumno),
	foreign key (idCalificacion) references catCalificacion(idCalificacion)
);
create table Pass(
	idPass int AUTO_INCREMENT primary key,
	pass nvarchar(50),
	idPersonal int,
	foreign key (idPersonal) references Personal (idPersonal)
);
-- Inserciones
go
	-- Perfiles
	insert into catPerfil(perfil) values ('EL ADMIN');
	insert into catPerfil(perfil) values ('Director');
	insert into catPerfil(perfil) values ('Subdirector');
	insert into catPerfil(perfil) values ('Profesor');
	insert into catPerfil(perfil) values ('Secretaria/o');
	insert into catPerfil(perfil) values ('Personal de limpieza');
	insert into catPerfil(perfil) values ('Alumno');
	-- Salones
	insert into catSalon(nomSalon,cupo,piso) values ('Sin Salon', 0, 'Todo el edificio');
	insert into catSalon(nomSalon,cupo,piso) values ('Cubiculo 002', 2, 'Primer Piso');
go
	insert into Datos(nombre,apPat,apMat,fechNac,curp,direccion,sexo) values ('Soy','El','Admin','2001-01-01','AAAAAAAAAAAAAAAAAA','N/A','Hombre');
	insert into Personal(rfc,email,sueldoHora,idDatos,idPerfil,idSalon) values ('BBBBBBBBBBBBB','admin',69420.69,1,1,1);
	insert into Pass(idPersonal,pass) values (1,'admin');


select ps.pass from Datos d, Personal p, Pass ps where d.idDatos=p.idDatos and ps.idPersonal=p.idPersonal and p.email='admin' and ps.pass='admin';
