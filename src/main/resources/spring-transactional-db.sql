
DROP DATABASE IF EXISTS `spring-transaction-db`;
CREATE DATABASE IF NOT EXISTS `spring-transaction-db`;
 
USE `spring-transaction-db`;

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `addr1` varchar(50) DEFAULT NULL,
  `addr2` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `api_call_audit_log` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`remark` VARCHAR(50) NULL DEFAULT NULL,
	`status` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB
;

CREATE TABLE `user_identity_logs` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`remark` VARCHAR(50) NULL DEFAULT NULL,
	`status` VARCHAR(50) NULL DEFAULT NULL,
	`userId` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=InnoDB
;

