--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-01-28 15:00:53

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
-- TOC entry 3357 (class 0 OID 16479)
-- Dependencies: 216
-- Data for Name: category; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.category (id, name) FROM stdin;
1	Category1
2	Category2
3	Category3
\.


--
-- TOC entry 3360 (class 0 OID 16502)
-- Dependencies: 219
-- Data for Name: units; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.units (id, name) FROM stdin;
1	Packs
2	KGs
3	RSs
\.


--
-- TOC entry 3359 (class 0 OID 16485)
-- Dependencies: 218
-- Data for Name: goods; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.goods (id, date, description, is_deleted, min_quantity, name, total_quantity, category, unit) FROM stdin;
4	2021-12-30	nope	f	10	test2	0	1	1
2	2021-12-31	abc	f	11	test5	0	2	2
6	2021-12-30	note	t	8	abc	0	1	1
7	2021-12-30	ddd	f	10	cde	0	2	2
3	2021-12-30	nope	f	10	test1	1975	1	1
8	2021-12-31	2121	f	10	ds	1000	1	1
9	2022-01-21	gfgfgfgfg	f	10	bbbbbbbbbbbbb	13000	1	1
\.


--
-- TOC entry 3362 (class 0 OID 16529)
-- Dependencies: 221
-- Data for Name: inventory; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.inventory (id, date, description, handover_to, inventory_type, quantity, shed_store_no, supervisor_name, "time", goods_id, unit_id) FROM stdin;
1	2021-12-30	gfg	fdfd	GOODS_IN	100	gf	fdfd	15:14:58	2	2
2	2021-12-30	gfg	fdfd	GOODS_OUT	100	gf	fdfd	15:15:44	2	2
3	2021-12-30	gfg	fdfd	GOODS_IN	100	gf	fdfd	15:18:03	2	2
4	2021-12-30	gfg	fdfd	GOODS_IN	1000	gf	fdfd	15:20:34	3	1
5	2021-12-30	gfg	fdfd	GOODS_IN	1000	gf	fdfd	15:49:42	3	1
6	2021-12-30	gfg	fdfd	GOODS_OUT	100	gf	fdfd	17:01:50	2	2
7	2021-12-31	dsd	dssd	GOODS_OUT	10	dsds	dsd	10:42:50	3	1
8	2021-12-31			GOODS_OUT	1			10:43:41	3	1
9	2021-12-31			GOODS_OUT	1			10:43:47	3	1
10	2021-12-31			GOODS_OUT	1			10:43:52	3	1
11	2021-12-31			GOODS_OUT	1			10:43:57	3	1
12	2021-12-31			GOODS_OUT	1			10:44:10	3	1
13	2021-12-31			GOODS_OUT	10			10:44:17	3	1
14	2021-12-31	gfg	gfg	GOODS_IN	1000	gfg	gfg	12:13:09	8	1
15	2022-01-21			GOODS_IN	10000			20:57:31	9	1
16	2022-01-21			GOODS_OUT	1000			20:58:38	9	1
17	2022-01-21			GOODS_IN	4000			20:58:58	9	1
\.


--
-- TOC entry 3353 (class 0 OID 16419)
-- Dependencies: 212
-- Data for Name: roles; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.roles (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_MODERATOR
3	ROLE_ADMIN
\.


--
-- TOC entry 3355 (class 0 OID 16426)
-- Dependencies: 214
-- Data for Name: user_details; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.user_details (id, email, password, username) FROM stdin;
1	admin@icta.lk	$2a$10$YtAFyankLB/B/KTtj3SDgeH4ihDMIDDYiZDx9mKhFFM1XUvanEnOG	admin
\.


--
-- TOC entry 3356 (class 0 OID 16434)
-- Dependencies: 215
-- Data for Name: user_roles; Type: TABLE DATA; Schema: railway; Owner: postgres
--

COPY railway.user_roles (user_id, role_id) FROM stdin;
1	3
\.


--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 217
-- Name: goods_id_seq; Type: SEQUENCE SET; Schema: railway; Owner: postgres
--

SELECT pg_catalog.setval('railway.goods_id_seq', 9, true);


--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 220
-- Name: inventory_id_seq; Type: SEQUENCE SET; Schema: railway; Owner: postgres
--

SELECT pg_catalog.setval('railway.inventory_id_seq', 17, true);


--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 211
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: railway; Owner: postgres
--

SELECT pg_catalog.setval('railway.roles_id_seq', 3, true);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 213
-- Name: user_details_id_seq; Type: SEQUENCE SET; Schema: railway; Owner: postgres
--

SELECT pg_catalog.setval('railway.user_details_id_seq', 1, true);


-- Completed on 2022-01-28 15:00:54

--
-- PostgreSQL database dump complete
--

