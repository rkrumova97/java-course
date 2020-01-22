DROP DATABASE IF EXISTS restaurant;
DROP TABLE IF EXISTS category, order, "user", offer,restaurant_category, restaurant, user_category;
CREATE DATABASE restaurant  IF NOT EXISTS
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Bulgarian_Bulgaria.1251'
    LC_CTYPE = 'Bulgarian_Bulgaria.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE public.category IF NOT EXISTS
(
    id bigint NOT NULL,
    category character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT restaurant_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.category
    OWNER to postgres;

CREATE TABLE public.offer  IF NOT EXISTS
(
    id bigint NOT NULL,
    text character varying COLLATE pg_catalog."default",
    price bigint,
    category bigint,
    restaurant bigint,
    is_deleted boolean DEFAULT false,
    CONSTRAINT offer_pkey PRIMARY KEY (id),
    CONSTRAINT offer_category_fkey FOREIGN KEY (category)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT offer_restaurant_fkey FOREIGN KEY (restaurant)
        REFERENCES public.restaurant (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.offer
    OWNER to postgres;

CREATE TABLE public."order"  IF NOT EXISTS
(
    id bigint NOT NULL,
    person bigint,
    offer bigint,
    "time" time without time zone,
    is_deleted boolean DEFAULT false,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT person_id FOREIGN KEY (person)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."order"
    OWNER to postgres;

CREATE TABLE public.restaurant  IF NOT EXISTS
(
    id bigint NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default",
    is_deleted boolean DEFAULT false,
    CONSTRAINT restaurant_pkey1 PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.restaurant
    OWNER to postgres;

CREATE TABLE public.restaurant_category  IF NOT EXISTS
(
    restaurant_id bigint NOT NULL,
    category_id bigint NOT NULL,
    CONSTRAINT restaurant_category_pkey PRIMARY KEY (restaurant_id, category_id),
    CONSTRAINT restaurant_category_category_id_fkey FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT restaurant_category_restaurant_id_fkey FOREIGN KEY (restaurant_id)
        REFERENCES public.restaurant (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.restaurant_category
    OWNER to postgres;

CREATE TABLE public."user"  IF NOT EXISTS
(
    id bigint NOT NULL,
    email character varying COLLATE pg_catalog."default" NOT NULL,
    password character varying COLLATE pg_catalog."default" NOT NULL,
    token character varying COLLATE pg_catalog."default",
    first_name character varying COLLATE pg_catalog."default" NOT NULL,
    last_name character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying COLLATE pg_catalog."default" NOT NULL,
    username character varying COLLATE pg_catalog."default" NOT NULL,
    restaurant bigint,
    role character varying COLLATE pg_catalog."default",
    is_deleted boolean DEFAULT false,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_restaurant_fkey FOREIGN KEY (restaurant)
        REFERENCES public.restaurant (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to postgres;

    CREATE TABLE public.user_category  IF NOT EXISTS
    (
        user_id bigint NOT NULL,
        category_id bigint NOT NULL,
        CONSTRAINT user_category_pkey PRIMARY KEY (user_id, category_id),
        CONSTRAINT user_category_category_id_fkey FOREIGN KEY (category_id)
            REFERENCES public.category (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    )
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

    ALTER TABLE public.user_category
        OWNER to postgres;