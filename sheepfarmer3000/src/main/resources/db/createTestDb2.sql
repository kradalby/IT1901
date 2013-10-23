
-- id is set by admin
CREATE TABLE users (
    id integer NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    hashpass character varying(255),
    email character varying(255),
    phone character varying(255)
);
ALTER TABLE users add CONSTRAINT pk_users PRIMARY KEY (id);






--farmer  id is same as userid
CREATE TABLE farmer (
    id integer NOT NULL,
    users_id integer NOT NULL
);
ALTER TABLE farmer ADD CONSTRAINT pk_farmer PRIMARY KEY (id);





--helper id is same as userid
CREATE TABLE helper (
    id integer NOT NULL,
    users_id integer NOT NULL,
    farmer_id integer NOT NULL
);
ALTER TABLE helper ADD CONSTRAINT pk_helper PRIMARY KEY (id);




CREATE TABLE coordinate(
  coordinate_id character varying(255) NOT NULL,
  longitude double precision,
  latitude double precision,
  dateevent timestamp
);
ALTER TABLE coordinate ADD CONSTRAINT pk_coordinate PRIMARY KEY (coordinate_id);



CREATE TABLE sheepcoordinate (
    sheepcoordinate_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL,
    sheep_id integer NOT NULL,
    attack_id character varying(255)
);
ALTER TABLE sheepcoordinate ADD CONSTRAINT pk_sheepcoordinate PRIMARY KEY (sheepcoordinate_id);





CREATE TABLE farmercoordinate (
    farmercoordinate_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL,
    farmerid integer NOT NULL
);
ALTER TABLE farmercoordinate ADD CONSTRAINT pk_farmercoordinate PRIMARY KEY (farmercoordinate_id);




CREATE TABLE attack (
    attack_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL
);
ALTER TABLE attack ADD CONSTRAINT pk_attack PRIMARY KEY (attack_id);









CREATE TABLE sheep (
    id character varying(255) NOT NULL,
    farmerid integer NOT NULL,
    birth date,
    alive boolean
);
ALTER TABLE sheep ADD CONSTRAINT pk_sheep PRIMARY KEY (id);
