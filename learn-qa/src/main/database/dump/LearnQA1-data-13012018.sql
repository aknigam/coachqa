USE `learn-qa-1`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: learn-qa-1
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Dumping data for table `AppUserDetail`
--

LOCK TABLES `AppUserDetail` WRITE;
/*!40000 ALTER TABLE `AppUserDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `AppUserDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UserTypeMaster`
--

LOCK TABLES `UserTypeMaster` WRITE;
/*!40000 ALTER TABLE `UserTypeMaster` DISABLE KEYS */;
INSERT INTO `UserTypeMaster` VALUES (1,'application user'),(2,'administator');
/*!40000 ALTER TABLE `UserTypeMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (8,NULL,'admin@learnqa.com','admin','Admin',NULL,'Admin',2);
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionlevel`
--

LOCK TABLES `questionlevel` WRITE;
/*!40000 ALTER TABLE `questionlevel` DISABLE KEYS */;
INSERT INTO `questionlevel` VALUES (1,'Medium',1),(2,'Hard',3),(3,'Easy',1);
/*!40000 ALTER TABLE `questionlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refclassroommemberstatus`
--

LOCK TABLES `refclassroommemberstatus` WRITE;
/*!40000 ALTER TABLE `refclassroommemberstatus` DISABLE KEYS */;
INSERT INTO `refclassroommemberstatus` VALUES (1,'Pending Approval','applied for membership'),(2,'Active','Request to join is approved'),(3,'Expired','Request to join is approved'),(4,'Rejected','Rejected');
/*!40000 ALTER TABLE `refclassroommemberstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refquestionstatus`
--

LOCK TABLES `refquestionstatus` WRITE;
/*!40000 ALTER TABLE `refquestionstatus` DISABLE KEYS */;
INSERT INTO `refquestionstatus` VALUES (1,'new');
/*!40000 ALTER TABLE `refquestionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refsubject`
--

LOCK TABLES `refsubject` WRITE;
/*!40000 ALTER TABLE `refsubject` DISABLE KEYS */;
INSERT INTO `refsubject` VALUES (1,'mathematics','mathematics');
/*!40000 ALTER TABLE `refsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'python','This is an OO programming language'),(2,'java','This is an OO programming language'),(3,'javascript','This is an OO programming language'),(4,'math','This is an OO programming language'),(5,'materials','This is an OO programming language'),(6,'chemistry','This is an OO programming language'),(7,'calculus','This is an OO programming language'),(8,'python','This is an OO programming language'),(9,'python','This is an OO programming language'),(10,'python','This is an OO programming language'),(11,'geography','This is an OO programming language'),(12,'java1','This is an OO programming language'),(13,'java12','This is an OO programming language'),(14,'java22','This is an OO programming language'),(15,'java32','This is an OO programming language'),(16,'java34','This is an OO programmming language'),(17,'chemistru','This is an chemical engineering tag');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-13 22:03:58
