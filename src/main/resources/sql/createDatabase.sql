DROP DATABASE IF EXISTS dndtools;
CREATE DATABASE dndtools;
USE dndtools;
DROP TABLE IF EXISTS ability_levels;
DROP TABLE IF EXISTS saving_throws;
DROP TABLE IF EXISTS abilities;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS spell;

CREATE TABLE USERS
(
  user_id SMALLINT AUTO_INCREMENT PRIMARY KEY,
  username text NOT NULL,
  ADMIN BOOLEAN DEFAULT FALSE,
  first_name text NOT NULL,
  last_name text NOT NULL
);

CREATE TABLE monster
(
  name VARCHAR(255) NOT NULL PRIMARY KEY,
  created_by SMALLINT NOT NULL,
  size text,
  TYPE text,
  subtype text,
  alignment text,
  armor_class SMALLINT,
  hit_points SMALLINT,
  hit_dice text,
  speed text,
  senses text,
  languages text,
  challenge_rating numeric(6,4),
  damage_vulnerabilities text,
  damage_resistances text,
  damage_immunities text,
  condition_immunities text,
  FOREIGN KEY(created_by)
  REFERENCES USERS(user_id)
  ON DELETE CASCADE ON UPDATE CASCADE );

CREATE TABLE ability_levels
(
  strength SMALLINT,
  dexterity SMALLINT,
  constitution SMALLINT,
  wisdom SMALLINT,
  intelligence SMALLINT,
  charisma SMALLINT,
  name VARCHAR(255) NOT NULL PRIMARY KEY,
  FOREIGN KEY (name)
      REFERENCES monster(name)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE saving_throws
(
  strength SMALLINT,
  dexterity SMALLINT,
  constitution SMALLINT,
  wisdom SMALLINT,
  intelligence SMALLINT,
  charisma SMALLINT,
  name VARCHAR(255) NOT NULL PRIMARY KEY,
  FOREIGN KEY (name)
      REFERENCES monster(name)
  ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE skills
(
  name VARCHAR(255) NOT NULL PRIMARY KEY,
  acrobatics SMALLINT,
  animal_handling SMALLINT,
  arcana SMALLINT,
  athletics SMALLINT,
  deception SMALLINT,
  history SMALLINT,
  insight SMALLINT,
  intimidation SMALLINT,
  investigation SMALLINT,
  medicine SMALLINT,
  nature SMALLINT,
  perception SMALLINT,
  performance SMALLINT,
  persuasion SMALLINT,
  religion SMALLINT,
  slight_of_hand SMALLINT,
  stealth SMALLINT,
  survival SMALLINT,
  FOREIGN KEY (name)
      REFERENCES monster(name)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE action
(
  action_id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  monster_name VARCHAR(255) NOT NULL,
  action_type SMALLINT NOT NULL,
  name text,
  damage_bonus SMALLINT,
  damage_dice text,
  attack_bonus SMALLINT,
  description text,
  FOREIGN KEY(monster_name)
  REFERENCES monster(name)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE spell
(
  spell_id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name text NOT NULL,
  description text NOT NULL,
  page text,
  spell_range text,
  components text,
  materials text,
  ritual BOOLEAN DEFAULT FALSE,
  duration text,
  concentration BOOLEAN NOT NULL DEFAULT FALSE,
  casting_time text NOT NULL,
  level SMALLINT NOT NULL,
  school text,
  classes text,
  higher_level text
);