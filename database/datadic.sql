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
# Source for table datadic
#

DROP TABLE IF EXISTS `datadic`;
CREATE TABLE `datadic` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '数据项(包含类型)名称',
  `pId` int(10) DEFAULT NULL COMMENT '所属数据类型的id',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '对数据项的描述',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='数据字典';

#
# Dumping data for table datadic
#

LOCK TABLES `datadic` WRITE;
/*!40000 ALTER TABLE `datadic` DISABLE KEYS */;
INSERT INTO `datadic` VALUES (1,'图标',0,'iconCls');
INSERT INTO `datadic` VALUES (7,'角色',NULL,'gfgdfdddddddddddddddddddddddddddddddd');
INSERT INTO `datadic` VALUES (10,'管理员',7,'');
INSERT INTO `datadic` VALUES (29,'编程语言',NULL,'');
INSERT INTO `datadic` VALUES (30,'java',29,'');
INSERT INTO `datadic` VALUES (34,'icon-blank',1,'');
INSERT INTO `datadic` VALUES (35,'icon-add',1,'');
INSERT INTO `datadic` VALUES (36,'icon-edit',1,'');
INSERT INTO `datadic` VALUES (37,'icon-clear',1,'');
INSERT INTO `datadic` VALUES (38,'icon-remove',1,'');
INSERT INTO `datadic` VALUES (39,'icon-save',1,'');
INSERT INTO `datadic` VALUES (40,'icon-cut',1,'');
INSERT INTO `datadic` VALUES (41,'icon-ok',1,'');
INSERT INTO `datadic` VALUES (42,'icon-no',1,'');
INSERT INTO `datadic` VALUES (43,'icon-cancel',1,'');
INSERT INTO `datadic` VALUES (44,'icon-reload',1,'');
INSERT INTO `datadic` VALUES (45,'icon-search',1,'');
INSERT INTO `datadic` VALUES (46,'icon-print',1,'');
INSERT INTO `datadic` VALUES (47,'icon-help',1,'');
INSERT INTO `datadic` VALUES (48,'icon-undo',1,'');
INSERT INTO `datadic` VALUES (49,'icon-redo',1,'');
INSERT INTO `datadic` VALUES (50,'icon-back',1,'');
INSERT INTO `datadic` VALUES (51,'icon-sum',1,'');
INSERT INTO `datadic` VALUES (52,'icon-tip',1,'');
INSERT INTO `datadic` VALUES (53,'icon-filter',1,'');
INSERT INTO `datadic` VALUES (54,'icon-man',1,'');
INSERT INTO `datadic` VALUES (55,'icon-lock',1,'');
INSERT INTO `datadic` VALUES (56,'icon-more',1,'');
INSERT INTO `datadic` VALUES (57,'icon-mini-add',1,'');
INSERT INTO `datadic` VALUES (58,'icon-mini-edit',1,'');
INSERT INTO `datadic` VALUES (59,'icon-mini-refresh',1,'');
INSERT INTO `datadic` VALUES (60,'icon-large-picture',1,'');
INSERT INTO `datadic` VALUES (61,'icon-large-clipart',1,'');
INSERT INTO `datadic` VALUES (62,'icon-large-shapes',1,'');
INSERT INTO `datadic` VALUES (63,'icon-large-smartart',1,'');
INSERT INTO `datadic` VALUES (64,'icon-large-chart',1,'');
INSERT INTO `datadic` VALUES (71,'学生',7,'');
/*!40000 ALTER TABLE `datadic` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
