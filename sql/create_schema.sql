--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-01-28 15:00:40

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16394)
-- Name: railway; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA railway;


ALTER SCHEMA railway OWNER TO postgres;

--
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16479)
-- Name: category; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE railway.category OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16485)
-- Name: goods; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.goods (
    id bigint NOT NULL,
    date date,
    description character varying(255),
    is_deleted boolean NOT NULL,
    min_quantity real NOT NULL,
    name character varying(255),
    total_quantity real NOT NULL,
    category bigint,
    unit bigint
);


ALTER TABLE railway.goods OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16484)
-- Name: goods_id_seq; Type: SEQUENCE; Schema: railway; Owner: postgres
--

CREATE SEQUENCE railway.goods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE railway.goods_id_seq OWNER TO postgres;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 217
-- Name: goods_id_seq; Type: SEQUENCE OWNED BY; Schema: railway; Owner: postgres
--

ALTER SEQUENCE railway.goods_id_seq OWNED BY railway.goods.id;


--
-- TOC entry 221 (class 1259 OID 16529)
-- Name: inventory; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.inventory (
    id bigint NOT NULL,
    date date,
    description character varying(255),
    handover_to character varying(255),
    inventory_type character varying(10),
    quantity real NOT NULL,
    shed_store_no character varying(255),
    supervisor_name character varying(255),
    "time" time without time zone,
    goods_id bigint,
    unit_id bigint
);


ALTER TABLE railway.inventory OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16528)
-- Name: inventory_id_seq; Type: SEQUENCE; Schema: railway; Owner: postgres
--

CREATE SEQUENCE railway.inventory_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE railway.inventory_id_seq OWNER TO postgres;

--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 220
-- Name: inventory_id_seq; Type: SEQUENCE OWNED BY; Schema: railway; Owner: postgres
--

ALTER SEQUENCE railway.inventory_id_seq OWNED BY railway.inventory.id;


--
-- TOC entry 212 (class 1259 OID 16419)
-- Name: roles; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.roles (
    id integer NOT NULL,
    name character varying(20)
);


ALTER TABLE railway.roles OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16418)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: railway; Owner: postgres
--

CREATE SEQUENCE railway.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE railway.roles_id_seq OWNER TO postgres;

--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 211
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: railway; Owner: postgres
--

ALTER SEQUENCE railway.roles_id_seq OWNED BY railway.roles.id;


--
-- TOC entry 219 (class 1259 OID 16502)
-- Name: units; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.units (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE railway.units OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16426)
-- Name: user_details; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.user_details (
    id bigint NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE railway.user_details OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16425)
-- Name: user_details_id_seq; Type: SEQUENCE; Schema: railway; Owner: postgres
--

CREATE SEQUENCE railway.user_details_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE railway.user_details_id_seq OWNER TO postgres;

--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 213
-- Name: user_details_id_seq; Type: SEQUENCE OWNED BY; Schema: railway; Owner: postgres
--

ALTER SEQUENCE railway.user_details_id_seq OWNED BY railway.user_details.id;


--
-- TOC entry 215 (class 1259 OID 16434)
-- Name: user_roles; Type: TABLE; Schema: railway; Owner: postgres
--

CREATE TABLE railway.user_roles (
    user_id bigint NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE railway.user_roles OWNER TO postgres;

--
-- TOC entry 3195 (class 2604 OID 16488)
-- Name: goods id; Type: DEFAULT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.goods ALTER COLUMN id SET DEFAULT nextval('railway.goods_id_seq'::regclass);


--
-- TOC entry 3196 (class 2604 OID 16532)
-- Name: inventory id; Type: DEFAULT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.inventory ALTER COLUMN id SET DEFAULT nextval('railway.inventory_id_seq'::regclass);


--
-- TOC entry 3193 (class 2604 OID 16422)
-- Name: roles id; Type: DEFAULT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.roles ALTER COLUMN id SET DEFAULT nextval('railway.roles_id_seq'::regclass);


--
-- TOC entry 3194 (class 2604 OID 16429)
-- Name: user_details id; Type: DEFAULT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.user_details ALTER COLUMN id SET DEFAULT nextval('railway.user_details_id_seq'::regclass);


--
-- TOC entry 3204 (class 2606 OID 16483)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3206 (class 2606 OID 16492)
-- Name: goods goods_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.goods
    ADD CONSTRAINT goods_pkey PRIMARY KEY (id);


--
-- TOC entry 3210 (class 2606 OID 16536)
-- Name: inventory inventory_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.inventory
    ADD CONSTRAINT inventory_pkey PRIMARY KEY (id);


--
-- TOC entry 3198 (class 2606 OID 16424)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3208 (class 2606 OID 16506)
-- Name: units units_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.units
    ADD CONSTRAINT units_pkey PRIMARY KEY (id);


--
-- TOC entry 3200 (class 2606 OID 16433)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (id);


--
-- TOC entry 3202 (class 2606 OID 16438)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 3216 (class 2606 OID 16542)
-- Name: inventory fk9wlh8r1o63mqim0hi7nsghdb4; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.inventory
    ADD CONSTRAINT fk9wlh8r1o63mqim0hi7nsghdb4 FOREIGN KEY (unit_id) REFERENCES railway.units(id);


--
-- TOC entry 3215 (class 2606 OID 16537)
-- Name: inventory fkeksqvm5w6gqchurbih3dvo8vy; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.inventory
    ADD CONSTRAINT fkeksqvm5w6gqchurbih3dvo8vy FOREIGN KEY (goods_id) REFERENCES railway.goods(id);


--
-- TOC entry 3212 (class 2606 OID 16459)
-- Name: user_roles fkh6deu8wkydeisyod6sif716g2; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.user_roles
    ADD CONSTRAINT fkh6deu8wkydeisyod6sif716g2 FOREIGN KEY (user_id) REFERENCES railway.user_details(id);


--
-- TOC entry 3211 (class 2606 OID 16454)
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES railway.roles(id);


--
-- TOC entry 3214 (class 2606 OID 16512)
-- Name: goods fklfxlsm7xh8hxca9nkgd2bqlt7; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.goods
    ADD CONSTRAINT fklfxlsm7xh8hxca9nkgd2bqlt7 FOREIGN KEY (unit) REFERENCES railway.units(id);


--
-- TOC entry 3213 (class 2606 OID 16507)
-- Name: goods fko0nrxou86hx21xwavcbufhnmt; Type: FK CONSTRAINT; Schema: railway; Owner: postgres
--

ALTER TABLE ONLY railway.goods
    ADD CONSTRAINT fko0nrxou86hx21xwavcbufhnmt FOREIGN KEY (category) REFERENCES railway.category(id);


-- Completed on 2022-01-28 15:00:40

--
-- PostgreSQL database dump complete
--

