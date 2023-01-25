-- Creating the table Superhero_power, that establishes
-- the many to many connection between Superhero and
-- Superpower (a Superhero can have many Superpower and
-- the same Superpower can be shared between many Superheroes)
-- and dropping it if it already exists.

DROP TABLE IF EXISTS superhero_power;

CREATE TABLE superhero_power(
	hero_id int REFERENCES Superhero, -- not null maybe? (check again)
	power_id int REFERENCES superpower, -- not null maybe? (check again)
	PRIMARY KEY (hero_id,power_id)
);
