DROP TABLE IF EXISTS public.ability_levels;
DROP TABLE IF EXISTS public.saving_throws;
DROP TABLE IF EXISTS public.abilities;
DROP TABLE IF EXISTS public.skills;
DROP TABLE IF EXISTS public.action;
DROP TABLE IF EXISTS public.monster;
DROP TABLE IF EXISTS public.users;
DROP SEQUENCE IF EXISTS public.user_id_seq;
DROP SEQUENCE IF EXISTS public.action_id_seq;


CREATE SEQUENCE public.user_id_seq START 100;
ALTER SEQUENCE public.user_id_seq OWNER TO postgres;

CREATE SEQUENCE public.action_id_seq START 100;
ALTER SEQUENCE public.action_id_seq OWNER TO postgres;

CREATE TABLE public.users
(
	user_id smallint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
	username text COLLATE pg_catalog."default" NOT NULL,
    admin boolean DEFAULT false,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.users OWNER to postgres;
INSERT INTO users(username, admin) VALUES('Dungeon Master', true);

CREATE TABLE public.monster
(
    name text COLLATE pg_catalog."default" NOT NULL,
	created_by smallint NOT NULL,
    size text COLLATE pg_catalog."default",
    type text COLLATE pg_catalog."default",
    subtype text COLLATE pg_catalog."default",
    alignment text COLLATE pg_catalog."default",
    armor_class smallint,
    hit_points smallint,
    hit_dice text COLLATE pg_catalog."default",
    speed text COLLATE pg_catalog."default",
    senses text COLLATE pg_catalog."default",
    languages text COLLATE pg_catalog."default",
    challenge_rating numeric(6,4),
    damage_vulnerabilities character varying(12)[] COLLATE pg_catalog."default",
    damage_resistances character varying(12)[] COLLATE pg_catalog."default",
    damage_immunities character varying(12)[] COLLATE pg_catalog."default",
    condition_immunities character varying(14)[] COLLATE pg_catalog."default",
    CONSTRAINT monster_pkey PRIMARY KEY (name),
    CONSTRAINT monster_fkey FOREIGN KEY (created_by)
	REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.monster OWNER to postgres;

CREATE TABLE public.abilities
(
    strength smallint,
    dexterity smallint,
    constitution smallint,
    wisdom smallint,
    intelligence smallint,
    charisma smallint,
    name text NOT NULL,
    CONSTRAINT abilities_pkey PRIMARY KEY (name),
    CONSTRAINT abilities_fkey FOREIGN KEY (name)
        REFERENCES public.monster (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.abilities OWNER to postgres;

CREATE TABLE public.ability_levels() INHERITS (public.abilities)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.ability_levels OWNER to postgres;

CREATE TABLE public.saving_throws() INHERITS (public.abilities)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.saving_throws OWNER to postgres;

CREATE TABLE public.skills
(
    name text NOT NULL,
    acrobatics smallint,
    animal_handling smallint,
    arcana smallint,
    athletics smallint,
    deception smallint,
    history smallint,
    insight smallint,
    intimidation smallint,
    investigation smallint,
    medicine smallint,
    nature smallint,
    perception smallint,
    performance smallint,
    persuasion smallint,
    religion smallint,
    slight_of_hand smallint,
    stealth smallint,
    survival smallint,
    CONSTRAINT skills_pkey PRIMARY KEY (name),
    CONSTRAINT skills_fkey FOREIGN KEY (name)
        REFERENCES public.monster (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.skills OWNER to postgres;

CREATE TABLE public.action
(
    action_id smallint NOT NULL DEFAULT nextval('action_id_seq'::regclass),
    monster_name text NOT NULL,
    action_type smallint NOT NULL,
	name text COLLATE pg_catalog."default",
    damage_bonus smallint,
    damage_dice text COLLATE pg_catalog."default",
    attack_bonus smallint,
    description text COLLATE pg_catalog."default",
    CONSTRAINT action_pkey PRIMARY KEY (name, action_id),
    CONSTRAINT action_fkey FOREIGN KEY (monster_name)
        REFERENCES public.monster (name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;
ALTER TABLE public.action OWNER to postgres;