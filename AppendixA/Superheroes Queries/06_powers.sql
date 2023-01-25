-- Inserting sample Superpower entries to the Superpower table.
-- Also inserting the respective hero_id, power_id to the
-- superhero_power table, so each Superhero is matched to one
-- ore more Superpowers, and each Superpower is attributed to
-- one or more Superheroes.

INSERT INTO superpower (superpower_name,superpower_description)
VALUES ('flying','Can fly himself and even carry his allies to the sky');

INSERT INTO superpower (superpower_name,superpower_description) 
VALUES ('web shooters','Can shoot web from his wrists, used for fighting and climbing buildings');

INSERT INTO superpower (superpower_name,superpower_description) 
VALUES ('superhuman strength','Has much more strength than that of a normal human');

INSERT INTO superpower (superpower_name,superpower_description) 
VALUES ('bat equipment','Has tools, weapons and even vehicles used to fight villains');

INSERT INTO superhero_power (hero_id,power_id) VALUES (1,1);
INSERT INTO superhero_power (hero_id,power_id) VALUES (1,3);
INSERT INTO superhero_power (hero_id,power_id) VALUES (2,4);
INSERT INTO superhero_power (hero_id,power_id) VALUES (3,2);
INSERT INTO superhero_power (hero_id,power_id) VALUES (3,3);