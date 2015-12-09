DROP TABLE IF EXISTS call_result;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS connection;
DROP TABLE IF EXISTS list;
DROP TABLE IF EXISTS originate_param;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS user;
DROP SEQUENCE IF EXISTS call_result_id_seq;
DROP SEQUENCE IF EXISTS client_clients_list_id_seq;
DROP SEQUENCE IF EXISTS client_id_seq;
DROP SEQUENCE IF EXISTS connection_id_seq;
DROP SEQUENCE IF EXISTS list_id_seq;
DROP SEQUENCE IF EXISTS originate_param_id_seq;
DROP SEQUENCE IF EXISTS task_client_list_id_seq;
DROP SEQUENCE IF EXISTS task_id_seq;
DROP SEQUENCE IF EXISTS task_originate_param_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;

CREATE TABLE call_result (
  id integer NOT NULL,
  datetime timestamp without time zone,
  result character varying,
  reason character varying,
  task_id integer,
  client_id integer
);

CREATE SEQUENCE call_result_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE client (
  id integer NOT NULL,
  firstname character varying,
  lastname character varying,
  phone_number character varying,
  email character varying,
  clients_list_id integer NOT NULL
);

CREATE SEQUENCE client_clients_list_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE client_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE connection (
  id integer NOT NULL,
  name character varying,
  host character varying,
  login character varying,
  password character varying,
  port integer
);

CREATE SEQUENCE connection_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE list (
  id integer NOT NULL,
  name character varying,
  description character varying
);

CREATE SEQUENCE list_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

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

CREATE SEQUENCE originate_param_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE task (
  id integer NOT NULL,
  name character varying,
  client_list_id integer NOT NULL,
  active boolean,
  originate_param_id integer NOT NULL
);

CREATE SEQUENCE task_client_list_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE task_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE task_originate_param_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE "user" (
  id integer NOT NULL,
  login character varying,
  password_hash character varying,
  user_role character varying
);

CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

ALTER TABLE ONLY call_result ALTER COLUMN id SET DEFAULT nextval('call_result_id_seq'::regclass);
ALTER TABLE ONLY client ALTER COLUMN id SET DEFAULT nextval('client_id_seq'::regclass);
ALTER TABLE ONLY client ALTER COLUMN clients_list_id SET DEFAULT nextval('client_clients_list_id_seq'::regclass);
ALTER TABLE ONLY connection ALTER COLUMN id SET DEFAULT nextval('connection_id_seq'::regclass);
ALTER TABLE ONLY list ALTER COLUMN id SET DEFAULT nextval('list_id_seq'::regclass);
ALTER TABLE ONLY originate_param ALTER COLUMN id SET DEFAULT nextval('originate_param_id_seq'::regclass);
ALTER TABLE ONLY task ALTER COLUMN id SET DEFAULT nextval('task_id_seq'::regclass);
ALTER TABLE ONLY task ALTER COLUMN client_list_id SET DEFAULT nextval('task_client_list_id_seq'::regclass);
ALTER TABLE ONLY task ALTER COLUMN originate_param_id SET DEFAULT nextval('task_originate_param_id_seq'::regclass);
ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);
ALTER TABLE ONLY call_result
ADD CONSTRAINT pkey_call_result_id PRIMARY KEY (id);
ALTER TABLE ONLY client
ADD CONSTRAINT pkey_client_id PRIMARY KEY (id);
ALTER TABLE ONLY connection
ADD CONSTRAINT pkey_connection_id PRIMARY KEY (id);
ALTER TABLE ONLY list
ADD CONSTRAINT pkey_list_id PRIMARY KEY (id);
ALTER TABLE ONLY originate_param
ADD CONSTRAINT pkey_orig_param_id PRIMARY KEY (id);
ALTER TABLE ONLY task
ADD CONSTRAINT pkey_task_id PRIMARY KEY (id);
ALTER TABLE ONLY "user"
ADD CONSTRAINT pkey_user_id PRIMARY KEY (id);
ALTER TABLE ONLY call_result
ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client(id);
ALTER TABLE ONLY client
ADD CONSTRAINT fk_list_id FOREIGN KEY (clients_list_id) REFERENCES list(id);
ALTER TABLE ONLY task
ADD CONSTRAINT fk_list_id FOREIGN KEY (client_list_id) REFERENCES list(id);
ALTER TABLE ONLY task
ADD CONSTRAINT fk_originate_param_id FOREIGN KEY (originate_param_id) REFERENCES originate_param(id);
ALTER TABLE ONLY call_result
ADD CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES task(id);