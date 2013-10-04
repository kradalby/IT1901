

CREATE TABLE coordinate (
    id integer NOT NULL,
    latitude character varying(255),
    longitude character varying(255),
    attack boolean,
    dateevent timestamp,
    sheepid integer NOT NULL,
    farmerid integer
);

CREATE TABLE farmer (
    id integer NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    hashpass character varying(255),
    email character varying(255),
    phone character varying(255),
    helperfirstname character varying,
    helperlastname character varying
);


CREATE TABLE sheep (
    id integer NOT NULL,
    farmerid integer NOT NULL,
    weight integer,
    birth date,
    alive boolean
);



