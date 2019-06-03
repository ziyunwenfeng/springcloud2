CREATE TABLE IF NOT EXISTS `alan-oauth`.`t_User` (
  `name` VARCHAR(128) NOT NULL,
  `password` VARCHAR(256) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `alan-oauth`.`t_role` (
  `id` VARCHAR(128) NOT NULL,
  `name` VARCHAR(256) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
insert into t_role(id,name) values("1","user");
commit;