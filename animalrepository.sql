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
    breedId integer NOT NULL,
    name character varying(20) NOT NULL,
    color character varying(20),
    description character varying(255)
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

ALTER SEQUENCE animal_id_seq OWNED BY animal.breedId;


--
-- Name: animalbreed; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animalbreed (
    breedId integer NOT NULL,
    animalid integer NOT NULL,
    breedid integer NOT NULL
);


ALTER TABLE animalbreed OWNER TO chris;

--
-- Name: animalbreed_id_seq; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE animalbreed_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animalbreed_id_seq OWNER TO chris;

--
-- Name: animalbreed_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE animalbreed_id_seq OWNED BY animalbreed.breedId;


--
-- Name: animaltype; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE animaltype (
    breedId integer NOT NULL,
    animalid integer NOT NULL,
    typeid integer NOT NULL
);


ALTER TABLE animaltype OWNER TO chris;

--
-- Name: type; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE type (
    breedId integer NOT NULL,
    type character varying(50) NOT NULL
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

ALTER SEQUENCE animaltype_id_seq OWNED BY type.breedId;


--
-- Name: animaltype_id_seq1; Type: SEQUENCE; Schema: public; Owner: chris
--

CREATE SEQUENCE animaltype_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animaltype_id_seq1 OWNER TO chris;

--
-- Name: animaltype_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: chris
--

ALTER SEQUENCE animaltype_id_seq1 OWNED BY animaltype.breedId;


--
-- Name: breed; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE breed (
    breedId integer NOT NULL,
    breed character varying(50) NOT NULL
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

ALTER SEQUENCE breed_id_seq OWNED BY breed.breedId;


--
-- Name: note; Type: TABLE; Schema: public; Owner: chris
--

CREATE TABLE note (
    breedId integer NOT NULL,
    note text NOT NULL,
    date date NOT NULL,
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

ALTER SEQUENCE table_name_id_seq OWNED BY note.breedId;


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal ALTER COLUMN breedId SET DEFAULT nextval('animal_id_seq'::regclass);


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animalbreed ALTER COLUMN breedId SET DEFAULT nextval('animalbreed_id_seq'::regclass);


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype ALTER COLUMN breedId SET DEFAULT nextval('animaltype_id_seq1'::regclass);


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed ALTER COLUMN breedId SET DEFAULT nextval('breed_id_seq'::regclass);


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note ALTER COLUMN breedId SET DEFAULT nextval('table_name_id_seq'::regclass);


--
-- Name: breedId; Type: DEFAULT; Schema: public; Owner: chris
--

ALTER TABLE ONLY type ALTER COLUMN breedId SET DEFAULT nextval('animaltype_id_seq'::regclass);


--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animal (breedId, name, color, description) FROM stdin;
1	Mia	grey	skittish
2	Myst	gray	tabby
3	Ragamuffin	black & white	'Rags' for short
4	Shadow	black & white	very energetic and loves chasing balls
\.


--
-- Name: animal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animal_id_seq', 4, true);


--
-- Data for Name: animalbreed; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animalbreed (breedId, animalid, breedid) FROM stdin;
1	1	2
2	2	2
3	3	1
4	4	4
\.


--
-- Name: animalbreed_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animalbreed_id_seq', 4, true);


--
-- Data for Name: animaltype; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY animaltype (breedId, animalid, typeid) FROM stdin;
1	1	2
2	2	2
3	3	3
4	4	1
\.


--
-- Name: animaltype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animaltype_id_seq', 4, true);


--
-- Name: animaltype_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('animaltype_id_seq1', 4, true);


--
-- Data for Name: breed; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY breed (breedId, breed) FROM stdin;
1	Domestic Short Hair
2	Domestic Medium Hair
3	Domestic Long Hair
4	Border Collie
\.


--
-- Name: breed_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('breed_id_seq', 4, true);


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY note (breedId, note, date, animalid) FROM stdin;
1	Shadow is a loving energetic ball chaser	2013-08-13	\N
2	Myst has been eating 3-4 cans of food per day and is loosing weight. Tests ordered.	2008-11-10	\N
3	Myst has cancer of the tounge	2008-11-14	\N
\.


--
-- Name: table_name_id_seq; Type: SEQUENCE SET; Schema: public; Owner: chris
--

SELECT pg_catalog.setval('table_name_id_seq', 3, true);


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: chris
--

COPY type (breedId, type) FROM stdin;
1	Dog
2	Cat
3	Pony
4	Ferret
\.


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (breedId);


--
-- Name: animalbreed_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animalbreed
    ADD CONSTRAINT animalbreed_pkey PRIMARY KEY (breedId);


--
-- Name: animaltype_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY type
    ADD CONSTRAINT animaltype_pkey PRIMARY KEY (breedId);


--
-- Name: animaltype_pkey1; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype
    ADD CONSTRAINT animaltype_pkey1 PRIMARY KEY (breedId);


--
-- Name: breed_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (breedId);


--
-- Name: table_name_pkey; Type: CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT table_name_pkey PRIMARY KEY (breedId);


--
-- Name: animalType_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX "animalType_id_uindex" ON type USING btree (breedId);


--
-- Name: animal_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX animal_id_uindex ON animal USING btree (breedId);


--
-- Name: animalbreed_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX animalbreed_id_uindex ON animalbreed USING btree (breedId);


--
-- Name: animaltype_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX animaltype_id_uindex ON animaltype USING btree (breedId);


--
-- Name: breed_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX breed_id_uindex ON breed USING btree (breedId);


--
-- Name: table_name_id_uindex; Type: INDEX; Schema: public; Owner: chris
--

CREATE UNIQUE INDEX table_name_id_uindex ON note USING btree (breedId);


--
-- Name: animalbreed_animal_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animalbreed
    ADD CONSTRAINT animalbreed_animal_id_fk FOREIGN KEY (animalid) REFERENCES animal(breedId);


--
-- Name: animalbreed_breed_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animalbreed
    ADD CONSTRAINT animalbreed_breed_id_fk FOREIGN KEY (breedid) REFERENCES breed(breedId);


--
-- Name: animaltype_animal_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype
    ADD CONSTRAINT animaltype_animal_id_fk FOREIGN KEY (animalid) REFERENCES animal(breedId);


--
-- Name: animaltype_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY animaltype
    ADD CONSTRAINT animaltype_type_id_fk FOREIGN KEY (typeid) REFERENCES type(breedId);


--
-- Name: note_animal_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: chris
--

ALTER TABLE ONLY note
    ADD CONSTRAINT note_animal_id_fk FOREIGN KEY (animalid) REFERENCES animal(breedId);


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

