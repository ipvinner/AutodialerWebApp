--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.8
-- Dumped by pg_dump version 9.3.1
-- Started on 2016-01-08 13:53:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 187 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2003 (class 0 OID 0)
-- Dependencies: 187
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 186 (class 1259 OID 131161)
-- Name: call_result; Type: TABLE; Schema: public; Owner: autodialer1; Tablespace: 
--

CREATE TABLE call_result (
    id integer NOT NULL,
    datetime timestamp without time zone,
    result character varying,
    reason character varying,
    task_id integer,
    client_id integer
);


ALTER TABLE public.call_result OWNER TO autodialer1;

--
-- TOC entry 185 (class 1259 OID 131159)
-- Name: call_result_id_seq; Type: SEQUENCE; Schema: public; Owner: autodialer1
--

CREATE SEQUENCE call_result_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.call_result_id_seq OWNER TO autodialer1;

--
-- TOC entry 2004 (class 0 OID 0)
-- Dependencies: 185
-- Name: call_result_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: autodialer1
--

ALTER SEQUENCE call_result_id_seq OWNED BY call_result.id;


--
-- TOC entry 174 (class 1259 OID 82035)
-- Name: client; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE client (
    id integer NOT NULL,
    firstname character varying,
    lastname character varying,
    phone_number character varying,
    email character varying,
    clients_list_id integer NOT NULL
);


ALTER TABLE public.client OWNER TO "SYSDBA";

--
-- TOC entry 173 (class 1259 OID 82033)
-- Name: client_clients_list_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE client_clients_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_clients_list_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 173
-- Name: client_clients_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE client_clients_list_id_seq OWNED BY client.clients_list_id;


--
-- TOC entry 172 (class 1259 OID 82031)
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 172
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE client_id_seq OWNED BY client.id;


--
-- TOC entry 178 (class 1259 OID 82070)
-- Name: connection; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE connection (
    id integer NOT NULL,
    name character varying,
    host character varying,
    login character varying,
    password character varying,
    port integer
);


ALTER TABLE public.connection OWNER TO "SYSDBA";

--
-- TOC entry 177 (class 1259 OID 82068)
-- Name: connection_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE connection_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.connection_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 177
-- Name: connection_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE connection_id_seq OWNED BY connection.id;


--
-- TOC entry 171 (class 1259 OID 82010)
-- Name: list; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE list (
    id integer NOT NULL,
    name character varying,
    description character varying
);


ALTER TABLE public.list OWNER TO "SYSDBA";

--
-- TOC entry 170 (class 1259 OID 82008)
-- Name: list_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.list_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 170
-- Name: list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE list_id_seq OWNED BY list.id;


--
-- TOC entry 176 (class 1259 OID 82059)
-- Name: originate_param; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE originate_param (
    id integer NOT NULL,
    name character varying,
    context character varying,
    exten character varying,
    priority integer,
    async boolean,
    timeout integer,
    var1 character varying,
    var2 character varying,
    trunk character varying
);


ALTER TABLE public.originate_param OWNER TO "SYSDBA";

--
-- TOC entry 175 (class 1259 OID 82057)
-- Name: originate_param_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE originate_param_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.originate_param_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 175
-- Name: originate_param_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE originate_param_id_seq OWNED BY originate_param.id;


--
-- TOC entry 184 (class 1259 OID 82142)
-- Name: task; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE task (
    id integer NOT NULL,
    name character varying,
    client_list_id integer NOT NULL,
    active boolean,
    originate_param_id integer NOT NULL
);


ALTER TABLE public.task OWNER TO "SYSDBA";

--
-- TOC entry 182 (class 1259 OID 82138)
-- Name: task_client_list_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE task_client_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_client_list_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 182
-- Name: task_client_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE task_client_list_id_seq OWNED BY task.client_list_id;


--
-- TOC entry 181 (class 1259 OID 82136)
-- Name: task_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 181
-- Name: task_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE task_id_seq OWNED BY task.id;


--
-- TOC entry 183 (class 1259 OID 82140)
-- Name: task_originate_param_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE task_originate_param_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_originate_param_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 183
-- Name: task_originate_param_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE task_originate_param_id_seq OWNED BY task.originate_param_id;


--
-- TOC entry 180 (class 1259 OID 82081)
-- Name: users; Type: TABLE; Schema: public; Owner: SYSDBA; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    login character varying,
    password_hash character varying,
    role character varying
);


ALTER TABLE public.users OWNER TO "SYSDBA";

--
-- TOC entry 179 (class 1259 OID 82079)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: SYSDBA
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO "SYSDBA";

--
-- TOC entry 2013 (class 0 OID 0)
-- Dependencies: 179
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SYSDBA
--

ALTER SEQUENCE user_id_seq OWNED BY users.id;


--
-- TOC entry 1870 (class 2604 OID 131164)
-- Name: id; Type: DEFAULT; Schema: public; Owner: autodialer1
--

ALTER TABLE ONLY call_result ALTER COLUMN id SET DEFAULT nextval('call_result_id_seq'::regclass);


--
-- TOC entry 1862 (class 2604 OID 82038)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);


--
-- TOC entry 1863 (class 2604 OID 82039)
-- Name: clients_list_id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY client ALTER COLUMN clients_list_id SET DEFAULT nextval('client_clients_list_id_seq'::regclass);


--
-- TOC entry 1865 (class 2604 OID 82073)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY connection ALTER COLUMN id SET DEFAULT nextval('connection_id_seq'::regclass);


--
-- TOC entry 1861 (class 2604 OID 82013)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY list ALTER COLUMN id SET DEFAULT nextval('list_id_seq'::regclass);


--
-- TOC entry 1864 (class 2604 OID 82062)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY originate_param ALTER COLUMN id SET DEFAULT nextval('originate_param_id_seq'::regclass);


--
-- TOC entry 1867 (class 2604 OID 82145)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY task ALTER COLUMN id SET DEFAULT nextval('task_id_seq'::regclass);


--
-- TOC entry 1868 (class 2604 OID 82146)
-- Name: client_list_id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY task ALTER COLUMN client_list_id SET DEFAULT nextval('task_client_list_id_seq'::regclass);


--
-- TOC entry 1869 (class 2604 OID 82147)
-- Name: originate_param_id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY task ALTER COLUMN originate_param_id SET DEFAULT nextval('task_originate_param_id_seq'::regclass);


--
-- TOC entry 1866 (class 2604 OID 82084)
-- Name: id; Type: DEFAULT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1884 (class 2606 OID 131169)
-- Name: pkey_call_result_id; Type: CONSTRAINT; Schema: public; Owner: autodialer1; Tablespace: 
--

ALTER TABLE ONLY call_result
    ADD CONSTRAINT pkey_call_result_id PRIMARY KEY (id);


--
-- TOC entry 1874 (class 2606 OID 82044)
-- Name: pkey_client_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT pkey_client_id PRIMARY KEY (id);


--
-- TOC entry 1878 (class 2606 OID 82078)
-- Name: pkey_connection_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY connection
    ADD CONSTRAINT pkey_connection_id PRIMARY KEY (id);


--
-- TOC entry 1872 (class 2606 OID 82051)
-- Name: pkey_list_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY list
    ADD CONSTRAINT pkey_list_id PRIMARY KEY (id);


--
-- TOC entry 1876 (class 2606 OID 82067)
-- Name: pkey_orig_param_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY originate_param
    ADD CONSTRAINT pkey_orig_param_id PRIMARY KEY (id);


--
-- TOC entry 1882 (class 2606 OID 82152)
-- Name: pkey_task_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY task
    ADD CONSTRAINT pkey_task_id PRIMARY KEY (id);


--
-- TOC entry 1880 (class 2606 OID 82089)
-- Name: pkey_user_id; Type: CONSTRAINT; Schema: public; Owner: SYSDBA; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT pkey_user_id PRIMARY KEY (id);


--
-- TOC entry 1889 (class 2606 OID 147669)
-- Name: fk_client_id; Type: FK CONSTRAINT; Schema: public; Owner: autodialer1
--

ALTER TABLE ONLY call_result
    ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE;


--
-- TOC entry 1885 (class 2606 OID 100202)
-- Name: fk_list_id; Type: FK CONSTRAINT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY client
    ADD CONSTRAINT fk_list_id FOREIGN KEY (clients_list_id) REFERENCES list(id);


--
-- TOC entry 1886 (class 2606 OID 117286)
-- Name: fk_list_id; Type: FK CONSTRAINT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk_list_id FOREIGN KEY (client_list_id) REFERENCES list(id);


--
-- TOC entry 1887 (class 2606 OID 117291)
-- Name: fk_originate_param_id; Type: FK CONSTRAINT; Schema: public; Owner: SYSDBA
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk_originate_param_id FOREIGN KEY (originate_param_id) REFERENCES originate_param(id);


--
-- TOC entry 1888 (class 2606 OID 147664)
-- Name: fk_task_id; Type: FK CONSTRAINT; Schema: public; Owner: autodialer1
--

ALTER TABLE ONLY call_result
    ADD CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE;


--
-- TOC entry 2002 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-01-08 13:53:35

--
-- PostgreSQL database dump complete
--

