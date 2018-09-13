CREATE DATABASE  IF NOT EXISTS `notificationsystem-1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `notificationsystem-1`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: notificationsystem-1
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-13 22:11:18
