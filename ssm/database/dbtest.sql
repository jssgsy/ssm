# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: ssme
# ------------------------------------------------------
# Server version 5.5.20

#
# Source for table dbtest
#

DROP TABLE IF EXISTS `dbtest`;
CREATE TABLE `dbtest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `parentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Dumping data for table dbtest
#

LOCK TABLES `dbtest` WRITE;
/*!40000 ALTER TABLE `dbtest` DISABLE KEYS */;
INSERT INTO `dbtest` VALUES (1,'浙工大','5000','江干区','');
INSERT INTO `dbtest` VALUES (2,'理学院','1000','西湖区','1');
INSERT INTO `dbtest` VALUES (3,'艺术学院','1234','上城区','1');
INSERT INTO `dbtest` VALUES (4,'数学实验室','147','细化去','2');
INSERT INTO `dbtest` VALUES (5,'画画实验室','159','结案件','3');
INSERT INTO `dbtest` VALUES (6,'工程学院','3698','借记卡','1');
INSERT INTO `dbtest` VALUES (7,'几何实验室','45','级的','4');
INSERT INTO `dbtest` VALUES (8,'代数实验室','478','急非','2');
INSERT INTO `dbtest` VALUES (9,'拓扑实验室','479','菲菲','4');
/*!40000 ALTER TABLE `dbtest` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
