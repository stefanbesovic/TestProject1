-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: playlistdb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'rok'),(2,'dens'),(3,'pop'),(4,'hiphop');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `channel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'kanal1'),(2,'kanal2'),(3,'kanal');
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_playlist`
--

DROP TABLE IF EXISTS `channel_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `channel_playlist` (
  `channel_id` bigint NOT NULL,
  `playlist_id` bigint NOT NULL,
  `position` int NOT NULL,
  PRIMARY KEY (`channel_id`,`playlist_id`),
  KEY `FK2w7k70qfskumlt16dp094oka9` (`playlist_id`),
  CONSTRAINT `FK2w7k70qfskumlt16dp094oka9` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `FKg04gea04vttv7w5kitimvwiiv` FOREIGN KEY (`channel_id`) REFERENCES `channel` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_playlist`
--

LOCK TABLES `channel_playlist` WRITE;
/*!40000 ALTER TABLE `channel_playlist` DISABLE KEYS */;
INSERT INTO `channel_playlist` VALUES (1,1,1),(1,2,4),(1,3,2),(1,4,3),(2,1,2),(2,2,3),(2,3,1),(2,4,4),(3,1,3),(3,2,2),(3,3,1);
/*!40000 ALTER TABLE `channel_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlbi6wsq41356go2ki0yirfixo` (`user_id`),
  CONSTRAINT `FKlbi6wsq41356go2ki0yirfixo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'Playlist1',1),(2,'Playlist2',1),(3,'Playlist3',2),(4,'Playlist4',2);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_categories`
--

DROP TABLE IF EXISTS `playlist_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_categories` (
  `playlist_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`category_id`,`playlist_id`),
  KEY `FK19r2sqa7biff9yowg7bivr0od` (`playlist_id`),
  CONSTRAINT `FK19r2sqa7biff9yowg7bivr0od` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `FK59f0lxt3dpxfysd0bbbxq6vg3` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_categories`
--

LOCK TABLES `playlist_categories` WRITE;
/*!40000 ALTER TABLE `playlist_categories` DISABLE KEYS */;
INSERT INTO `playlist_categories` VALUES (1,2),(1,3),(2,1),(2,2),(3,2),(3,4),(4,1),(4,3),(4,4);
/*!40000 ALTER TABLE `playlist_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_video`
--

DROP TABLE IF EXISTS `playlist_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_video` (
  `playlist_id` bigint NOT NULL,
  `video_id` bigint NOT NULL,
  `position` int NOT NULL,
  PRIMARY KEY (`playlist_id`,`video_id`),
  KEY `FKkxjstj24wj73qx3q9uocwfxn9` (`video_id`),
  CONSTRAINT `FK8nwqvhf8moddp42wr8phs9lut` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `FKkxjstj24wj73qx3q9uocwfxn9` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_video`
--

LOCK TABLES `playlist_video` WRITE;
/*!40000 ALTER TABLE `playlist_video` DISABLE KEYS */;
INSERT INTO `playlist_video` VALUES (1,1,3),(1,2,4),(1,3,1),(1,4,2),(2,1,2),(2,2,1),(3,1,2),(3,3,1),(4,2,3),(4,3,1),(4,4,2);
/*!40000 ALTER TABLE `playlist_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privilege` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
INSERT INTO `role_privilege` VALUES (1,'USER_READ'),(2,'USER_WRITE'),(3,'PLAYLIST_WRITE'),(4,'PLAYLIST_READ');
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Stefan Besovic','$2a$10$CcCKc8/IX2OGDc.ILSi1/evjLkTFVbfb/rufa7oGz5f1ueDUIY3sS','stefan'),(2,'Marko Markovic','$2a$10$rvixhh0MTnfF8BayUN258.0QPKOvm1Hj2maDNcsMxBCDzwgneesBu','marko'),(3,'Milanko Markovic','$2a$10$RQUyoKV3vcK4Qoxe0wnB1e7IamPVRitdRGBCLR6I/GhnpL3Kp0h/2','srboja');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'ADMIN'),(2,'LISTENER'),(3,'MODERATOR');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role_privileges`
--

DROP TABLE IF EXISTS `user_role_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role_privileges` (
  `user_role_id` bigint NOT NULL,
  `privileges_id` bigint NOT NULL,
  PRIMARY KEY (`user_role_id`,`privileges_id`),
  KEY `FK6onikd3vy9hv0rb30y93lvwg0` (`privileges_id`),
  CONSTRAINT `FK6onikd3vy9hv0rb30y93lvwg0` FOREIGN KEY (`privileges_id`) REFERENCES `role_privilege` (`id`),
  CONSTRAINT `FKo5ulh7fpyh8gx264drtpuqds9` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role_privileges`
--

LOCK TABLES `user_role_privileges` WRITE;
/*!40000 ALTER TABLE `user_role_privileges` DISABLE KEYS */;
INSERT INTO `user_role_privileges` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_role_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user_roles`
--

DROP TABLE IF EXISTS `user_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user_roles` (
  `user_id` bigint NOT NULL,
  `user_roles_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`user_roles_id`),
  KEY `FK60vm96njnava0xkygkeuekglu` (`user_roles_id`),
  CONSTRAINT `FK60vm96njnava0xkygkeuekglu` FOREIGN KEY (`user_roles_id`) REFERENCES `user_role` (`id`),
  CONSTRAINT `FKhxmdhadm01kwqo2lvyv8l8ho7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user_roles`
--

LOCK TABLES `user_user_roles` WRITE;
/*!40000 ALTER TABLE `user_user_roles` DISABLE KEYS */;
INSERT INTO `user_user_roles` VALUES (1,1),(2,2),(2,3),(3,3);
/*!40000 ALTER TABLE `user_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'video1'),(2,'video2'),(3,'video3'),(4,'video4');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_categories`
--

DROP TABLE IF EXISTS `video_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_categories` (
  `video_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`category_id`,`video_id`),
  KEY `FKht5aytqrnymhibb70cdjwtorv` (`video_id`),
  CONSTRAINT `FKd2s1f7vumacvi2s352rf8mr0b` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKht5aytqrnymhibb70cdjwtorv` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_categories`
--

LOCK TABLES `video_categories` WRITE;
/*!40000 ALTER TABLE `video_categories` DISABLE KEYS */;
INSERT INTO `video_categories` VALUES (1,1),(1,2),(2,2),(3,1),(3,4),(4,1),(4,2),(4,3);
/*!40000 ALTER TABLE `video_categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-24 13:06:38
