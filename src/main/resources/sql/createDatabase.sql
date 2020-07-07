DROP DATABASE IF EXISTS dndtools;
CREATE DATABASE dndtools;
USE dndtools;
DROP TABLE IF EXISTS ability_levels;
DROP TABLE IF EXISTS saving_throws;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS spell;
DROP TABLE IF EXISTS campaign;

CREATE TABLE `monster` (
  `name` varchar(255) NOT NULL,
  `size` text,
  `type` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `subtype` text,
  `alignment` text,
  `armor_class` smallint DEFAULT NULL,
  `hit_points` smallint DEFAULT NULL,
  `hit_dice` text,
  `speed` text,
  `senses` text,
  `languages` text,
  `challenge_rating` decimal(6,4) DEFAULT NULL,
  `damage_vulnerabilities` text,
  `damage_resistances` text,
  `damage_immunities` text,
  `condition_immunities` text,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ability_levels` (
  `strength` smallint DEFAULT NULL,
  `dexterity` smallint DEFAULT NULL,
  `constitution` smallint DEFAULT NULL,
  `wisdom` smallint DEFAULT NULL,
  `intelligence` smallint DEFAULT NULL,
  `charisma` smallint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `ability_levels_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `action` (
  `action_id` smallint NOT NULL AUTO_INCREMENT,
  `monster_name` varchar(255) NOT NULL,
  `action_type` smallint NOT NULL,
  `name` text,
  `damage_bonus` smallint DEFAULT NULL,
  `damage_dice` text,
  `attack_bonus` smallint DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`action_id`),
  KEY `monster_name` (`monster_name`),
  CONSTRAINT `action_ibfk_1` FOREIGN KEY (`monster_name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1477 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `saving_throws` (
  `strength` smallint DEFAULT NULL,
  `dexterity` smallint DEFAULT NULL,
  `constitution` smallint DEFAULT NULL,
  `wisdom` smallint DEFAULT NULL,
  `intelligence` smallint DEFAULT NULL,
  `charisma` smallint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `saving_throws_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `skills` (
  `name` varchar(255) NOT NULL,
  `acrobatics` smallint DEFAULT NULL,
  `animal_handling` smallint DEFAULT NULL,
  `arcana` smallint DEFAULT NULL,
  `athletics` smallint DEFAULT NULL,
  `deception` smallint DEFAULT NULL,
  `history` smallint DEFAULT NULL,
  `insight` smallint DEFAULT NULL,
  `intimidation` smallint DEFAULT NULL,
  `investigation` smallint DEFAULT NULL,
  `medicine` smallint DEFAULT NULL,
  `nature` smallint DEFAULT NULL,
  `perception` smallint DEFAULT NULL,
  `performance` smallint DEFAULT NULL,
  `persuasion` smallint DEFAULT NULL,
  `religion` smallint DEFAULT NULL,
  `slight_of_hand` smallint DEFAULT NULL,
  `stealth` smallint DEFAULT NULL,
  `survival` smallint DEFAULT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `spell` (
  `spell_id` smallint NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `page` text,
  `spell_range` text,
  `components` text,
  `materials` text,
  `ritual` tinyint(1) DEFAULT '0',
  `duration` text,
  `concentration` tinyint(1) NOT NULL DEFAULT '0',
  `casting_time` text NOT NULL,
  `level` smallint NOT NULL,
  `school` text,
  `classes` text,
  `higher_level` text,
  PRIMARY KEY (`spell_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


