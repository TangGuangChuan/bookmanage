-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: bookmanage
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book_info`
--

DROP TABLE IF EXISTS `book_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `bookname` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `auther` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2tmm8w07nk0i13mqlho6jdkcf` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_info`
--

LOCK TABLES `book_info` WRITE;
/*!40000 ALTER TABLE `book_info` DISABLE KEYS */;
INSERT INTO `book_info` VALUES (1,NULL,NULL,NULL,NULL,'九阳','121','123','倦收天',1),(2,NULL,NULL,NULL,NULL,'僧与','123','123','惋红曲',1),(3,'2021-01-25 21:30:48','admin','2021-01-25 21:30:47','admin','萨达','12','12','112',1),(4,'2021-01-25 21:34:49','admin','2021-01-25 21:34:49','admin','1','123','1','22',2),(5,'2021-01-25 21:38:34','admin','2021-01-25 21:38:34','admin','3','12  ','12','111',2),(6,NULL,'admin',NULL,NULL,'11','123','123','233',3);
/*!40000 ALTER TABLE `book_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_type`
--

DROP TABLE IF EXISTS `book_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `type_code` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f7q91feswhdi2ge7ax7mpkitk` (`type_code`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_type`
--

LOCK TABLES `book_type` WRITE;
/*!40000 ALTER TABLE `book_type` DISABLE KEYS */;
INSERT INTO `book_type` VALUES (1,NULL,NULL,NULL,NULL,'01','历史'),(2,NULL,NULL,NULL,NULL,'02','军事'),(3,NULL,NULL,NULL,NULL,'03','科技');
/*!40000 ALTER TABLE `book_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_permission`
--

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` VALUES (1,NULL,NULL,NULL,NULL,_binary '\0','用户查询','user:retrieve','menu',''),(2,NULL,NULL,NULL,NULL,_binary '\0','用户添加','user:creat','button',''),(3,NULL,NULL,NULL,NULL,_binary '\0','用户删除','user:delete','button',''),(4,NULL,NULL,NULL,NULL,_binary '\0','用户修改','user:update','button',NULL),(5,NULL,'',NULL,NULL,NULL,'管理员查询','admin:retrieve','menu',''),(6,NULL,'',NULL,NULL,NULL,'管理员查询','admin:creat','button',''),(7,NULL,'',NULL,NULL,NULL,'管理员删除','admin:delete','button',''),(8,NULL,'',NULL,NULL,NULL,'管理员修改','admin:update','button',''),(9,NULL,'',NULL,NULL,NULL,'超级管理员查询','system:creat','button',''),(10,NULL,'',NULL,NULL,NULL,'超级管理员删除','system:delete','button',''),(11,NULL,'',NULL,NULL,NULL,'超级管理员修改','system:update','button',''),(12,NULL,'',NULL,NULL,NULL,'超级管理员修改','system:retrieve','menu','');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (2,NULL,NULL,NULL,NULL,'管理员',_binary '\0','admin'),(3,NULL,NULL,NULL,NULL,'普通用户',_binary '\0','user'),(1,NULL,NULL,NULL,NULL,'超级管理员',_binary '\0','system');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKomxrs8a388bknvhjokh440waq` (`permission_id`),
  KEY `FK9q28ewrhntqeipl1t04kh1be7` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_permission`
--

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` VALUES (1,9),(1,10),(1,11),(1,12),(2,5),(2,6),(2,7),(2,8),(3,1),(3,2),(3,3),(3,4);
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (2,NULL,NULL,'2021-01-20 21:32:57','admin','86a5e88de4dd9b84595b7edc84323a71','8d78869f470951332959580424d4bf4f','admin',_binary '\0','616934150@qq.com11'),(3,NULL,NULL,NULL,NULL,'86a5e88de4dd9b84595b7edc84323a71','8d78869f470951332959580424d4bf4f','user',_binary '\0',''),(1,NULL,NULL,NULL,NULL,'86a5e88de4dd9b84595b7edc84323a71','8d78869f470951332959580424d4bf4f','system',_binary '\0',''),(4,'2021-01-18 01:01:05','user','2021-01-18 01:01:05','user','e63e9ab4a3457f1674202de3fe04dbdc','6a22254524f30e8dc484ceedaafef528','test',_binary '\0','1212'),(5,'2021-01-18 01:26:16',NULL,'2021-01-18 01:26:16',NULL,'a2777ed261253db2e8f43c9a4e974035','f58394d201789b70cc33534f920ce6bb','test1',_binary '\0','123'),(6,'2021-01-18 21:10:36',NULL,'2021-01-18 21:10:36',NULL,'ca3cf476c5188b145f4c3e0cf6cb9bb3','c6702095a503aa0ee59fd01e579ec8a7','test2',_binary '\0','2223'),(7,'2021-01-20 20:27:58',NULL,'2021-01-20 20:27:58',NULL,'5e5df15ba7a562e1358aa213d7fd8f01','62f83a5e0f36f17b7f7479d56f838e60','test3',_binary '\0','1233333'),(8,'2021-01-20 20:36:56','','2021-01-20 20:36:56','','3c86e14825c262173a157fd0515101b8','42b401f2ecd6ccb07c4e9ac9b37da21a','test4',_binary '\0','12333334'),(9,'2021-01-20 20:38:45','','2021-01-20 20:38:45','','e096ca578c94d8053edfd5f81e2d6929','81eb41ced487f5429c0c73d2dabc8232','test5',_binary '\0','123333345'),(10,'2021-01-20 20:41:24','','2021-01-20 20:41:24','','41c08d79b0417c40974a45f2e58eed2f','e8200f2d8157c9afa39617d5cc67b939','test6',_binary '\0','1233333456'),(11,'2021-01-20 20:43:32','','2021-01-20 20:43:32','','e6692af367bdab40c6c3e074a10b7cd3','d1dbe161c59c6a9d173d7fc29f186216','test0',_binary '\0','12300'),(13,'2021-01-25 21:51:45',NULL,'2021-01-25 21:51:45',NULL,'83bea9b40faa52eb519f18e7de8a0044','701ff3b2729c8b38aac7b875986954af','惋红曲',_binary '\0','616934150@qq.com13'),(12,'2021-01-20 21:04:36',NULL,'2021-01-20 21:24:54','','b46ac88bcdc78f32c4b0b1f1f885e07a','a8f9d4ba93048da6856e4e7405cc0390','test7',_binary '\0','616934151@qq.com');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `uid` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  KEY `FKput17v9wwg8wiukw8ykroaaag` (`uid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(1,2),(1,3),(2,2),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-26 17:17:47
