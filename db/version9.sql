-- MySQL Script generated by MySQL Workbench
-- Wed Sep 25 10:37:32 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`school` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(250) NOT NULL,
  `ADDRESS` VARCHAR(250) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 97
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admin` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(64) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `SCHOOL_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID_ADMIN`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`school` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`admin_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admin_info` (
  `ADMIN_ID` INT(11) NOT NULL,
  `PASSPORT_ID` VARCHAR(45) NOT NULL,
  `SOCIAL_CARD_ID` VARCHAR(45) NOT NULL,
  `BIRTH_DATE` BIGINT(20) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(100) NOT NULL,
  `IMAGE_URL` VARCHAR(100) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  UNIQUE INDEX `ADMIN_ID_UNIQUE` (`ADMIN_ID` ASC) VISIBLE,
  UNIQUE INDEX `PASSPORT_ID_UNIQUE` (`PASSPORT_ID` ASC) VISIBLE,
  UNIQUE INDEX `SOCIAL_CARD_ID_UNIQUE` (`SOCIAL_CARD_ID` ASC) VISIBLE,
  CONSTRAINT `ADMIN_IN_ADMIN_INFO`
    FOREIGN KEY (`ADMIN_ID`)
    REFERENCES `mydb`.`admin` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `DURATION` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(45) NOT NULL,
  `LOCATION` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`lesson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`lesson` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `CONTENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `COURSE_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID_LESSON_idx` (`COURSE_ID` ASC) VISIBLE,
  CONSTRAINT `COURSE_ID_LESSON`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `mydb`.`course` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`test` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `LESSON_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `LESSON_ID_TEST`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`lesson` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`question` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `TEST_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `TEST_ID_idx` (`TEST_ID` ASC) VISIBLE,
  CONSTRAINT `TEST_ID_QUESTION`
    FOREIGN KEY (`TEST_ID`)
    REFERENCES `mydb`.`test` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`answer` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` TEXT NOT NULL,
  `SCORE` INT(11) NOT NULL DEFAULT '0',
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `QUESTION_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `QUESTION_ID_idx` (`QUESTION_ID` ASC) VISIBLE,
  CONSTRAINT `QUESTION_ID_ANSWER`
    FOREIGN KEY (`QUESTION_ID`)
    REFERENCES `mydb`.`question` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`teacher` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(64) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `SCHOOL_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID_TEACHER`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`school` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`assignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`assignment` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `START_DATE` BIGINT(20) NOT NULL,
  `DEADLINE` BIGINT(20) NOT NULL,
  `TITLE` VARCHAR(45) NOT NULL,
  `DESCRIPTION` TEXT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `TEACHER_ID` INT(11) NOT NULL,
  `LESSON_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `TEACHER_ID_ASSIGNMENT_idx` (`TEACHER_ID` ASC) VISIBLE,
  INDEX `LESSON_ID_ASSIGNMENT_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `LESSON_ID_ASSIGNMENT`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`lesson` (`ID`),
  CONSTRAINT `TEACHER_ID_ASSIGNMENT`
    FOREIGN KEY (`TEACHER_ID`)
    REFERENCES `mydb`.`teacher` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`group` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `SCHOOL_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID_GROUP`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`school` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 586
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(64) NOT NULL,
  `STATUS` INT(11) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `GROUP_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  INDEX `GROUP_ID_idx` (`GROUP_ID` ASC) VISIBLE,
  CONSTRAINT `GROUP_ID_STUDENT`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `mydb`.`group` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 393
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`assessment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`assessment` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TITLE` VARCHAR(45) NOT NULL,
  `SCORE` INT(11) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `STUDENT_ID` INT(11) NOT NULL,
  `ASSIGNMENT_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `TITLE_UNIQUE` (`TITLE` ASC) VISIBLE,
  INDEX `ASSIGNMENT_ID_idx` (`ASSIGNMENT_ID` ASC) VISIBLE,
  INDEX `USER_ID_idx` (`STUDENT_ID` ASC) VISIBLE,
  CONSTRAINT `ASSIGNMENT_ID_ASSESMENT`
    FOREIGN KEY (`ASSIGNMENT_ID`)
    REFERENCES `mydb`.`assignment` (`ID`),
  CONSTRAINT `USER_ID_ASSESMENT`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`assignment_feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`assignment_feedback` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` INT(11) NOT NULL,
  `ASSIGNMENT_ID` INT(11) NOT NULL,
  `COMMENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `HOMEWORK_ID_ASSIGNMENT_FEEDBACK_idx` (`ASSIGNMENT_ID` ASC) VISIBLE,
  INDEX `STUDENT_ID_ASSIGNMENT_FEEDBACK_idx` (`STUDENT_ID` ASC) VISIBLE,
  CONSTRAINT `HOMEWORK_ID_ASSIGNMENT_FEEDBACK`
    FOREIGN KEY (`ASSIGNMENT_ID`)
    REFERENCES `mydb`.`assignment` (`ID`),
  CONSTRAINT `STUDENT_ID_ASSIGNMENT_FEEDBACK`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`classroom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`classroom` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `SCHOOL_ID` INT(11) NOT NULL,
  `NUMBER` INT(11) NOT NULL,
  `CAPACITY` INT(11) NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  `SUBJECT` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  INDEX `SCOOL_ID_NUMBER_UNIQUE` (`SCHOOL_ID` ASC, `NUMBER` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID_CLASSROOM`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`school` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`group_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`group_course` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `GROUP_ID` INT(11) NOT NULL,
  `COURSE_ID` INT(11) NOT NULL,
  `TEACHER_ID` INT(11) NOT NULL,
  `START_DATE` BIGINT(20) NOT NULL,
  `IS_FINISHED` TINYINT(4) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID_idx` (`COURSE_ID` ASC) VISIBLE,
  INDEX `GROUP_ID_idx` (`GROUP_ID` ASC) VISIBLE,
  INDEX `TEACHER_ID_GROUP_COURSE_idx` (`TEACHER_ID` ASC) VISIBLE,
  CONSTRAINT `COURSE_ID_GROUP_COURSE`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `mydb`.`course` (`ID`),
  CONSTRAINT `GROUP_ID_GROUP_COURSE`
    FOREIGN KEY (`GROUP_ID`)
    REFERENCES `mydb`.`group` (`ID`),
  CONSTRAINT `TEACHER_ID_GROUP_COURSE`
    FOREIGN KEY (`TEACHER_ID`)
    REFERENCES `mydb`.`teacher` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`schedule_record`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`schedule_record` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `GROUP_COURSE_ID` INT(11) NOT NULL,
  `CLASSROOM_ID` INT(11) NOT NULL,
  `LESSON_ID` INT(11) NOT NULL,
  `START_DATE` BIGINT(20) NOT NULL,
  `END_DATE` BIGINT(20) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE,
  INDEX `GROUP_COURSE_ID_SCHEDULE_idx` (`GROUP_COURSE_ID` ASC) VISIBLE,
  INDEX `CLASSROOM_ID_SCHEDULE_idx` (`CLASSROOM_ID` ASC) VISIBLE,
  INDEX `LESSON_ID_SCHEDULE_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `CLASSROOM_ID_SCHEDULE`
    FOREIGN KEY (`CLASSROOM_ID`)
    REFERENCES `mydb`.`classroom` (`ID`),
  CONSTRAINT `GROUP_COURSE_ID_SCHEDULE`
    FOREIGN KEY (`GROUP_COURSE_ID`)
    REFERENCES `mydb`.`group_course` (`ID`),
  CONSTRAINT `LESSON_ID_SCHEDULE`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`lesson` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`topic` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `VIDEO_URL` VARCHAR(250) NOT NULL,
  `WEB_PAGE_URL` VARCHAR(250) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `LESSON_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `LESSON_ID_TOPIC`
    FOREIGN KEY (`LESSON_ID`)
    REFERENCES `mydb`.`lesson` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`student_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student_comment` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `COMMENT` TEXT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  `TOPIC_ID` INT(11) NOT NULL,
  `STUDENT_ID` INT(11) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `URL_ID_idx` (`TOPIC_ID` ASC) VISIBLE,
  INDEX `USER_ID_idx` (`STUDENT_ID` ASC) VISIBLE,
  CONSTRAINT `STUDENT_ID_STUDENT_COMMENT`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`),
  CONSTRAINT `TOPIC_ID_STUDENT_COMMENT`
    FOREIGN KEY (`TOPIC_ID`)
    REFERENCES `mydb`.`topic` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`student_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`student_info` (
  `STUDENT_ID` INT(11) NOT NULL,
  `PASSPORT_ID` VARCHAR(45) NOT NULL,
  `SOCIAL_CARD_ID` VARCHAR(45) NOT NULL,
  `BIRTH_DATE` BIGINT(20) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(100) NOT NULL,
  `ADMISSION_DATE` BIGINT(20) NOT NULL,
  `IMAGE_URL` VARCHAR(100) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  UNIQUE INDEX `STUDENT_ID_UNIQUE` (`STUDENT_ID` ASC) VISIBLE,
  UNIQUE INDEX `SOCIAL_CARD_ID_UNIQUE` (`SOCIAL_CARD_ID` ASC) VISIBLE,
  UNIQUE INDEX `IMAGE_URL_UNIQUE` (`IMAGE_URL` ASC) VISIBLE,
  UNIQUE INDEX `PASSPORT_ID_UNIQUE` (`PASSPORT_ID` ASC) VISIBLE,
  CONSTRAINT `STUDENT_ID_STUDENT_INFO`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`survey_question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`survey_question` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` VARCHAR(100) NOT NULL,
  `INPUT_NAME` VARCHAR(45) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`survey_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`survey_result` (
  `STUDENT_ID` INT(11) NOT NULL,
  `EXT` FLOAT NOT NULL,
  `EST` FLOAT NOT NULL,
  `AGR` FLOAT NOT NULL,
  `CSN` FLOAT NOT NULL,
  `OPN` FLOAT NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  UNIQUE INDEX `STUDENT_ID_UNIQUE` (`STUDENT_ID` ASC),
  CONSTRAINT `STUDENT_ID_SURVEY_RESULT`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`teacher_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`teacher_info` (
  `TEACHER_ID` INT(11) NOT NULL,
  `PASSPORT_ID` VARCHAR(45) NOT NULL,
  `SOCIAL_CARD_ID` VARCHAR(45) NOT NULL,
  `BIRTH_DATE` BIGINT(20) NOT NULL,
  `PHONE` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(100) NOT NULL,
  `IMAGE_URL` VARCHAR(100) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  UNIQUE INDEX `TEACHER_ID_UNIQUE` (`TEACHER_ID` ASC) VISIBLE,
  UNIQUE INDEX `PASSPORT_ID_UNIQUE` (`PASSPORT_ID` ASC) VISIBLE,
  UNIQUE INDEX `SOCIAL_CARD_ID_UNIQUE` (`SOCIAL_CARD_ID` ASC) VISIBLE,
  CONSTRAINT `TEACHER_ID_TEACHER_INFO`
    FOREIGN KEY (`TEACHER_ID`)
    REFERENCES `mydb`.`teacher` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`test_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`test_result` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `TEST_ID` INT(11) NOT NULL,
  `STUDENT_ID` INT(11) NOT NULL,
  `SCORE` INT(11) NOT NULL,
  `CREATION_DATE` BIGINT(20) NOT NULL,
  `CHANGE_DATE` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `USER_ID_TEST_RESULT_idx` (`STUDENT_ID` ASC) VISIBLE,
  INDEX `TEST_ID_TEST_RESULT` (`TEST_ID` ASC) VISIBLE,
  CONSTRAINT `STUDENT_ID_TEST_RESULT`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`student` (`ID`),
  CONSTRAINT `TEST_ID_TEST_RESULT`
    FOREIGN KEY (`TEST_ID`)
    REFERENCES `mydb`.`test` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `mydb`;

DELIMITER $$
USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`SCHOOL_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`school`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`ADMIN_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`admin`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`COURSE_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`course`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`LESSON_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`lesson`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`TEST_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`test`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`QUESTION_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`question`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`ANSWER_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`answer`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`TEACHER_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`teacher`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`ASSIGNMENT_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`assignment`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`GROUP_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`group`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`STUDENT_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`student`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`ASSESSMENT_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`assessment`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`ASSIGNMENT_FEEDBACK_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`assignment_feedback`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`CLASSROOM_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`classroom`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`GROUP_COURSE_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`group_course`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`SCHEDULE_RECORD_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`schedule_record`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`TOPIC_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`topic`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`USER_COMMENT_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`topic`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$

USE `mydb`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `mydb`.`TEST_RESULT_CHANGE_DATE_BEFORE_INSERT`
BEFORE INSERT ON `mydb`.`test_result`
FOR EACH ROW
BEGIN
IF NEW.CHANGE_DATE IS NULL THEN
    SET NEW.CHANGE_DATE := NEW.CREATION_DATE;
  END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;