-- MySQL Script generated by MySQL Workbench
-- Wed May 24 17:14:08 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrips
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrips
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrips` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrips` ;

-- -----------------------------------------------------
-- Table `enjoytrips`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrips`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrips`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrips`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `enjoytrips`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrips`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrips`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrips`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrips`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrips`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrips`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrips`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`members` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NOT NULL,
  `type` INT NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `announce_to_members_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `announce_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrips`.`members` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 44
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `comment` VARCHAR(100) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  INDEX `comment_to_board_article_no_idx` (`article_no` ASC) VISIBLE,
  INDEX `comment_to_members_user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `comment_to_board_article_no`
    FOREIGN KEY (`article_no`)
    REFERENCES `enjoytrips`.`board` (`article_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `comment_to_members_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrips`.`members` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`file_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`file_info` (
  `file_id` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NULL DEFAULT NULL,
  `save_folder` VARCHAR(45) NULL DEFAULT NULL,
  `origin_name` VARCHAR(50) NULL DEFAULT NULL,
  `save_name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  INDEX `file_info_to_board_article_no_fk_idx` (`article_no` ASC) VISIBLE,
  CONSTRAINT `file_info_to_board_article_no_fk`
    FOREIGN KEY (`article_no`)
    REFERENCES `enjoytrips`.`board` (`article_no`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`like_content`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`like_content` (
  `like_id` INT NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `content_id` VARCHAR(45) NOT NULL,
  `content_type` INT NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_id`),
  INDEX `like_content_to_members_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `like_content_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrips`.`members` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`plan` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `plan_name` VARCHAR(45) NULL DEFAULT NULL,
  `start_date` TIMESTAMP NULL DEFAULT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `plan_desc` VARCHAR(2000) NULL DEFAULT NULL,
  `like_cnt` INT NULL DEFAULT '0',
  PRIMARY KEY (`plan_id`),
  INDEX `plan_to_members_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `plan_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrips`.`members` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`plan_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`plan_detail` (
  `plan_detail_id` INT NOT NULL AUTO_INCREMENT,
  `plan_id` INT NOT NULL,
  `content_id` INT NOT NULL,
  `day` INT NULL DEFAULT '1',
  `order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`plan_detail_id`),
  INDEX `plan_detail_to_attraction_info_content_id_idx` (`content_id` ASC) VISIBLE,
  INDEX `plan_detail_to_plan_plan_id_fk` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `plan_detail_to_attraction_info_content_id`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrips`.`attraction_info` (`content_id`),
  CONSTRAINT `plan_detail_to_plan_plan_id_fk`
    FOREIGN KEY (`plan_id`)
    REFERENCES `enjoytrips`.`plan` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 83
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrips`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrips`.`rating` (
  `rating_id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
  `rate` ENUM('1', '2', '3', '4', '5', '6', '7', '8', '9', '10') NULL DEFAULT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rating_id`),
  INDEX `rating_to_members_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `rating_to_attraction_info_content_id_fk` (`content_id` ASC) VISIBLE,
  CONSTRAINT `rating_to_attraction_info_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrips`.`attraction_info` (`content_id`),
  CONSTRAINT `rating_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrips`.`members` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
