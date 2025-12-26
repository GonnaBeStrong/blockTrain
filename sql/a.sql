-- MySQL dump 10.13  Distrib 5.7.24, for osx11.1 (x86_64)
--
-- Host: localhost    Database: blocktrain
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `data_asset`
--

DROP TABLE IF EXISTS `data_asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_asset` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `data_name` varchar(255) DEFAULT NULL,
                              `data_type` varchar(255) DEFAULT NULL,
                              `file_hash` varchar(255) DEFAULT NULL,
                              `file_path` varchar(255) DEFAULT NULL,
                              `tx_id` varchar(255) DEFAULT NULL,
                              `upload_time` datetime(6) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_asset`
--

LOCK TABLES `data_asset` WRITE;
/*!40000 ALTER TABLE `data_asset` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `path` varchar(50) DEFAULT NULL,
                        `size` varchar(30) DEFAULT NULL,
                        `resolution` varchar(30) DEFAULT NULL,
                        `detail` varchar(30) DEFAULT NULL,
                        `upload_time` datetime DEFAULT NULL,
                        `name` varchar(30) DEFAULT NULL,
                        `from` varchar(30) DEFAULT NULL,
                        `form` varchar(30) DEFAULT NULL,
                        `longitude` varchar(30) DEFAULT NULL,
                        `latitude` varchar(30) DEFAULT NULL,
                        `lite` varchar(30) DEFAULT NULL,
                        `shoot_time` datetime DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'12','12','12','12','2027-12-25 19:37:53','1','1','1','43','33','NO1','2025-12-25 20:21:59'),(2,'sdcasd','e23','23','23','2024-12-25 19:46:00','2','2','23','21','22','NO2','2025-12-25 20:22:00'),(3,'用于测试pinia','sd','asd','ad','2025-12-25 19:47:26','asd','ads','ada','34','11','NO3','2025-12-25 20:22:01'),(4,NULL,NULL,NULL,'这是测试',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,'这是测试2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,'这是测试2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,'这是测试6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,'这是测试自增id',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
                         `user_id` int DEFAULT NULL COMMENT '用户id',
                         `data_id` int DEFAULT NULL COMMENT '数据id',
                         `buy_time` datetime DEFAULT NULL COMMENT '购买时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,1,'1970-01-01 00:00:00'),(2,1,2,'1970-01-01 00:00:00'),(3,1,3,'2025-12-25 22:12:24'),(6,1,4,'2025-12-26 12:26:38'),(7,1,5,'2025-12-26 12:30:54'),(10,1,4,'2025-12-26 12:35:19'),(11,1,9,'2025-12-26 13:43:10');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                        `name` varchar(30) DEFAULT NULL,
                        `password` varchar(30) DEFAULT NULL,
                        `a` varchar(30) DEFAULT NULL,
                        `b` varchar(30) DEFAULT NULL,
                        `c` varchar(30) NOT NULL,
                        `id` int NOT NULL AUTO_INCREMENT,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('test1','123456','null','null','null',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-26 14:36:37
