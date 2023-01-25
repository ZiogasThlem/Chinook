-- Creating the tables Superhero, Assistant, Superpower (power keyword
-- is taken in postgres so we used Superpower for them to be completely
-- discrete from each other), and dropping them, if the already exist,
-- at the order they are created since at this stage they have no connection
-- between them, so there are no restrictions.
DROP TABLE IF EXISTS Superhero;

CREATE TABLE Superhero(
	superhero_id serial PRIMARY KEY,
    -- Leaving this field nullable on purpose as a Superhero can be
    -- know only by his alias and not his/her actual name.
	superhero_name varchar(50),
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
    -- Leaving this field nullable on purpose as a Superpower might
    -- be too abstract to actually give it a short name and since there
    -- is a description field in this table, a generic name can be nullable.
	superpower_name varchar(50),
	superpower_description varchar(150) NOT NULL
);
