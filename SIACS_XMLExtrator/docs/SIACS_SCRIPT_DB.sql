CREATE SCHEMA IF NOT EXISTS siacs_xml_extrator; 
USE siacs_xml_extrator;

-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS siacs_xml_extrator.tag(
  id_tag INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  root_tag VARCHAR(120),
  children_tag VARCHAR(120))
COMMENT = 'why size of root_tag and children_tag columm is 120? Because the biggest tag found on xml curriculum was 59. Consider this size(120) as safer.';

-- -----------------------------------------------------
-- Table `siacs_xml_extrator`.`value`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS siacs_xml_extrator.`value` (
  id_value INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  content TEXT,
  tag_id_tag INT NOT NULL,
  FOREIGN KEY (tag_id_tag) REFERENCES siacs_xml_extrator.tag (id_tag) on delete cascade)