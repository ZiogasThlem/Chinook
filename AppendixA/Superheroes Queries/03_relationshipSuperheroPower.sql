DROP TABLE IF EXISTS superhero_power;

CREATE TABLE superhero_power(
	hero_id int REFERENCES Superhero, -- not null maybe? (check again)
	power_id int REFERENCES superpower, -- not null maybe? (check again)
	PRIMARY KEY (hero_id,power_id)
);
