-- Deleting an entry of Assistant table, by assistant_name.
-- No further adjustment is required since no other table
-- has references on Assistant.

DELETE FROM assistant
WHERE assistant_name = 'Aunt May';