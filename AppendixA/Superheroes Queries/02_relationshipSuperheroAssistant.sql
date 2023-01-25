-- Altering table Assistant to add theForeign Key "mentor_id"
-- that references the Superhero table, thus creating a connection
-- between them.

ALTER TABLE assistant
ADD COLUMN mentor_id int REFERENCES superhero;