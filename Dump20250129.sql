CREATE DATABASE  IF NOT EXISTS `pal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pal`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: pal
-- ------------------------------------------------------
-- Server version	8.0.40-0ubuntu0.24.04.1

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `book_title` varchar(255) NOT NULL COMMENT 'Book Title',
  `book_author` varchar(255) NOT NULL COMMENT 'Book Author',
  `book_publisher` varchar(255) NOT NULL COMMENT 'Book Publisher',
  `book_year` int NOT NULL COMMENT 'Book Year',
  `book_updated_time` datetime NOT NULL COMMENT 'Book Updated Time',
  `book_create_time` datetime NOT NULL COMMENT 'Book Create Time',
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('2070408507','Le Petit Prince','Antoine de Saint-Exupéry','Gallimard',1999,'2025-01-16 16:00:00','2025-01-16 16:00:00');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `comment_content` text NOT NULL COMMENT 'Comment Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `favorite_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `favorite_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list`
--

DROP TABLE IF EXISTS `list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list` (
  `list_id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `user_id` int NOT NULL COMMENT 'User ID',
  `list_name` varchar(255) NOT NULL COMMENT 'List Name',
  `list_description` varchar(255) DEFAULT NULL COMMENT 'List Description',
  `list_type` varchar(255) NOT NULL COMMENT 'List Type',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`list_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `list_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list`
--

LOCK TABLES `list` WRITE;
/*!40000 ALTER TABLE `list` DISABLE KEYS */;
INSERT INTO `list` VALUES (1,1,'Ma liste','Ma liste de livre','list','2025-01-16 16:00:00'),(2,2,'Ma liste','Ma liste de livre','list','2025-01-16 16:00:00'),(3,3,'Ma liste','Ma liste de livre','list','2025-01-16 16:00:00');
/*!40000 ALTER TABLE `list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_book`
--

DROP TABLE IF EXISTS `list_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_book` (
  `list_id` int NOT NULL COMMENT 'List ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`list_id`,`isbn`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `list_book_ibfk_1` FOREIGN KEY (`list_id`) REFERENCES `list` (`list_id`),
  CONSTRAINT `list_book_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_book`
--

LOCK TABLES `list_book` WRITE;
/*!40000 ALTER TABLE `list_book` DISABLE KEYS */;
INSERT INTO `list_book` VALUES (1,'2070408507','2025-01-16 16:00:00'),(2,'2070408507','2025-01-16 16:00:00'),(3,'2070408507','2025-01-16 16:00:00');
/*!40000 ALTER TABLE `list_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note` (
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `user_id` int NOT NULL COMMENT 'User ID',
  `note_content` text NOT NULL COMMENT 'Note Content',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`isbn`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `note_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `user_name` varchar(255) NOT NULL COMMENT 'User Name',
  `user_mail` varchar(255) NOT NULL COMMENT 'User Mail',
  `user_password` varchar(255) NOT NULL COMMENT 'User Password',
  `user_last_login` datetime NOT NULL COMMENT 'Last Login Time',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','gabriel.henin@gmail.com','password_lol','2025-01-16 16:00:00','2025-01-16 16:00:00'),(2,'Gabriel','gabriel.henin@gmail.com','password_lol','2025-01-16 16:00:00','2025-01-16 16:00:00'),(3,'Laura','gabriel.henin@gmail.com','password_lol','2025-01-16 16:00:00','2025-01-16 16:00:00'),(4,'new_user','mail','pass','2025-01-16 16:00:00','2025-01-16 16:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `user_id` int NOT NULL COMMENT 'User ID',
  `isbn` varchar(255) NOT NULL COMMENT 'ISBN',
  `create_time` datetime NOT NULL COMMENT 'Create Time',
  PRIMARY KEY (`user_id`,`isbn`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `wishlist_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-29 10:56:20
