-- MySQL Script generated by MySQL Workbench
-- Tue Sep  3 19:17:35 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`SCHOOL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SCHOOL` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(250) NOT NULL,
  `ADDRESS` VARCHAR(250) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`GROUP` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `SCHOOL_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC),
  CONSTRAINT `SCHOOL_ID_GROUP`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`SCHOOL` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`COURSE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `DURATION` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(45) NOT NULL,
  `LOCATION` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LESSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LESSON` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `CONTENT` TEXT(1500) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `COURSE_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID_LESSON_idx` (`COURSE_ID` ASC),
  CONSTRAINT `COURSE_ID_LESSON`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `mydb`.`COURSE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TOPIC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TOPIC` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `VIDEO_URL` VARCHAR(250) NOT NULL,
  `WEB_PAGE_URL` VARCHAR(250) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `LESSON_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC),
  CONSTRAINT `LESSON_ID_TOPIC`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`LESSON` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TEST`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TEST` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `LESSON_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC),
  CONSTRAINT `LESSON_ID_TEST`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`LESSON` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`QUESTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`QUESTION` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONTENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `TEST_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `TEST_ID_idx` (`TEST_ID` ASC),
  CONSTRAINT `TEST_ID_QUESTION`
    FOREIGN KEY (`TEST_ID`)
    REFERENCES `mydb`.`TEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ANSWER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ANSWER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONTENT` TEXT NOT NULL,
  `SCORE` INT NOT NULL DEFAULT 0,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `QUESTION_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `QUESTION_ID_idx` (`QUESTION_ID` ASC),
  CONSTRAINT `QUESTION_ID_ANSWER`
    FOREIGN KEY (`QUESTION_ID`)
    REFERENCES `mydb`.`QUESTION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  `STATUS` VARCHAR(45) NOT NULL,
  `INFO` TEXT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `GROUP_ID` INT NOT NULL,
  `SCHOOL_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `GROUP_ID_idx` (`GROUP_ID` ASC),
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC),
  CONSTRAINT `GROUP_ID_USER`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `mydb`.`GROUP` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `SCHOOL_ID`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`SCHOOL` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`GROUP_COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`GROUP_COURSE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `GROUP_ID` INT NOT NULL,
  `COURSE_ID` INT NOT NULL,
  `TEACHER_ID` INT NOT NULL,
  `START_DATE` BIGINT NOT NULL,
  `IS_FINISHED` TINYINT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID_idx` (`COURSE_ID` ASC),
  INDEX `GROUP_ID_idx` (`GROUP_ID` ASC),
  INDEX `TEACHER_ID_GROUP_COURSE_idx` (`TEACHER_ID` ASC),
  CONSTRAINT `COURSE_ID_GROUP_COURSE`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `mydb`.`COURSE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `GROUP_ID_GROUP_COURSE`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `mydb`.`GROUP` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TEACHER_ID_GROUP_COURSE`
    FOREIGN KEY (`TEACHER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ASSIGNMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ASSIGNMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `START_DATE` BIGINT NOT NULL,
  `DEADLINE` BIGINT NOT NULL,
  `TITLE` VARCHAR(45) NOT NULL,
  `DESCRIPTION` TEXT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `TEACHER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `TEACHER_ID_ASSIGNMENT_idx` (`TEACHER_ID` ASC),
  CONSTRAINT `TEACHER_ID_ASSIGNMENT`
    FOREIGN KEY (`TEACHER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`USER_COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`USER_COMMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `COMMENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `TOPIC_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `URL_ID_idx` (`TOPIC_ID` ASC),
  INDEX `USER_ID_idx` (`USER_ID` ASC),
  CONSTRAINT `USER_ID_USER_COMMENT`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `TOPIC_ID_USER_COMMENT`
    FOREIGN KEY (`TOPIC_ID`)
    REFERENCES `mydb`.`TOPIC` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ASSESSMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ASSESSMENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `SCORE` INT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  `USER_ID` INT NOT NULL,
  `ASSIGNMENT_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `ASSIGNMENT_ID_idx` (`ASSIGNMENT_ID` ASC),
  INDEX `USER_ID_idx` (`USER_ID` ASC),
  UNIQUE INDEX `TITLE_UNIQUE` (`TITLE` ASC),
  CONSTRAINT `ASSIGNMENT_ID_ASSESMENT`
    FOREIGN KEY (`ASSIGNMENT_ID`)
    REFERENCES `mydb`.`ASSIGNMENT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_ID_ASSESMENT`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TEST_RESULT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TEST_RESULT` (
  `TEST_ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  `SCORE` INT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`TEST_ID`, `USER_ID`),
  INDEX `USER_ID_idx` (`USER_ID` ASC),
  CONSTRAINT `TEST_ID_TEST_RESULT`
    FOREIGN KEY (`TEST_ID`)
    REFERENCES `mydb`.`TEST` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `USER_ID_TEST_RESULT`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `mydb`.`USER` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CLASSROOM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CLASSROOM` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `SCHOOL_ID` INT NOT NULL,
  `NUMBER` INT NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  `SUBJECT` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  UNIQUE INDEX `SCOOL_ID_NUMBER_UNIQUE` (`SCHOOL_ID` ASC, `NUMBER` ASC),
  CONSTRAINT `SCHOOL_ID_CLASSROOM`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`SCHOOL` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SCHEDULE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SCHEDULE` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `GROUP_COURSE_ID` INT NOT NULL,
  `CLASSROOM_ID` INT NOT NULL,
  `LESSON_ID` INT NOT NULL,
  `START_DATE` BIGINT NOT NULL,
  `END_DATE` BIGINT NOT NULL,
  `CREATION_DATE` BIGINT NOT NULL,
  `CHANGE_DATE` BIGINT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  INDEX `GROUP_COURSE_ID_SCHEDULE_idx` (`GROUP_COURSE_ID` ASC),
  INDEX `CLASSROOM_ID_SCHEDULE_idx` (`CLASSROOM_ID` ASC),
  INDEX `LESSON_ID_SCHEDULE_idx` (`LESSON_ID` ASC),
  CONSTRAINT `GROUP_COURSE_ID_SCHEDULE`
    FOREIGN KEY (`GROUP_COURSE_ID`)
    REFERENCES `mydb`.`GROUP_COURSE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CLASSROOM_ID_SCHEDULE`
    FOREIGN KEY (`CLASSROOM_ID`)
    REFERENCES `mydb`.`CLASSROOM` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `LESSON_ID_SCHEDULE`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`LESSON` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb`;

DELIMITER $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`SCHOOL_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `SCHOOL` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`GROUP_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `GROUP` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`COURSE_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `COURSE` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`LESSON_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `LESSON` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`TOPIC_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `TOPIC` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`TEST_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `TEST` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`QUESTION_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `QUESTION` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`ANSWER_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `ANSWER` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`USER_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `USER` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`GROUP_COURSE_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `GROUP_COURSE` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`ASSIGNMENT_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `ASSIGNMENT` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`USER_COMMENT_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `TOPIC` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`ASSESSMENT_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `ASSESSMENT` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`TEST_RESULT_CHANGE_DATE_BEFORE_INSERT` BEFORE INSERT ON `TEST_RESULT` FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
