--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.4
-- Dumped by pg_dump version 9.1.4
-- Started on 2013-10-11 15:42:16

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;



CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1870 (class 0 OID 0)
-- Dependencies: 164
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--
SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;




-- id is set by admin
CREATE TABLE users (
    id integer NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    hashpass character varying(255),
    email character varying(255),
    phone character varying(255)
);
ALTER TABLE public.users OWNER TO prosjekt;
ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);






--farmer  id is same as userid
CREATE TABLE farmer (
    id integer NOT NULL,
    users_id integer NOT NULL
);

ALTER TABLE public.farmer OWNER TO prosjekt;
ALTER TABLE ONLY farmer
    ADD CONSTRAINT farmer_pkey PRIMARY KEY (id);






--helper id is same as userid
CREATE TABLE helper (
    id integer NOT NULL,
    users_id integer NOT NULL,
    farmer_id integer NOT NULL
);
ALTER TABLE public.helper OWNER TO prosjekt;
ALTER TABLE ONLY helper
    ADD CONSTRAINT helper_pkey PRIMARY KEY (id);




CREATE TABLE coordinate(
  coordinate_id character varying(255) NOT NULL,
  longitude double precision,
  latitude double precision,
  dateevent timestamp without time zone,
  CONSTRAINT pk_coordinate PRIMARY KEY (coordinate_id)
);
ALTER TABLE coordinate
  OWNER TO prosjekt;



CREATE TABLE sheepcoordinate (
    sheepcoordinate_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL,
    sheep_id integer NOT NULL,
    attack_id character varying(255),
    CONSTRAINT pk_sheepcoordinate PRIMARY KEY (sheepcoordinate_id),
    CONSTRAINT fk_sheepcoordinate FOREIGN KEY (coordinate_id)
      REFERENCES coordinate (coordinate_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE public.sheepcoordinate OWNER TO prosjekt;



CREATE TABLE farmercoordinate (
    farmercoordinate_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL,
    farmerid integer NOT NULL,
    CONSTRAINT pk_farmercoordinate PRIMARY KEY (farmercoordinate_id),
    CONSTRAINT fk_farmercoordinate FOREIGN KEY (coordinate_id)
      REFERENCES coordinate (coordinate_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE public.farmercoordinate OWNER TO prosjekt;




CREATE TABLE attack (
    attack_id character varying(255) NOT NULL,
    coordinate_id character varying(255) NOT NULL,
    CONSTRAINT pk_attack PRIMARY KEY (attack_id),
    CONSTRAINT fk_attack FOREIGN KEY (coordinate_id)
      REFERENCES coordinate (coordinate_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
    
);
ALTER TABLE public.attack OWNER TO prosjekt;









CREATE TABLE sheep (
    id character varying(255) NOT NULL,
    farmerid integer NOT NULL,
    birth date,
    alive boolean
);

ALTER TABLE public.sheep OWNER TO prosjekt;
ALTER TABLE ONLY sheep
    ADD CONSTRAINT sheep_pkey PRIMARY KEY (id);





--
-- TOC entry 1869 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: prosjekt
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM prosjekt;
GRANT ALL ON SCHEMA public TO prosjekt;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

