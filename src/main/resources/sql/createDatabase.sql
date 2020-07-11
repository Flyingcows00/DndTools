DROP DATABASE IF EXISTS dndtools;
CREATE DATABASE dndtools;
USE dndtools;
DROP TABLE IF EXISTS ability_levels;
DROP TABLE IF EXISTS saving_throws;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS action;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS spell;
DROP TABLE IF EXISTS encounter_player;
DROP TABLE IF EXISTS encounter_monster;
DROP TABLE IF EXISTS encounter;
DROP TABLE IF EXISTS campaign;

CREATE TABLE `monster` (
  `name` varchar(256) NOT NULL,
  `size` varchar(64),
  `type` varchar(64),
  `subtype` varchar(64),
  `alignment` varchar(64),
  `armor_class` smallint DEFAULT NULL,
  `hit_points` smallint DEFAULT NULL,
  `hit_dice` varchar(64),
  `speed` varchar(64),
  `senses` varchar(256),
  `languages` varchar(256),
  `challenge_rating` decimal(6,4) DEFAULT NULL,
  `damage_vulnerabilities` varchar(256),
  `damage_resistances` varchar(256),
  `damage_immunities` varchar(256),
  `condition_immunities` varchar(256),
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ability_levels` (
  `strength` smallint DEFAULT NULL,
  `dexterity` smallint DEFAULT NULL,
  `constitution` smallint DEFAULT NULL,
  `wisdom` smallint DEFAULT NULL,
  `intelligence` smallint DEFAULT NULL,
  `charisma` smallint DEFAULT NULL,
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `ability_levels_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `action` (
  `action_id` smallint NOT NULL AUTO_INCREMENT,
  `monster_name` varchar(256) NOT NULL,
  `action_type` smallint NOT NULL,
  `name` varchar(256),
  `damage_bonus` smallint DEFAULT NULL,
  `damage_dice` varchar(64),
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
  `name` varchar(256) NOT NULL,
  PRIMARY KEY (`name`),
  CONSTRAINT `saving_throws_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `skills` (
  `name` varchar(256) NOT NULL,
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
  `name` varchar(64) NOT NULL,
  `description` text NOT NULL,
  `page` varchar(64),
  `spell_range` varchar(64),
  `components` varchar(256),
  `materials` varchar(1024),
  `ritual` tinyint(1) DEFAULT '0',
  `duration` varchar(64),
  `concentration` tinyint(1) NOT NULL DEFAULT '0',
  `casting_time` varchar(64) NOT NULL,
  `level` smallint NOT NULL,
  `school` varchar(64),
  `classes` varchar(256),
  `higher_level` text,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `campaign` (
  `campaign_id` smallint NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL UNIQUE,
  `create_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`campaign_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `player` (
  `player_id` smallint NOT NULL AUTO_INCREMENT,
  `player_name` varchar(256) NOT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `character` (
  `character_id` smallint NOT NULL AUTO_INCREMENT,
  `player_id` smallint NOT NULL,
  `character_name` varchar(256) NOT NULL,
  `alive` tinyint(1) NOT NULL DEFAULT '1'
  `notes` text,
  PRIMARY KEY (`character_id`),
  CONSTRAINT `character_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `character_campaign` (
  `character_id` smallint NOT NULL,
  `campaign_id` smallint NOT NULL,
  CONSTRAINT `character_campaign_ibfk_1` FOREIGN KEY (`character_id`) REFERENCES `character` (`character_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `character_campaign_ibfk_2` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `encounter` (
  `encounter_id` smallint NOT NULL AUTO_INCREMENT,
  `campaign_id` smallint NOT NULL,
  `name` varchar(256) NOT NULL,
  `create_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`encounter_id`),
  CONSTRAINT `encounter_ibfk_1` FOREIGN KEY (`campaign_id`) REFERENCES `campaign` (`campaign_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `encounter_player` (
    `encounter_id` smallint NOT NULL,
    `player_id` smallint NOT NULL,
    `conditions` varchar(256),
    `notes` text,
    `order` smallint NOT NULL,
    CONSTRAINT `encounter_player_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `encounter_player_ibfk_2` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `encounter_monster` (
    `encounter_id` smallint NOT NULL,
    `name` varchar(256) NOT NULL,
    `current_hp` smallint NOT NULL,
    `conditions` varchar(256),
    `notes` text,
    `order` smallint NOT NULL,
    CONSTRAINT `encounter_monster_ibfk_1` FOREIGN KEY (`name`) REFERENCES `monster` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `encounter_monster_ibfk_2` FOREIGN KEY (`encounter_id`) REFERENCES `encounter` (`encounter_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

