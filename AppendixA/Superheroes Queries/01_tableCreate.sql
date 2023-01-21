DROP TABLE IF EXISTS Superhero;

CREATE TABLE Superhero(
	superhero_id serial PRIMARY KEY,
	superhero_name varchar(50) NOT NULL, --maybe null?
	superhero_alias varchar(50) NOT NULL,
	superhero_origin varchar(50) NOT NULL
);

DROP TABLE IF EXISTS Assistant;

CREATE TABLE Assistant(
	assistant_id serial PRIMARY KEY,
	assistant_name varchar(50) NOT NULL
);

DROP TABLE IF EXISTS Superpower;

CREATE TABLE Superpower(
	superpower_id serial PRIMARY KEY,
	superpower_name varchar(50), --maybe null?
	superpower_description varchar(150) NOT NULL
);
