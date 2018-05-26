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
# Source for table menu
#

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `level` int(5) DEFAULT NULL COMMENT '菜单级别',
  `url` varchar(50) DEFAULT NULL COMMENT '点击菜单触发的url',
  `px` int(5) DEFAULT '1' COMMENT '菜单显示的顺序(默认为1)',
  `pId` varchar(10) DEFAULT NULL COMMENT '父菜单的id',
  `iconCls` varchar(20) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

#
# Dumping data for table menu
#

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'系统设置',NULL,'',1,'','icon-save');
INSERT INTO `menu` VALUES (2,'菜单管理',NULL,'menu/toTree',1,'1','icon-large-smartart');
INSERT INTO `menu` VALUES (3,'数据字典',0,'dataDic/toList',2,'1','icon-edit');
INSERT INTO `menu` VALUES (12,'test',NULL,'',3,NULL,'icon-help');
INSERT INTO `menu` VALUES (18,'test2',NULL,'',2,'12','icon-lock');
INSERT INTO `menu` VALUES (19,'test1',NULL,'',1,'12','icon-filter');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
