DROP DATABASE IF EXISTS dndtools;
CREATE DATABASE dndtools;
USE dndtools;
DROP TABLE IF EXISTS ability_levels;
DROP TABLE IF EXISTS saving_throws;
DROP TABLE IF EXISTS abilities;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS spell;

CREATE TABLE users
(
user_id smallint AUTO_INCREMENT PRIMARY KEY,
username text NOT NULL,
admin boolean DEFAULT false
);

CREATE TABLE monster
(
    name varchar(255) NOT NULL PRIMARY KEY,
	created_by smallint NOT NULL,
    size text,
    type text,
    subtype text,
    alignment text,
    armor_class smallint,
    hit_points smallint,
    hit_dice text,
    speed text,
    senses text,
    languages text,
    challenge_rating numeric(6,4),
damage_vulnerabilities text,
damage_resistances text,
damage_immunities text,
condition_immunities text,
FOREIGN KEY (created_by)
REFERENCES users (user_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

CREATE TABLE ability_levels
(
    strength smallint,
    dexterity smallint,
    constitution smallint,
    wisdom smallint,
    intelligence smallint,
    charisma smallint,
    name varchar(255) NOT NULL PRIMARY KEY,
    FOREIGN KEY (name)
        REFERENCES monster (name)
  ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE saving_throws
(
    strength smallint,
    dexterity smallint,
    constitution smallint,
    wisdom smallint,
    intelligence smallint,
    charisma smallint,
    name varchar(255) NOT NULL PRIMARY KEY,
    FOREIGN KEY (name)
        REFERENCES monster (name)
  ON UPDATE CASCADE
ON DELETE CASCADE
);


CREATE TABLE skills
(
    name varchar(255) NOT NULL PRIMARY KEY,
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
    FOREIGN KEY (name)
        REFERENCES monster (name)
  ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE action
(
    action_id smallint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    monster_name varchar(255) NOT NULL,
action_type smallint NOT NULL,
name text,
damage_bonus smallint,
damage_dice text,
attack_bonus smallint,
description text,
FOREIGN KEY (monster_name)
REFERENCES monster (name)
  ON UPDATE CASCADE
ON DELETE CASCADE
);

CREATE TABLE spell
(
spell_id smallint NOT NULL AUTO_INCREMENT PRIMARY KEY,
name text NOT NULL,
description text NOT NULL,
page text,
spell_range text,
components text,
materials text,
ritual boolean DEFAULT false,
duration text,
concentration boolean NOT NULL DEFAULT false,
casting_time text NOT NULL,
level smallint NOT NULL,
school text,
classes text,
higher_level text
);