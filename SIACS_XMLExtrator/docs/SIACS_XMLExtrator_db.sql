
CREATE SCHEMA IF NOT EXISTS `siacs_xml_extrator`;
USE `siacs_xml_extrator` ;

-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`element`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`element` (
  `id_element` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NULL,
  PRIMARY KEY (`id_element`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`attribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`attribute` (
  `id_attribute` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NULL,
  `id_element` INT NOT NULL,
  PRIMARY KEY (`id_attribute`, `id_element`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_attribute_element_idx` (`id_element` ASC),
  CONSTRAINT `fk_attribute_element`
    FOREIGN KEY (`id_element`)
    REFERENCES `siacs_xml_extrator`.`element` (`id_element`) on delete cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`value`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`value` (
  `id_value` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NULL,
  `id_attribute` INT NOT NULL,
  PRIMARY KEY (`id_value`, `id_attribute`),
  INDEX `fk_value_attribute1_idx` (`id_attribute` ASC),
  CONSTRAINT `fk_value_attribute1`
    FOREIGN KEY (`id_attribute`)
    REFERENCES `siacs_xml_extrator`.`attribute` (`id_attribute`) on delete cascade)
ENGINE = InnoDB;