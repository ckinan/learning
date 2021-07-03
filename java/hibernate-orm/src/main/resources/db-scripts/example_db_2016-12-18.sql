# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.30)
# Database: example_db
# Generation Time: 2016-12-18 05:27:02 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table company
# ------------------------------------------------------------

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;

INSERT INTO `company` (`id`, `name`)
VALUES
	(1,'Americas TLF'),
	(2,'Rossel INC');

/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;

INSERT INTO `employee` (`id`, `age`, `name`, `salary`, `company_id`)
VALUES
	(1,20,'hibernate_employee',10044.8,1),
	(2,20,'hibernate_employee',2000,1),
	(3,20,'hibernate_employee',2000,1),
	(4,20,'hibernate_employee',2000,1),
	(5,20,'hibernate_employee',2000,1),
	(6,20,'hibernate_employee',2000,1),
	(7,20,'hibernate_employee',2000,1),
	(8,20,'hibernate_employee',2000,1),
	(9,20,'hibernate_employee',2000,1),
	(10,20,'hibernate_employee',2000,1),
	(11,20,'hibernate_employee',2000,1),
	(12,20,'hibernate_employee',2000,1),
	(13,20,'hibernate_employee',2000,1),
	(14,20,'hibernate_employee',2000,1),
	(15,20,'hibernate_employee',2000,1),
	(16,20,'hibernate_employee',2000,1),
	(17,20,'hibernate_employee',2000,1),
	(18,20,'hibernate_employee',2000,1),
	(19,25,'name1',2500,1),
	(20,20,'hibernate_employee',2000,1),
	(21,20,'hibernate employee crud 1',2000,1),
	(22,20,'hibernate employee crud 1',2000,1),
	(23,20,'hibernate employee crud 1_v2',2000,1),
	(24,20,'hibernate employee crud 1_v2',2000,1),
	(26,20,'hibernate employee crud 1_v2',2000,1),
	(28,20,'hibernate employee crud 1_v2',2000,1),
	(30,20,'hibernate employee crud 1_v2',2000,1),
	(32,20,'hibernate_employee',2000,1),
	(33,20,'hibernate_employee',2000,1),
	(34,20,'hibernate_employee',2000,1),
	(35,20,'hibernate_employee',2000,NULL),
	(36,20,'hibernate employee crud 1_v2',2000,NULL),
	(38,18,'auto',1500,NULL),
	(39,20,'autoPreparedStatement',2000,NULL),
	(41,18,'Batch Statement 0',1500,NULL),
	(42,18,'Batch Statement 1',1500,NULL),
	(43,18,'Batch Statement 2',1500,NULL),
	(44,18,'Batch Statement 3',1500,NULL),
	(45,18,'Batch Statement 4',1500,NULL),
	(46,18,'Batch Statement 5',1500,NULL),
	(47,18,'Batch Statement 6',1500,NULL),
	(48,18,'Batch Statement 7',1500,NULL),
	(49,18,'Batch Statement 8',1500,NULL),
	(50,18,'Batch Statement 9',1500,NULL),
	(51,20,'Batch Prepared Statement 0',2000,NULL),
	(52,20,'Batch Prepared Statement 1',2000,NULL),
	(53,20,'Batch Prepared Statement 2',2000,NULL),
	(54,20,'Batch Prepared Statement 3',2000,NULL),
	(55,20,'Batch Prepared Statement 4',2000,NULL),
	(56,20,'Batch Prepared Statement 5',2000,NULL),
	(57,20,'Batch Prepared Statement 6',2000,NULL),
	(58,20,'Batch Prepared Statement 7',2000,NULL),
	(59,20,'Batch Prepared Statement 8',2000,NULL),
	(60,20,'Batch Prepared Statement 9',2000,NULL),
	(61,20,'hibernate employee crud 1',2000,1),
	(62,20,'hibernate employee crud 1',2000,1),
	(63,20,'hibernate employee crud 1',2000,1),
	(64,20,'hibernate employee crud 1',2000,1),
	(65,20,'hibernate employee crud 1',2000,1),
	(66,20,'hibernate employee crud 1',2000,1),
	(67,20,'hibernate employee crud 1_v2',2000,1);

/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table title
# ------------------------------------------------------------

DROP TABLE IF EXISTS `title`;

CREATE TABLE `title` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;

INSERT INTO `title` (`id`, `name`, `employee_id`)
VALUES
	(1,'Title 1',1),
	(2,'Title 2',1),
	(3,'Title 3',2),
	(4,'Title 4',3),
	(5,'New Title',65),
	(6,'New Title',66),
	(7,'New Title',67),
	(8,'New Title',68);

/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping routines (PROCEDURE) for database 'example_db'
--
DELIMITER ;;

# Dump of PROCEDURE sp_get_employees
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `sp_get_employees` */;;
/*!50003 SET SESSION SQL_MODE="NO_ENGINE_SUBSTITUTION"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `sp_get_employees`(
	IN p_name VARCHAR(50)
    )
BEGIN
	SELECT * FROM employee
	WHERE NAME = p_name;
    END */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
