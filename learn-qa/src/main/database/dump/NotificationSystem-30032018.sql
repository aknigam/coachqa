CREATE DATABASE  IF NOT EXISTS `notificationsystem` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `notificationsystem`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: notificationsystem
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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `EventId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `EventTypeId` int(10) unsigned NOT NULL,
  `EventSourceId` int(10) unsigned NOT NULL,
  `LatestEventDate` datetime DEFAULT NULL,
  `ExpirationDate` datetime DEFAULT NULL,
  `EventTriggeredByUser` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`EventId`),
  UNIQUE KEY `source_type_unique_key` (`EventTypeId`,`EventSourceId`),
  KEY `Event_FKIndex1` (`EventTypeId`),
  KEY `eventAppUser___fk` (`EventTriggeredByUser`),
  CONSTRAINT `eventAppUser___fk` FOREIGN KEY (`EventTriggeredByUser`) REFERENCES `learn-qa`.`appuser` (`AppUserId`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`EventTypeId`) REFERENCES `eventtype` (`EventTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (29,1,1000,'2015-09-11 00:00:00','2015-09-11 00:00:00',NULL),(30,5,6,'2016-02-12 00:00:00','2016-02-12 00:00:00',1),(31,5,5,'2016-02-13 00:00:00','2016-02-13 00:00:00',NULL),(34,9,45,'2016-12-29 00:00:00','2016-12-29 00:00:00',NULL),(36,8,45,'2016-12-29 00:00:00','2016-12-29 00:00:00',NULL),(37,10,1,'2017-04-11 00:00:00','2017-04-11 00:00:00',NULL),(38,7,231,'2017-04-13 00:00:00','2017-04-13 00:00:00',NULL),(39,7,232,'2017-04-17 00:00:00','2017-04-17 00:00:00',NULL),(40,10,101,'2017-11-22 00:00:00','2017-11-22 00:00:00',NULL),(41,7,234,'2017-11-22 00:00:00','2017-11-22 00:00:00',NULL),(42,7,235,'2017-11-24 00:00:00','2017-11-24 00:00:00',NULL),(43,7,237,'2017-11-27 00:00:00','2017-11-27 00:00:00',NULL),(44,7,238,'2017-11-27 00:00:00','2017-11-27 00:00:00',NULL),(45,7,241,'2017-11-27 00:00:00','2017-11-27 00:00:00',NULL),(46,7,242,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(47,7,244,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(48,10,244,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(49,10,197,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(50,7,249,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(51,7,250,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(52,10,250,'2017-11-28 00:00:00','2017-11-28 00:00:00',NULL),(53,7,257,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(54,7,258,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(55,7,260,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(56,7,261,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(57,7,262,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(58,7,263,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(59,7,264,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(60,7,265,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(61,7,266,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(62,7,267,'2017-11-29 00:00:00','2017-11-29 00:00:00',NULL),(63,7,268,'2017-11-30 00:00:00','2017-11-30 00:00:00',NULL),(64,7,269,'2017-11-30 00:00:00','2017-11-30 00:00:00',NULL),(65,10,267,'2017-12-01 00:00:00','2017-12-01 00:00:00',NULL),(66,7,278,'2017-12-29 00:00:00','2017-12-29 00:00:00',NULL),(67,10,278,'2017-12-29 00:00:00','2017-12-29 00:00:00',NULL),(68,7,288,'2017-12-29 00:00:00','2017-12-29 00:00:00',NULL),(69,7,290,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(70,10,290,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(71,7,300,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(72,7,301,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(73,7,302,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(74,7,306,'2018-01-06 00:00:00','2018-01-06 00:00:00',NULL),(75,7,309,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(76,7,310,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(77,10,310,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(78,7,312,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(79,10,306,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(80,7,315,'2018-01-07 00:00:00','2018-01-07 00:00:00',NULL),(81,7,316,'2018-01-12 00:00:00','2018-01-12 00:00:00',NULL),(82,7,319,'2018-01-13 00:00:00','2018-01-13 00:00:00',NULL),(83,10,319,'2018-01-13 00:00:00','2018-01-13 00:00:00',NULL);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventregistration`
--

DROP TABLE IF EXISTS `eventregistration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventregistration` (
  `EventRegistrationId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(10) unsigned NOT NULL,
  `EventSourceId` int(10) unsigned NOT NULL,
  `EventTypeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`EventRegistrationId`),
  UNIQUE KEY `user_event_ukey` (`UserId`,`EventSourceId`),
  KEY `eventregistration_ibfk_1_idx` (`EventSourceId`),
  KEY `eventtype___fk` (`EventTypeId`),
  CONSTRAINT `eventid_fk` FOREIGN KEY (`EventSourceId`) REFERENCES `event` (`EventId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `eventtype___fk` FOREIGN KEY (`EventTypeId`) REFERENCES `eventtype` (`EventTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventregistration`
--

LOCK TABLES `eventregistration` WRITE;
/*!40000 ALTER TABLE `eventregistration` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventregistration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventtype`
--

DROP TABLE IF EXISTS `eventtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventtype` (
  `EventTypeId` int(10) unsigned NOT NULL,
  `EventType` varchar(20) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `StageOneMessage` varchar(4000) DEFAULT NULL,
  `StageTwoMessage` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`EventTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventtype`
--

LOCK TABLES `eventtype` WRITE;
/*!40000 ALTER TABLE `eventtype` DISABLE KEYS */;
INSERT INTO `eventtype` VALUES (1,'Question Answered',NULL,NULL,NULL),(2,'Question Deleted',NULL,NULL,NULL),(3,'Question Voted',NULL,NULL,NULL),(4,'Question viewed',NULL,NULL,NULL),(5,'Membership request',NULL,NULL,NULL),(6,'Question updated',NULL,NULL,NULL),(7,'Question posted',NULL,NULL,NULL),(8,'POST_APPROVED',NULL,NULL,NULL),(9,'POST_REJECTED',NULL,NULL,NULL),(10,'ANSWER_POSTED',NULL,NULL,NULL);
/*!40000 ALTER TABLE `eventtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificationtype`
--

DROP TABLE IF EXISTS `notificationtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notificationtype` (
  `NotificationTypeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NotificationType` varchar(20) NOT NULL,
  PRIMARY KEY (`NotificationTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificationtype`
--

LOCK TABLES `notificationtype` WRITE;
/*!40000 ALTER TABLE `notificationtype` DISABLE KEYS */;
INSERT INTO `notificationtype` VALUES (1,'email'),(2,'app_notification'),(3,'log_notification');
/*!40000 ALTER TABLE `notificationtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usereventnotification`
--

DROP TABLE IF EXISTS `usereventnotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usereventnotification` (
  `UserEventNotificationId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserEventNotificationStatusId` int(10) unsigned NOT NULL,
  `NotificationTypeId` int(10) unsigned NOT NULL,
  `EventId` int(10) unsigned NOT NULL,
  `UserId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`UserEventNotificationId`),
  UNIQUE KEY `type_event_user_ukey` (`NotificationTypeId`,`EventId`,`UserId`),
  KEY `UserEvent_FKIndex1` (`UserEventNotificationStatusId`),
  KEY `UserEvent_FKIndex2` (`EventId`),
  KEY `UserEvent_FKIndex3` (`NotificationTypeId`),
  CONSTRAINT `usereventnotification_ibfk_1` FOREIGN KEY (`UserEventNotificationStatusId`) REFERENCES `usereventstatus` (`UserEventNotificationStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usereventnotification_ibfk_2` FOREIGN KEY (`EventId`) REFERENCES `event` (`EventId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usereventnotification_ibfk_3` FOREIGN KEY (`NotificationTypeId`) REFERENCES `notificationtype` (`NotificationTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=510 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usereventnotification`
--

LOCK TABLES `usereventnotification` WRITE;
/*!40000 ALTER TABLE `usereventnotification` DISABLE KEYS */;
INSERT INTO `usereventnotification` VALUES (459,1,2,29,2),(460,2,1,34,4),(462,2,1,36,4),(463,1,1,38,8),(464,1,1,39,8),(465,1,1,40,8),(466,1,1,41,8),(467,1,1,42,8),(468,1,1,43,8),(469,1,1,44,8),(470,1,1,45,8),(471,1,1,46,8),(472,1,1,47,8),(473,2,1,48,8),(474,2,1,49,8),(475,1,1,50,8),(476,1,1,51,8),(477,2,1,52,8),(478,1,1,53,8),(479,1,1,54,8),(480,1,1,55,8),(481,1,1,56,8),(482,1,1,57,8),(483,1,1,58,8),(484,1,1,59,8),(485,2,1,62,8),(486,2,1,62,6),(487,2,1,63,8),(488,2,1,64,8),(489,2,1,65,8),(490,2,1,66,8),(491,2,1,66,6),(492,2,1,67,8),(493,2,1,68,8),(494,2,1,68,6),(495,2,1,69,6),(496,2,1,70,8),(497,2,1,71,6),(498,2,1,72,6),(499,2,1,73,6),(500,2,1,74,6),(501,2,1,75,6),(502,2,1,76,6),(503,2,1,77,8),(504,2,1,78,6),(505,2,1,79,8),(506,2,1,80,6),(507,2,1,81,6),(508,2,1,82,6),(509,2,1,83,8);
/*!40000 ALTER TABLE `usereventnotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usereventstatus`
--

DROP TABLE IF EXISTS `usereventstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usereventstatus` (
  `UserEventNotificationStatusId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserEventStatusName` varchar(20) NOT NULL,
  PRIMARY KEY (`UserEventNotificationStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usereventstatus`
--

LOCK TABLES `usereventstatus` WRITE;
/*!40000 ALTER TABLE `usereventstatus` DISABLE KEYS */;
INSERT INTO `usereventstatus` VALUES (1,'new'),(2,'notified'),(3,'notification failed'),(4,'acknowledged');
/*!40000 ALTER TABLE `usereventstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usernotificationpreference`
--

DROP TABLE IF EXISTS `usernotificationpreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usernotificationpreference` (
  `UserNotificationPreferenceId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NotificationTypeId` int(10) unsigned NOT NULL,
  `UserId` int(10) unsigned NOT NULL,
  `IsEnabled` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserNotificationPreferenceId`),
  KEY `UserNotificationPreference_FKIndex1` (`NotificationTypeId`),
  CONSTRAINT `usernotificationpreference_ibfk_1` FOREIGN KEY (`NotificationTypeId`) REFERENCES `notificationtype` (`NotificationTypeId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usernotificationpreference`
--

LOCK TABLES `usernotificationpreference` WRITE;
/*!40000 ALTER TABLE `usernotificationpreference` DISABLE KEYS */;
INSERT INTO `usernotificationpreference` VALUES (28,3,2,1),(29,1,0,1),(30,1,1,1),(31,1,4,1);
/*!40000 ALTER TABLE `usernotificationpreference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'notificationsystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-30 22:50:34
