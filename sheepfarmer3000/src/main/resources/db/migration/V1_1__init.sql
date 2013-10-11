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

--
-- TOC entry 164 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1870 (class 0 OID 0)
-- Dependencies: 164
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--



SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 161 (class 1259 OID 101976)
-- Dependencies: 6
-- Name: coordinate; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE coordinate (
    id integer NOT NULL,
    latitude character varying(255),
    longitude character varying(255),
    attack boolean,
    dateevent timestamp without time zone,
    sheepid integer,
    farmerid integer
);


ALTER TABLE public.coordinate OWNER TO prosjekt;

--
-- TOC entry 162 (class 1259 OID 101982)
-- Dependencies: 6
-- Name: farmer; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE farmer (
    id integer NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    hashpass character varying(255),
    email character varying(255),
    phone character varying(255),
    helperfirstname character varying,
    helperlastname character varying,
    helperemail character varying,
    helperphone character varying
);


ALTER TABLE public.farmer OWNER TO prosjekt;

--
-- TOC entry 163 (class 1259 OID 101988)
-- Dependencies: 6
-- Name: sheep; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE sheep (
    id integer NOT NULL,
    farmerid integer NOT NULL,
    birth date,
    alive boolean
);


ALTER TABLE public.sheep OWNER TO prosjekt;

--
-- TOC entry 1862 (class 0 OID 101976)
-- Dependencies: 161
-- Data for Name: coordinate; Type: TABLE DATA; Schema: public; Owner: prosjekt
--


--
-- TOC entry 1857 (class 2606 OID 101992)
-- Dependencies: 161 161
-- Name: coordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY coordinate
    ADD CONSTRAINT coordinate_pkey PRIMARY KEY (id);


--
-- TOC entry 1859 (class 2606 OID 101994)
-- Dependencies: 162 162
-- Name: farmer_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY farmer
    ADD CONSTRAINT farmer_pkey PRIMARY KEY (id);


--
-- TOC entry 1861 (class 2606 OID 101996)
-- Dependencies: 163 163
-- Name: sheep_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

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


-- Completed on 2013-10-11 15:42:16

--
-- PostgreSQL database dump complete
--

