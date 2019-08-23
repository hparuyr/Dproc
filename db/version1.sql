-- MySQL Script generated by MySQL Workbench
-- Fri Aug 23 19:56:56 2019
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
-- Table `mydb`.`SCHOOL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SCHOOL` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`GROUP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`GROUP` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `SCHOOL_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID`
    FOREIGN KEY (`SCHOOL_ID`)
    REFERENCES `mydb`.`SCHOOL` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`STUDENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`STUDENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `LASTNAME` VARCHAR(45) NOT NULL,
  `PARENT` INT NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `GRUIP_ID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `GRUIP_ID_idx` (`GRUIP_ID` ASC) VISIBLE,
  CONSTRAINT `GRUIP_ID`
    FOREIGN KEY (`GRUIP_ID`)
    REFERENCES `mydb`.`GROUP` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TEACHER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TEACHER` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `SURNAME` VARCHAR(45) NOT NULL,
  `LASTNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `SCHOOL_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `SCHOOL_ID_idx` (`SCHOOL_ID` ASC) VISIBLE,
  CONSTRAINT `SCHOOL_ID`
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
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LESSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LESSON` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `CONTENT` TEXT(1500) NOT NULL,
  `COURE_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURE_ID_idx` (`COURE_ID` ASC) VISIBLE,
  CONSTRAINT `COURE_ID`
    FOREIGN KEY (`COURE_ID`)
    REFERENCES `mydb`.`COURSE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`VIDEO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`VIDEO` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `URL` VARCHAR(255) NOT NULL,
  `LESSON_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `LESSON_ID`
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
  `LESSON_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `LESSON_ID_idx` (`LESSON_ID` ASC) VISIBLE,
  CONSTRAINT `LESSON_ID`
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
  `CONTENT` VARCHAR(150) NOT NULL,
  `TEST_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `TEST_ID_idx` (`TEST_ID` ASC) VISIBLE,
  CONSTRAINT `TEST_ID`
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
  `CONTENT` VARCHAR(45) NOT NULL,
  `STATUS` VARCHAR(45) NOT NULL,
  `QUESTION_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `QUESTION_ID_idx` (`QUESTION_ID` ASC) VISIBLE,
  CONSTRAINT `QUESTION_ID`
    FOREIGN KEY (`QUESTION_ID`)
    REFERENCES `mydb`.`QUESTION` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`STUDENT_COURSE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`STUDENT_COURSE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `COURSE_ID` INT NULL,
  `STUDENT_ID` INT NULL,
  `STUDENT_COUSEcol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `COURSE_ID_idx` (`COURSE_ID` ASC) VISIBLE,
  INDEX `STUDENT_ID_idx` (`STUDENT_ID` ASC) VISIBLE,
  CONSTRAINT `COURSE_ID`
    FOREIGN KEY (`COURSE_ID`)
    REFERENCES `mydb`.`COURSE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `STUDENT_ID`
    FOREIGN KEY (`STUDENT_ID`)
    REFERENCES `mydb`.`STUDENT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
