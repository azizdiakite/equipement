-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema equipement_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema equipement_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `equipement_db` DEFAULT CHARACTER SET utf8 ;
USE `equipement_db` ;

-- -----------------------------------------------------
-- Table `equipement_db`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(255) NOT NULL,
  `longitude` FLOAT NULL,
  `latitude` FLOAT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`dictrict`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`dictrict` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(255) NOT NULL,
  `longitude` FLOAT NULL,
  `latitude` FLOAT NULL,
  `region_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC) VISIBLE,
  INDEX `fk_dictrict_region_idx` (`region_id` ASC) VISIBLE,
  CONSTRAINT `fk_dictrict_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `equipement_db`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`site`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`site` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `diis_code` VARCHAR(255) NULL,
  `datim_name` VARCHAR(255) NULL,
  `datim_code` VARCHAR(255) NULL,
  `city` VARCHAR(100) NOT NULL,
  `longitude` FLOAT NOT NULL,
  `latitude` FLOAT NOT NULL,
  `dictrict_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `datim_name_UNIQUE` (`datim_name` ASC) VISIBLE,
  UNIQUE INDEX `datim_code_UNIQUE` (`datim_code` ASC) VISIBLE,
  UNIQUE INDEX `diis_code_UNIQUE` (`diis_code` ASC) VISIBLE,
  INDEX `fk_site_dictrict1_idx` (`dictrict_id` ASC) VISIBLE,
  CONSTRAINT `fk_site_dictrict1`
    FOREIGN KEY (`dictrict_id`)
    REFERENCES `equipement_db`.`dictrict` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`lab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`lab` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lab_manager_name` VARCHAR(255) NOT NULL,
  `lab_manager_phone` VARCHAR(100) NOT NULL,
  `lab_manager_mail` VARCHAR(45) NOT NULL,
  `electric_context` VARCHAR(100) NULL,
  `lab_type` VARCHAR(100) NOT NULL,
  `active_staff_number` INT NOT NULL,
  `infrastructure_type` VARCHAR(100) NULL,
  `diem_staff_name` VARCHAR(255) NOT NULL,
  `diem_staff_contact` VARCHAR(45) NOT NULL,
  `diem_staff_mail` VARCHAR(100) NOT NULL,
  `longitude` FLOAT NULL,
  `latitude` FLOAT NULL,
  `site_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `lab_manager_contact_UNIQUE` (`lab_manager_phone` ASC) VISIBLE,
  UNIQUE INDEX `lab_manager_mail_UNIQUE` (`lab_manager_mail` ASC) VISIBLE,
  UNIQUE INDEX `electric_context_UNIQUE` (`electric_context` ASC) VISIBLE,
  INDEX `fk_lab_site1_idx` (`site_id` ASC) VISIBLE,
  CONSTRAINT `fk_lab_site1`
    FOREIGN KEY (`site_id`)
    REFERENCES `equipement_db`.`site` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`partner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`partner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `partner_name` VARCHAR(100) NOT NULL,
  `active` CHAR(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `partner_name_UNIQUE` (`partner_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`supplier` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `contact` VARCHAR(100) NOT NULL,
  `mail` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `contact_UNIQUE` (`contact` ASC) VISIBLE,
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`equipement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`equipement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `equipement_name` VARCHAR(255) NOT NULL,
  `category` VARCHAR(255) NULL,
  `brand` VARCHAR(255) NULL,
  `model` VARCHAR(255) NULL,
  `serial_number` VARCHAR(255) NULL,
  `capacity` INT NULL,
  `supplier_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `equipement_name_UNIQUE` (`equipement_name` ASC) VISIBLE,
  INDEX `fk_equipement_supplier1_idx` (`supplier_id` ASC) VISIBLE,
  CONSTRAINT `fk_equipement_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `equipement_db`.`supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`contractor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`contractor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `rep_name` VARCHAR(255) NOT NULL,
  `rep_contact` VARCHAR(45) NOT NULL,
  `rep_mail` VARCHAR(100) NOT NULL,
  `contract_start_date` DATE NULL,
  `contract_end_date` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`lab_has_equipement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`lab_has_equipement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `lab_id` INT NOT NULL,
  `equipement_id` INT NOT NULL,
  `tag_number` VARCHAR(100) NULL,
  `installation_date` DATE NULL,
  `warranty_start_date` DATE NULL,
  `warranty_end_date` DATE NULL,
  `active` CHAR(1) NULL,
  `release_date` DATE NULL,
  `contractor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lab_has_equipement_equipement1_idx` (`equipement_id` ASC) VISIBLE,
  INDEX `fk_lab_has_equipement_lab1_idx` (`lab_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_lab_has_equipement_contractor1_idx` (`contractor_id` ASC) VISIBLE,
  CONSTRAINT `fk_lab_has_equipement_lab1`
    FOREIGN KEY (`lab_id`)
    REFERENCES `equipement_db`.`lab` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lab_has_equipement_equipement1`
    FOREIGN KEY (`equipement_id`)
    REFERENCES `equipement_db`.`equipement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lab_has_equipement_contractor1`
    FOREIGN KEY (`contractor_id`)
    REFERENCES `equipement_db`.`contractor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`maintenance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`maintenance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(100) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `lab_staff_name` VARCHAR(100) NOT NULL,
  `lab_staff_contact` VARCHAR(45) NOT NULL,
  `intervention` TEXT NOT NULL,
  `recommendatioon` TEXT NOT NULL,
  `lab_has_equipement_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenance_lab_has_equipement1_idx` (`lab_has_equipement_id` ASC) VISIBLE,
  CONSTRAINT `fk_maintenance_lab_has_equipement1`
    FOREIGN KEY (`lab_has_equipement_id`)
    REFERENCES `equipement_db`.`lab_has_equipement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`panne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`panne` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NULL,
  `reporter_name` VARCHAR(255) NULL,
  `report_date` DATE NULL,
  `type` VARCHAR(100) NULL,
  `contractor_inform_date` DATE NULL,
  `status` VARCHAR(45) NULL,
  `lab_has_equipement_id` INT NOT NULL,
  `lab_has_equipement_contractor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_panne_lab_has_equipement1_idx` (`lab_has_equipement_id` ASC, `lab_has_equipement_contractor_id` ASC) VISIBLE,
  CONSTRAINT `fk_panne_lab_has_equipement1`
    FOREIGN KEY (`lab_has_equipement_id`)
    REFERENCES `equipement_db`.`lab_has_equipement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`maintenance_planning`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`maintenance_planning` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `lab_has_equipement_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenance_planning_lab_has_equipement1_idx` (`lab_has_equipement_id` ASC) VISIBLE,
  CONSTRAINT `fk_maintenance_planning_lab_has_equipement1`
    FOREIGN KEY (`lab_has_equipement_id`)
    REFERENCES `equipement_db`.`lab_has_equipement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipement_db`.`partner_has_lab`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipement_db`.`partner_has_lab` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `partner_id` INT NOT NULL,
  `lab_id` INT NOT NULL,
  `active` CHAR(1) NULL,
  `partner_rep_name` VARCHAR(255) NOT NULL,
  `partner_rep_contact` VARCHAR(45) NULL,
  `partner_rep_mail` VARCHAR(100) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  INDEX `fk_partner_has_lab_lab1_idx` (`lab_id` ASC) VISIBLE,
  INDEX `fk_partner_has_lab_partner1_idx` (`partner_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `partner_rep_mail_UNIQUE` (`partner_rep_mail` ASC) VISIBLE,
  CONSTRAINT `fk_partner_has_lab_partner1`
    FOREIGN KEY (`partner_id`)
    REFERENCES `equipement_db`.`partner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partner_has_lab_lab1`
    FOREIGN KEY (`lab_id`)
    REFERENCES `equipement_db`.`lab` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
