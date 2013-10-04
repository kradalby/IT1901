--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.4
-- Dumped by pg_dump version 9.1.4
-- Started on 2013-10-04 14:03:03

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;



--
-- TOC entry 163 (class 1259 OID 85406)
-- Dependencies: 6
-- Name: coordinate; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE coordinate (
    id integer NOT NULL,
    latitude character varying(255),
    longitude character varying(255),
    attack boolean,
    dateevent timestamp without time zone,
    sheepid integer NOT NULL,
    farmerid integer
);


ALTER TABLE public.coordinate OWNER TO prosjekt;

--
-- TOC entry 1883 (class 0 OID 0)
-- Dependencies: 163
-- Name: COLUMN coordinate.latitude; Type: COMMENT; Schema: public; Owner: prosjekt
--

COMMENT ON COLUMN coordinate.latitude IS '
';


--
-- TOC entry 161 (class 1259 OID 77264)
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
    helperlastname character varying
);


ALTER TABLE public.farmer OWNER TO prosjekt;

--
-- TOC entry 1884 (class 0 OID 0)
-- Dependencies: 161
-- Name: COLUMN farmer.email; Type: COMMENT; Schema: public; Owner: prosjekt
--

COMMENT ON COLUMN farmer.email IS '
';


--
-- TOC entry 164 (class 1259 OID 101804)
-- Dependencies: 1861 6
-- Name: schema_history; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE schema_history (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.schema_history OWNER TO prosjekt;

--
-- TOC entry 162 (class 1259 OID 77272)
-- Dependencies: 6
-- Name: sheep; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE sheep (
    id integer NOT NULL,
    farmerid integer NOT NULL,
    weight integer,
    birth date,
    alive boolean
);


ALTER TABLE public.sheep OWNER TO prosjekt;

--
-- TOC entry 1875 (class 0 OID 85406)
-- Dependencies: 163
-- Data for Name: coordinate; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY coordinate (id, latitude, longitude, attack, dateevent, sheepid, farmerid) FROM stdin;
\.


--
-- TOC entry 1873 (class 0 OID 77264)
-- Dependencies: 161
-- Data for Name: farmer; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY farmer (id, firstname, lastname, hashpass, email, phone, helperfirstname, helperlastname) FROM stdin;
\.


--
-- TOC entry 1876 (class 0 OID 101804)
-- Dependencies: 164
-- Data for Name: schema_history; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY schema_history (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	1.0	Base Migration	INIT	Base Migration	\N	prosjekt	2013-10-04 14:00:55.86	0	t
\.


--
-- TOC entry 1874 (class 0 OID 77272)
-- Dependencies: 162
-- Data for Name: sheep; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY sheep (id, farmerid, weight, birth, alive) FROM stdin;
\.


--
-- TOC entry 1867 (class 2606 OID 85413)
-- Dependencies: 163 163
-- Name: coordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY coordinate
    ADD CONSTRAINT coordinate_pkey PRIMARY KEY (id);


--
-- TOC entry 1863 (class 2606 OID 77271)
-- Dependencies: 161 161
-- Name: farmer_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY farmer
    ADD CONSTRAINT farmer_pkey PRIMARY KEY (id);


--
-- TOC entry 1870 (class 2606 OID 101812)
-- Dependencies: 164 164
-- Name: schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY schema_history
    ADD CONSTRAINT schema_history_pk PRIMARY KEY (version);


--
-- TOC entry 1865 (class 2606 OID 77276)
-- Dependencies: 162 162
-- Name: sheep_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY sheep
    ADD CONSTRAINT sheep_pkey PRIMARY KEY (id);


--
-- TOC entry 1868 (class 1259 OID 101814)
-- Dependencies: 164
-- Name: schema_history_ir_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_ir_idx ON schema_history USING btree (installed_rank);


--
-- TOC entry 1871 (class 1259 OID 101815)
-- Dependencies: 164
-- Name: schema_history_s_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_s_idx ON schema_history USING btree (success);


--
-- TOC entry 1872 (class 1259 OID 101813)
-- Dependencies: 164
-- Name: schema_history_vr_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_vr_idx ON schema_history USING btree (version_rank);


--
-- TOC entry 1881 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-10-04 14:03:03

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.4
-- Dumped by pg_dump version 9.1.4
-- Started on 2013-10-04 14:03:03

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 165 (class 3079 OID 11639)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1882 (class 0 OID 0)
-- Dependencies: 165
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 163 (class 1259 OID 85406)
-- Dependencies: 6
-- Name: coordinate; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE coordinate (
    id integer NOT NULL,
    latitude character varying(255),
    longitude character varying(255),
    attack boolean,
    dateevent timestamp without time zone,
    sheepid integer NOT NULL,
    farmerid integer
);


ALTER TABLE public.coordinate OWNER TO prosjekt;

--
-- TOC entry 1883 (class 0 OID 0)
-- Dependencies: 163
-- Name: COLUMN coordinate.latitude; Type: COMMENT; Schema: public; Owner: prosjekt
--

COMMENT ON COLUMN coordinate.latitude IS '
';


--
-- TOC entry 161 (class 1259 OID 77264)
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
    helperlastname character varying
);


ALTER TABLE public.farmer OWNER TO prosjekt;

--
-- TOC entry 1884 (class 0 OID 0)
-- Dependencies: 161
-- Name: COLUMN farmer.email; Type: COMMENT; Schema: public; Owner: prosjekt
--

COMMENT ON COLUMN farmer.email IS '
';


--
-- TOC entry 164 (class 1259 OID 101804)
-- Dependencies: 1861 6
-- Name: schema_history; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE schema_history (
    version_rank integer NOT NULL,
    installed_rank integer NOT NULL,
    version character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.schema_history OWNER TO prosjekt;

--
-- TOC entry 162 (class 1259 OID 77272)
-- Dependencies: 6
-- Name: sheep; Type: TABLE; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE TABLE sheep (
    id integer NOT NULL,
    farmerid integer NOT NULL,
    weight integer,
    birth date,
    alive boolean
);


ALTER TABLE public.sheep OWNER TO prosjekt;

--
-- TOC entry 1875 (class 0 OID 85406)
-- Dependencies: 163
-- Data for Name: coordinate; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY coordinate (id, latitude, longitude, attack, dateevent, sheepid, farmerid) FROM stdin;
\.


--
-- TOC entry 1873 (class 0 OID 77264)
-- Dependencies: 161
-- Data for Name: farmer; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY farmer (id, firstname, lastname, hashpass, email, phone, helperfirstname, helperlastname) FROM stdin;
\.


--
-- TOC entry 1876 (class 0 OID 101804)
-- Dependencies: 164
-- Data for Name: schema_history; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY schema_history (version_rank, installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	1.0	Base Migration	INIT	Base Migration	\N	prosjekt	2013-10-04 14:00:55.86	0	t
\.


--
-- TOC entry 1874 (class 0 OID 77272)
-- Dependencies: 162
-- Data for Name: sheep; Type: TABLE DATA; Schema: public; Owner: prosjekt
--

COPY sheep (id, farmerid, weight, birth, alive) FROM stdin;
\.


--
-- TOC entry 1867 (class 2606 OID 85413)
-- Dependencies: 163 163
-- Name: coordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY coordinate
    ADD CONSTRAINT coordinate_pkey PRIMARY KEY (id);


--
-- TOC entry 1863 (class 2606 OID 77271)
-- Dependencies: 161 161
-- Name: farmer_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY farmer
    ADD CONSTRAINT farmer_pkey PRIMARY KEY (id);


--
-- TOC entry 1870 (class 2606 OID 101812)
-- Dependencies: 164 164
-- Name: schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY schema_history
    ADD CONSTRAINT schema_history_pk PRIMARY KEY (version);


--
-- TOC entry 1865 (class 2606 OID 77276)
-- Dependencies: 162 162
-- Name: sheep_pkey; Type: CONSTRAINT; Schema: public; Owner: prosjekt; Tablespace: 
--

ALTER TABLE ONLY sheep
    ADD CONSTRAINT sheep_pkey PRIMARY KEY (id);


--
-- TOC entry 1868 (class 1259 OID 101814)
-- Dependencies: 164
-- Name: schema_history_ir_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_ir_idx ON schema_history USING btree (installed_rank);


--
-- TOC entry 1871 (class 1259 OID 101815)
-- Dependencies: 164
-- Name: schema_history_s_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_s_idx ON schema_history USING btree (success);


--
-- TOC entry 1872 (class 1259 OID 101813)
-- Dependencies: 164
-- Name: schema_history_vr_idx; Type: INDEX; Schema: public; Owner: prosjekt; Tablespace: 
--

CREATE INDEX schema_history_vr_idx ON schema_history USING btree (version_rank);


--
-- TOC entry 1881 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-10-04 14:03:03

--
-- PostgreSQL database dump complete
--

