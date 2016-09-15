--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animal (
    id integer NOT NULL,
    name character varying(20) NOT NULL,
    color character varying(20),
    description character varying(255),
    typeid integer,
    breedid integer
);


ALTER TABLE animal OWNER TO chris;

--
-- Name: animal_id_seq; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animal_id_seq OWNER TO chris;

--
-- Name: animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE animal_id_seq OWNED BY animal.id;


--
-- Name: animaltype; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animaltype (
    typeid integer NOT NULL,
    typename character varying(50) NOT NULL
);


ALTER TABLE animaltype OWNER TO chris;

--
-- Name: animaltype_id_seq; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE animaltype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animaltype_id_seq OWNER TO chris;

--
-- Name: animaltype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE animaltype_id_seq OWNED BY animaltype.typeid;


--
-- Name: breed; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE breed (
    breedid integer NOT NULL,
    breed character varying(50) NOT NULL,
    typeid integer
);


ALTER TABLE breed OWNER TO chris;

--
-- Name: breed_id_seq; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE breed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE breed_id_seq OWNER TO chris;

--
-- Name: breed_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE breed_id_seq OWNED BY breed.breedid;


--
-- Name: note; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE note (
    noteid integer NOT NULL,
    note text NOT NULL,
    date timestamp without time zone NOT NULL,
    animalid integer
);


ALTER TABLE note OWNER TO chris;

--
-- Name: table_name_id_seq; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE table_name_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE table_name_id_seq OWNER TO chris;

--
-- Name: table_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE table_name_id_seq OWNED BY note.noteid;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal ALTER COLUMN id SET DEFAULT nextval('animal_id_seq'::regclass);


--
-- Name: typeid; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype ALTER COLUMN typeid SET DEFAULT nextval('animaltype_id_seq'::regclass);


--
-- Name: breedid; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed ALTER COLUMN breedid SET DEFAULT nextval('breed_id_seq'::regclass);


--
-- Name: noteid; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note ALTER COLUMN noteid SET DEFAULT nextval('table_name_id_seq'::regclass);


--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animal (id, name, color, description, typeid, breedid) FROM stdin;
4	Shadow	black & white	very energetic and loves chasing balls	1	4
3	Ragamuffin	black & white	'Rags' for short	4	2
1	Mia	grey	skittish	3	2
2	Myst	gray	tabby	2	3
72	Joey	brown	loves to run obstical courses	4	1
74	Dave	Brown	funny	1	4
75	Sammie	green		1	4
\.


--
-- Name: animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animal_id_seq', 75, true);


--
-- Data for Name: animaltype; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animaltype (typeid, typename) FROM stdin;
1	Dog
3	Pony
2	Cat
4	Ferret
\.


--
-- Name: animaltype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animaltype_id_seq', 6, true);


--
-- Data for Name: breed; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY breed (breedid, breed, typeid) FROM stdin;
4	Border Collie	1
1	Domestic Short Hair	2
3	Domestic Long Hair	2
2	Domestic Medium Hair	2
6	Shnauzer	1
\.


--
-- Name: breed_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('breed_id_seq', 6, true);


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY note (noteid, note, date, animalid) FROM stdin;
1	Shadow is a loving energetic ball chaser	2013-08-13 00:00:00	4
2	Myst has been eating 3-4 cans of food per day and is loosing weight. Tests ordered.	2008-11-10 00:00:00	2
3	Myst has cancer of the tounge	2008-11-14 00:00:00	2
7	Myst died in 2008	2016-09-12 22:07:22.673	2
8	Dave is a very obedient and loving dog	2016-09-14 16:26:02.867	74
\.


--
-- Name: table_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('table_name_id_seq', 8, true);


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- Name: animaltype_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype
    ADD CONSTRAINT animaltype_pkey PRIMARY KEY (typeid);


--
-- Name: breed_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (breedid);


--
-- Name: table_name_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT table_name_pkey PRIMARY KEY (noteid);


--
-- Name: animalType_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX "animalType_id_uindex" ON animaltype USING btree (typeid);


--
-- Name: animal_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX animal_id_uindex ON animal USING btree (id);


--
-- Name: breed_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX breed_id_uindex ON breed USING btree (breedid);


--
-- Name: table_name_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX table_name_id_uindex ON note USING btree (noteid);


--
-- Name: breed_type_typeid_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT breed_type_typeid_fk FOREIGN KEY (typeid) REFERENCES animaltype(typeid);


--
-- Name: note_animal_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT note_animal_id_fk FOREIGN KEY (animalid) REFERENCES animal(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

