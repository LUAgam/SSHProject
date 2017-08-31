--创建数据库demo并创建两张表
CREATE DATABASE demo;
CREATE TABLE IF NOT EXISTS public.tbl_user_0
(
  id bigint NOT NULL,
  nname character varying(255),
  password character varying(255),
  username character varying(255),
  version integer NOT NULL,
  CONSTRAINT tbl_user_0_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.tbl_user_1
(
  id bigint NOT NULL,
  nname character varying(255),
  password character varying(255),
  username character varying(255),
  version integer NOT NULL,
  CONSTRAINT tbl_user_1_pkey PRIMARY KEY (id)
);
--创建数据库demo1并创建两张表
CREATE DATABASE demo1;
CREATE TABLE IF NOT EXISTS public.tbl_user_0
(
  id bigint NOT NULL,
  nname character varying(255),
  password character varying(255),
  username character varying(255),
  version integer NOT NULL,
  CONSTRAINT tbl_user_0_pkey PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS public.tbl_user_1
(
  id bigint NOT NULL,
  nname character varying(255),
  password character varying(255),
  username character varying(255),
  version integer NOT NULL,
  CONSTRAINT tbl_user_1_pkey PRIMARY KEY (id)
);