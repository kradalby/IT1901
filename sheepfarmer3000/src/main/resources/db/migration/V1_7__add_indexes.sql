CREATE INDEX index_sc_coordinateid
  ON sheepcoordinate
  USING btree
  (id COLLATE pg_catalog."default" , sheep_id COLLATE pg_catalog."default" , coordinate_id COLLATE pg_catalog."default" );

-- Index: index_sc_id

-- DROP INDEX index_sc_id;

CREATE INDEX index_sc_id
  ON sheepcoordinate
  USING btree
  (id COLLATE pg_catalog."default" );

-- Index: index_sc_sheepid

-- DROP INDEX index_sc_sheepid;

CREATE INDEX index_sc_sheepid
  ON sheepcoordinate
  USING btree
  (sheep_id COLLATE pg_catalog."default" );


CREATE INDEX index_sheep_id
  ON sheep
  USING btree
  (id COLLATE pg_catalog."default" );

-- Index: index_sheep_lastcoord

-- DROP INDEX index_sheep_lastcoord;

CREATE INDEX index_sheep_lastcoord
  ON sheep
  USING btree
  (lastcoordinateid COLLATE pg_catalog."default" );



-- Index: coordinate_index_id

-- DROP INDEX coordinate_index_id;

CREATE INDEX coordinate_index_id
  ON coordinate
  USING btree
  (id COLLATE pg_catalog."default" );
