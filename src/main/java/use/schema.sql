--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2

-- Started on 2025-02-21 21:04:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS university;
--
-- TOC entry 3447 (class 1262 OID 24737)
-- Name: university; Type: DATABASE; Schema: -; Owner: juandavyc
--

CREATE DATABASE university WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE university OWNER TO juandavyc;

\connect university

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 883 (class 1247 OID 57470)
-- Name: role; Type: TYPE; Schema: public; Owner: juandavyc
--

CREATE TYPE public.role AS ENUM (
    'STUDENT',
    'PROFESSOR'
);


ALTER TYPE public.role OWNER TO juandavyc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 24780)
-- Name: classroom; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.classroom (
    id bigint NOT NULL,
    room integer
);


ALTER TABLE public.classroom OWNER TO juandavyc;

--
-- TOC entry 223 (class 1259 OID 24779)
-- Name: classroom_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

CREATE SEQUENCE public.classroom_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.classroom_id_seq OWNER TO juandavyc;

--
-- TOC entry 3448 (class 0 OID 0)
-- Dependencies: 223
-- Name: classroom_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: juandavyc
--

ALTER SEQUENCE public.classroom_id_seq OWNED BY public.classroom.id;


--
-- TOC entry 227 (class 1259 OID 24792)
-- Name: course; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.course (
    id bigint NOT NULL,
    name character varying(255),
    "time" boolean,
    id_classroom bigint
);


ALTER TABLE public.course OWNER TO juandavyc;

--
-- TOC entry 3449 (class 0 OID 0)
-- Dependencies: 227
-- Name: COLUMN course."time"; Type: COMMENT; Schema: public; Owner: juandavyc
--

COMMENT ON COLUMN public.course."time" IS 'AM true,PM false';


--
-- TOC entry 226 (class 1259 OID 24791)
-- Name: course_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

CREATE SEQUENCE public.course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.course_id_seq OWNER TO juandavyc;

--
-- TOC entry 3450 (class 0 OID 0)
-- Dependencies: 226
-- Name: course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: juandavyc
--

ALTER SEQUENCE public.course_id_seq OWNED BY public.course.id;


--
-- TOC entry 220 (class 1259 OID 24762)
-- Name: document_type; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.document_type (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.document_type OWNER TO juandavyc;

--
-- TOC entry 219 (class 1259 OID 24761)
-- Name: document_type_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

CREATE SEQUENCE public.document_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.document_type_id_seq OWNER TO juandavyc;

--
-- TOC entry 3451 (class 0 OID 0)
-- Dependencies: 219
-- Name: document_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: juandavyc
--

ALTER SEQUENCE public.document_type_id_seq OWNED BY public.document_type.id;


--
-- TOC entry 218 (class 1259 OID 24751)
-- Name: person; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.person (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    document character varying(20) NOT NULL,
    id_document_type bigint,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    version integer DEFAULT 0,
    role character varying(255) DEFAULT 'STUDENT'::public.role
);


ALTER TABLE public.person OWNER TO juandavyc;

--
-- TOC entry 217 (class 1259 OID 24750)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.person_id_seq OWNER TO juandavyc;

--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 217
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: juandavyc
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- TOC entry 222 (class 1259 OID 24774)
-- Name: professor; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.professor (
    id bigint NOT NULL,
    salary numeric(38,0),
    id_person bigint
);


ALTER TABLE public.professor OWNER TO juandavyc;

--
-- TOC entry 225 (class 1259 OID 24786)
-- Name: professor_course; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.professor_course (
    id_professor bigint NOT NULL,
    id_course bigint NOT NULL
);


ALTER TABLE public.professor_course OWNER TO juandavyc;

--
-- TOC entry 230 (class 1259 OID 65655)
-- Name: professor_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

ALTER TABLE public.professor ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.professor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 24768)
-- Name: student; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    scholarship boolean DEFAULT false,
    id_person bigint
);


ALTER TABLE public.student OWNER TO juandavyc;

--
-- TOC entry 228 (class 1259 OID 24798)
-- Name: student_course; Type: TABLE; Schema: public; Owner: juandavyc
--

CREATE TABLE public.student_course (
    id_student bigint NOT NULL,
    id_course bigint NOT NULL
);


ALTER TABLE public.student_course OWNER TO juandavyc;

--
-- TOC entry 229 (class 1259 OID 57463)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: juandavyc
--

ALTER TABLE public.student ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3251 (class 2604 OID 32871)
-- Name: classroom id; Type: DEFAULT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.classroom ALTER COLUMN id SET DEFAULT nextval('public.classroom_id_seq'::regclass);


--
-- TOC entry 3252 (class 2604 OID 32883)
-- Name: course id; Type: DEFAULT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.course ALTER COLUMN id SET DEFAULT nextval('public.course_id_seq'::regclass);


--
-- TOC entry 3249 (class 2604 OID 24843)
-- Name: document_type id; Type: DEFAULT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.document_type ALTER COLUMN id SET DEFAULT nextval('public.document_type_id_seq'::regclass);


--
-- TOC entry 3246 (class 2604 OID 24855)
-- Name: person id; Type: DEFAULT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- TOC entry 3435 (class 0 OID 24780)
-- Dependencies: 224
-- Data for Name: classroom; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.classroom (id, room) VALUES (31, 101);
INSERT INTO public.classroom (id, room) VALUES (32, 104);
INSERT INTO public.classroom (id, room) VALUES (34, 102);
INSERT INTO public.classroom (id, room) VALUES (44, 105);
INSERT INTO public.classroom (id, room) VALUES (45, 106);
INSERT INTO public.classroom (id, room) VALUES (46, 107);
INSERT INTO public.classroom (id, room) VALUES (47, 201);
INSERT INTO public.classroom (id, room) VALUES (48, 202);
INSERT INTO public.classroom (id, room) VALUES (51, 301);
INSERT INTO public.classroom (id, room) VALUES (50, 302);
INSERT INTO public.classroom (id, room) VALUES (52, 303);
INSERT INTO public.classroom (id, room) VALUES (53, 305);


--
-- TOC entry 3438 (class 0 OID 24792)
-- Dependencies: 227
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.course (id, name, "time", id_classroom) VALUES (4, 'Sistemas 3', false, 32);
INSERT INTO public.course (id, name, "time", id_classroom) VALUES (47, 'Matematicas 1', false, 34);
INSERT INTO public.course (id, name, "time", id_classroom) VALUES (2, 'Commercio 1', true, 31);
INSERT INTO public.course (id, name, "time", id_classroom) VALUES (48, 'Sistemas 2', true, 32);
INSERT INTO public.course (id, name, "time", id_classroom) VALUES (5, 'Matematicas 2', true, 53);
INSERT INTO public.course (id, name, "time", id_classroom) VALUES (49, 'Matematicas 2', false, 53);


--
-- TOC entry 3431 (class 0 OID 24762)
-- Dependencies: 220
-- Data for Name: document_type; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.document_type (id, name) VALUES (1, 'Cedula');
INSERT INTO public.document_type (id, name) VALUES (3, 'Nit');
INSERT INTO public.document_type (id, name) VALUES (2, 'Cedula Extranjera');


--
-- TOC entry 3429 (class 0 OID 24751)
-- Dependencies: 218
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (12, 'MARTIN GAMEZ', '1110000007', 2, '2025-02-12 22:15:12.184058', NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (13, 'VALENTINA JAIMEZ', '1110000008', 1, '2025-02-12 22:15:12.187028', NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (11, 'PAULA RAMIREZ', '1110000006', 1, '2025-02-12 22:15:12.157086', NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (15, 'VALENTINA JAIMEZ D', '1110000009', NULL, '2025-02-12 22:15:12.187028', NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (2, 'JOSE ALEJANDRO YARA CIFUENTES', '1110000001', 2, NULL, NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (1, 'JUAN DAVID YARA CIFUENTES', '1110563467', 1, NULL, NULL, 0, 'STUDENT');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (23, 'Miranda Keyes', '1110000018', 3, '2025-02-19 11:28:54.822374', '2025-02-19 11:29:55.26948', 2, 'PROFESSOR');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (6, 'CAMILA LLORENTE RAMIREZ', '1110000004', 1, '2025-02-12 22:07:50.356179', NULL, 0, 'PROFESSOR');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (5, 'LINA YARA CIFUENTES', '1110000003', 2, '2025-02-12 21:46:02.338544', '2025-02-22 22:01:01.242098', 0, 'PROFESSOR');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (40, 'Mika mercado', '1110000025', 3, '2025-02-20 12:24:25.70501', '2025-02-20 12:24:25.70501', 0, 'PROFESSOR');
INSERT INTO public.person (id, name, document, id_document_type, created_at, updated_at, version, role) VALUES (41, 'Mika mercado', '1110000024', 3, '2025-02-21 20:44:45.232457', '2025-02-21 20:44:45.232457', 0, 'STUDENT');


--
-- TOC entry 3433 (class 0 OID 24774)
-- Dependencies: 222
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.professor (id, salary, id_person) VALUES (7, 2520000, 23);
INSERT INTO public.professor (id, salary, id_person) VALUES (8, 5600000, 5);
INSERT INTO public.professor (id, salary, id_person) VALUES (9, 1200000, 6);
INSERT INTO public.professor (id, salary, id_person) VALUES (13, 26009, 40);


--
-- TOC entry 3436 (class 0 OID 24786)
-- Dependencies: 225
-- Data for Name: professor_course; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.professor_course (id_professor, id_course) VALUES (7, 2);
INSERT INTO public.professor_course (id_professor, id_course) VALUES (7, 49);


--
-- TOC entry 3432 (class 0 OID 24768)
-- Dependencies: 221
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.student (id, scholarship, id_person) OVERRIDING SYSTEM VALUE VALUES (1, false, 1);
INSERT INTO public.student (id, scholarship, id_person) OVERRIDING SYSTEM VALUE VALUES (2, false, 2);
INSERT INTO public.student (id, scholarship, id_person) OVERRIDING SYSTEM VALUE VALUES (9, true, 41);


--
-- TOC entry 3439 (class 0 OID 24798)
-- Dependencies: 228
-- Data for Name: student_course; Type: TABLE DATA; Schema: public; Owner: juandavyc
--

INSERT INTO public.student_course (id_student, id_course) VALUES (1, 2);
INSERT INTO public.student_course (id_student, id_course) VALUES (1, 4);
INSERT INTO public.student_course (id_student, id_course) VALUES (9, 48);


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 223
-- Name: classroom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.classroom_id_seq', 54, true);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 226
-- Name: course_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.course_id_seq', 50, true);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 219
-- Name: document_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.document_type_id_seq', 15, true);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 217
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.person_id_seq', 41, true);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 230
-- Name: professor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.professor_id_seq', 13, true);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 229
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: juandavyc
--

SELECT pg_catalog.setval('public.student_id_seq', 9, true);


--
-- TOC entry 3266 (class 2606 OID 32873)
-- Name: classroom pk_classroom; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.classroom
    ADD CONSTRAINT pk_classroom PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 32885)
-- Name: course pk_course; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT pk_course PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 24845)
-- Name: document_type pk_document_type; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.document_type
    ADD CONSTRAINT pk_document_type PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 24857)
-- Name: person pk_person; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT pk_person PRIMARY KEY (id);


--
-- TOC entry 3264 (class 2606 OID 65663)
-- Name: professor pk_professor; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT pk_professor PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 32974)
-- Name: professor_course pk_professor_classroom; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.professor_course
    ADD CONSTRAINT pk_professor_classroom PRIMARY KEY (id_professor, id_course);


--
-- TOC entry 3262 (class 2606 OID 32913)
-- Name: student pk_student; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT pk_student PRIMARY KEY (id);


--
-- TOC entry 3274 (class 2606 OID 32940)
-- Name: student_course pk_student_course; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT pk_student_course PRIMARY KEY (id_student, id_course);


--
-- TOC entry 3256 (class 2606 OID 24760)
-- Name: person uq_document; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uq_document UNIQUE (document);


--
-- TOC entry 3260 (class 2606 OID 41066)
-- Name: document_type uq_dt_name; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.document_type
    ADD CONSTRAINT uq_dt_name UNIQUE (name);


--
-- TOC entry 3268 (class 2606 OID 32961)
-- Name: classroom uq_room; Type: CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.classroom
    ADD CONSTRAINT uq_room UNIQUE (room);


--
-- TOC entry 3280 (class 2606 OID 32900)
-- Name: course fk_classroom; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT fk_classroom FOREIGN KEY (id_classroom) REFERENCES public.classroom(id) ON DELETE SET NULL;


--
-- TOC entry 3278 (class 2606 OID 32964)
-- Name: professor_course fk_course; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.professor_course
    ADD CONSTRAINT fk_course FOREIGN KEY (id_course) REFERENCES public.course(id) ON DELETE CASCADE;


--
-- TOC entry 3281 (class 2606 OID 32930)
-- Name: student_course fk_couse; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT fk_couse FOREIGN KEY (id_course) REFERENCES public.course(id) ON DELETE CASCADE;


--
-- TOC entry 3275 (class 2606 OID 24876)
-- Name: person fk_document_type; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT fk_document_type FOREIGN KEY (id_document_type) REFERENCES public.document_type(id) ON DELETE SET NULL;


--
-- TOC entry 3276 (class 2606 OID 57492)
-- Name: student fk_person; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES public.person(id) ON DELETE CASCADE;


--
-- TOC entry 3277 (class 2606 OID 65688)
-- Name: professor fk_person; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT fk_person FOREIGN KEY (id_person) REFERENCES public.person(id) NOT VALID;


--
-- TOC entry 3279 (class 2606 OID 65669)
-- Name: professor_course fk_professor; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.professor_course
    ADD CONSTRAINT fk_professor FOREIGN KEY (id_professor) REFERENCES public.professor(id) ON DELETE CASCADE;


--
-- TOC entry 3282 (class 2606 OID 32941)
-- Name: student_course fk_student; Type: FK CONSTRAINT; Schema: public; Owner: juandavyc
--

ALTER TABLE ONLY public.student_course
    ADD CONSTRAINT fk_student FOREIGN KEY (id_student) REFERENCES public.student(id) ON DELETE CASCADE;


-- Completed on 2025-02-21 21:04:06

--
-- PostgreSQL database dump complete
--

