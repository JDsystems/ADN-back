CREATE TABLE "membresia" (
	"id" SERIAL,
	"id_cliente" INTEGER NOT NULL,
	"fecha_inicio" DATE NOT NULL,
	"fecha_fin" DATE NOT NULL,
	"estado" VARCHAR(20) NOT NULL,
	"valor_pagado" NUMERIC(10,2) NOT NULL,
	"tipo" VARCHAR(20) NOT NULL,
	"dias_habiles" INTEGER NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "FK_membresia_cliente" FOREIGN KEY ("id_cliente") REFERENCES "cliente" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
)
;
CREATE TABLE "invitacion" (
	"id" SERIAL,
	"id_membresia" INTEGER NOT NULL,
	"indentificacion_invitado" VARCHAR(11) NOT NULL,
	"nombre_invitado" VARCHAR(100) NOT NULL,
	"fecha" DATE NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "FK_invitacion_membresia" FOREIGN KEY ("id_membresia") REFERENCES "membresia" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
)
;