DROP DATABASE `siacs_xml_extrator`;
CREATE SCHEMA IF NOT EXISTS `siacs_xml_extrator`;
USE `siacs_xml_extrator` ;

-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`element`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`element` (
  `id_element` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NULL DEFAULT NULL,
  `id_parent_element` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_element`),
  FOREIGN KEY (`id_parent_element`)
  REFERENCES `siacs_xml_extrator`.`element` (`id_element`)
);

-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`attribute`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`attribute` (
  `id_attribute` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NULL DEFAULT NULL,
  `id_element` INT(11) NOT NULL,
  PRIMARY KEY (`id_attribute`),
  FOREIGN KEY (`id_element`) REFERENCES `siacs_xml_extrator`.`element` (`id_element`)
);


-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`value`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `siacs_xml_extrator`.`value` (
  `id_value` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `id_attribute` INT(11) NOT NULL,
  PRIMARY KEY (`id_value`),
  FOREIGN KEY (`id_attribute`) REFERENCES `siacs_xml_extrator`.`attribute` (`id_attribute`)
);