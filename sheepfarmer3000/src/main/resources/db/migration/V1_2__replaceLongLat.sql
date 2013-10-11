ALTER TABLE coordinate DROP COLUMN latitude;
ALTER TABLE coordinate ADD COLUMN latitude double precision;

ALTER TABLE coordinate DROP COLUMN longitude;
ALTER TABLE coordinate ADD COLUMN longitude double precision;
