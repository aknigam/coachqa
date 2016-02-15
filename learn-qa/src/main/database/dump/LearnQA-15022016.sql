CREATE DATABASE  IF NOT EXISTS `learn-qa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `learn-qa`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: learn-qa
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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `AnswerId` int(10) unsigned NOT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`AnswerId`),
  KEY `Answer_FKIndex1` (`QuestionId`),
  KEY `answerPost_fk` (`AnswerId`),
  CONSTRAINT `answerPost_fk` FOREIGN KEY (`AnswerId`) REFERENCES `post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (118,45),(119,45),(120,45),(121,45),(122,45),(123,50),(124,51),(125,54),(126,54),(127,56),(133,70),(128,84),(129,84),(130,84),(131,84),(132,84),(134,89),(135,89),(136,89),(137,96),(139,96),(138,100),(140,100),(141,100),(142,100),(143,100),(164,102),(144,103),(145,104),(146,104),(158,156),(159,156),(160,156),(161,156),(162,156),(163,156),(167,156),(174,156),(166,165),(171,170),(172,170);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answercomment`
--

DROP TABLE IF EXISTS `answercomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answercomment` (
  `AnswerCommentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnswerId` int(10) unsigned NOT NULL,
  `AnswerComment` longtext NOT NULL,
  PRIMARY KEY (`AnswerCommentId`),
  KEY `AnswerComment_FKIndex1` (`AnswerId`),
  CONSTRAINT `answercomment_ibfk_1` FOREIGN KEY (`AnswerId`) REFERENCES `answer` (`AnswerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answercomment`
--

LOCK TABLES `answercomment` WRITE;
/*!40000 ALTER TABLE `answercomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `answercomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuser` (
  `AppUserId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserReputationId` int(10) unsigned DEFAULT NULL,
  `Email` varchar(20) NOT NULL,
  `Pasword` varchar(20) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `MiddleName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) NOT NULL,
  PRIMARY KEY (`AppUserId`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `AppUser_FKIndex1` (`UserReputationId`),
  CONSTRAINT `appuser_ibfk_1` FOREIGN KEY (`UserReputationId`) REFERENCES `userreputation` (`UserReputationId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (1,NULL,'anigam@expedia.com','pass','Anand','K','nigam'),(2,NULL,'anigam@gmail.com','pass','Anand','K','nigam'),(4,NULL,'anand@gmail.com','pass','Anand','K','nigam'),(5,NULL,'priya@yahoo.com','password','priya','','Nigam'),(6,NULL,'a.nigam@cvent.com','password','Anand','Kumar','Nigam'),(7,NULL,'pnath@opshub.com','password','Pushkar','Nath','Jha');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `ClassroomId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MinReputationToJoinId` int(10) unsigned DEFAULT NULL,
  `ClassOwner` int(10) unsigned NOT NULL,
  `ClassName` varchar(20) CHARACTER SET dec8 NOT NULL,
  `IsPublic` tinyint(1) NOT NULL,
  `LastUpdateDate` datetime NOT NULL,
  `Description` varchar(200) NOT NULL,
  PRIMARY KEY (`ClassroomId`),
  UNIQUE KEY `ClassName_UNIQUE` (`ClassName`),
  KEY `Classroom_FKIndex1` (`ClassOwner`),
  KEY `Classroom_FKIndex2` (`MinReputationToJoinId`),
  CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`ClassOwner`) REFERENCES `appuser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroom_ibfk_2` FOREIGN KEY (`MinReputationToJoinId`) REFERENCES `userreputation` (`UserReputationId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,NULL,6,'Physics class',1,'2015-10-02 00:00:00','Physics class'),(4,NULL,6,'Chemistry class',0,'2015-10-02 00:00:00','Chemistry class'),(5,NULL,6,'Maths class',1,'2016-02-12 00:00:00','mathematics classes'),(6,NULL,6,'Maths class by Anand',1,'2016-02-12 00:00:00','mathematics classes');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroommember`
--

DROP TABLE IF EXISTS `classroommember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroommember` (
  `ClassroomMemberId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AppUserId` int(10) unsigned NOT NULL,
  `ClassroomId` int(10) unsigned NOT NULL,
  `Status` int(10) unsigned NOT NULL,
  `MembershipStartDate` datetime NOT NULL,
  `MembershipExpirartionDate` datetime NOT NULL,
  `MembershipRequestDate` datetime NOT NULL,
  `Comments` varchar(150) NOT NULL,
  PRIMARY KEY (`ClassroomMemberId`),
  UNIQUE KEY `ClassroomMmebrUKey` (`ClassroomId`,`AppUserId`),
  KEY `ClassroomMember_FKIndex1` (`ClassroomId`),
  KEY `ClassroomMember_FKIndex2` (`AppUserId`),
  KEY `classroommember_fk2` (`ClassroomId`),
  KEY `classroommember_fk3` (`Status`),
  CONSTRAINT `classroommember_fk2` FOREIGN KEY (`ClassroomId`) REFERENCES `classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroommember_fk3` FOREIGN KEY (`Status`) REFERENCES `refclassroommemberstatus` (`RefClassroomMemberStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroommember_ibfk_2` FOREIGN KEY (`AppUserId`) REFERENCES `appuser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroommember`
--

LOCK TABLES `classroommember` WRITE;
/*!40000 ALTER TABLE `classroommember` DISABLE KEYS */;
INSERT INTO `classroommember` VALUES (1,1,1,2,'2015-10-02 00:00:00','2115-10-02 00:00:00','2015-10-02 00:00:00','Added the member'),(5,7,1,2,'2015-10-03 00:00:00','2115-10-03 00:00:00','2015-10-02 00:00:00','Added the member'),(6,6,1,1,'2016-02-12 00:00:00','2017-02-12 00:00:00','2016-02-12 00:00:00','\"Please add me in the class\"'),(8,6,5,1,'2016-02-12 00:00:00','2017-02-12 00:00:00','2016-02-12 00:00:00','\"Please add me in the class\"'),(11,6,6,1,'2016-02-12 00:00:00','2017-02-12 00:00:00','2016-02-12 00:00:00','\"Please add me in the class\"');
/*!40000 ALTER TABLE `classroommember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `PostId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PostType` tinyint(4) NOT NULL,
  `NoOfViews` int(10) NOT NULL DEFAULT '0',
  `PostedBy` int(10) unsigned NOT NULL,
  `PostDate` datetime NOT NULL,
  `Votes` int(10) NOT NULL DEFAULT '0',
  `Content` text NOT NULL,
  PRIMARY KEY (`PostId`),
  KEY `fk_post_user` (`PostedBy`),
  CONSTRAINT `fk_post_user` FOREIGN KEY (`PostedBy`) REFERENCES `appuser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (45,1,0,4,'2015-01-18 11:11:43',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(47,1,0,4,'2015-01-18 13:48:05',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div><p>\r\n$$\r\n\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a}\r\n$$\r\n</p></div>'),(49,1,0,4,'2015-01-26 21:41:58',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(50,1,0,4,'2015-01-26 21:50:10',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(51,1,0,4,'2015-01-26 22:27:16',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(53,1,0,1,'2015-06-12 11:03:37',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(54,1,0,1,'2015-06-12 11:04:25',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(55,1,0,1,'2015-06-12 11:17:03',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(56,1,0,1,'2015-06-12 16:21:42',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$   $\\sqrt[2]{3}$</div>'),(57,1,0,1,'2015-06-13 16:57:04',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(58,1,0,1,'2015-06-13 17:09:56',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(59,1,0,1,'2015-06-13 17:14:54',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(60,1,0,1,'2015-06-13 17:19:08',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(61,1,0,1,'2015-06-16 16:05:30',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(63,1,0,1,'2015-06-16 16:08:37',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(65,1,0,1,'2015-06-16 16:11:12',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(66,1,0,1,'2015-06-16 16:12:02',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(67,1,0,1,'2015-06-16 16:15:56',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(68,1,0,1,'2015-06-16 16:46:28',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(69,1,0,1,'2015-06-16 19:17:20',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(70,1,1,1,'2015-06-16 19:18:54',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(71,1,0,1,'2015-06-16 19:24:46',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(72,1,0,1,'2015-06-16 19:27:23',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(73,1,0,1,'2015-06-16 19:34:01',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(74,1,0,1,'2015-06-16 19:47:22',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(75,1,0,1,'2015-06-16 19:57:51',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(76,1,0,1,'2015-06-16 20:16:49',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(78,1,1,1,'2015-06-16 20:18:55',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(79,1,9,1,'2015-06-16 20:19:18',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(80,1,8,1,'2015-06-19 18:46:38',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(82,1,0,1,'2015-06-19 18:47:57',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(83,1,0,1,'2015-06-19 18:48:37',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(84,1,0,1,'2015-06-19 18:49:21',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(85,1,0,1,'2015-06-20 21:54:36',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(86,1,0,1,'2015-06-20 22:00:49',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(87,1,0,1,'2015-06-20 22:06:53',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(88,1,0,1,'2015-06-20 22:52:37',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(89,1,1,1,'2015-06-20 22:54:08',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(92,1,0,1,'2015-07-02 12:05:00',0,''),(93,1,0,1,'2015-07-02 12:06:55',0,''),(94,1,0,1,'2015-07-02 12:35:39',0,''),(95,1,0,1,'2015-07-02 12:38:44',0,''),(96,1,6,1,'2015-07-02 15:20:16',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(99,1,0,1,'2015-07-03 09:28:49',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(100,1,6,1,'2015-07-03 14:39:38',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(101,1,2,1,'2015-07-03 20:46:40',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(102,1,7,1,'2015-07-03 22:00:28',2,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(103,1,2,1,'2015-07-09 22:21:28',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px none; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div style=\"display: none;\" id=\"MathJax_Message\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px none; margin: 0px;\"><div style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px none; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-size-adjust: none; font-family: MathJax_Main,sans-serif;\" id=\"MathJax_Font_Test\"></div></div>'),(104,1,3,1,'2015-07-10 07:51:51',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px none; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div style=\"display: none;\" id=\"MathJax_Message\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px none; margin: 0px;\"><div style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px none; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-size-adjust: none; font-family: MathJax_Main,sans-serif;\" id=\"MathJax_Font_Test\"></div></div>'),(118,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(119,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(120,0,1,4,'2015-08-25 21:20:41',1,''),(121,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(122,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(123,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(124,0,1,4,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(125,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inlinzcdasce equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(126,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is iassdadadanline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(127,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div>This is Avijit\'s answer $\\sqrt[2]{3}$ </div>'),(128,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(129,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(130,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(131,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(132,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(133,0,1,1,'2015-08-25 21:20:41',1,'\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(134,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(135,0,1,1,'2015-08-25 21:20:41',1,'\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n'),(136,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(137,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(138,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(139,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(140,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(141,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(142,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is This is inline equation</div><div id=\"editordiv\"></div><div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; word-spacing: normal; font-size: 40px; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div></div><div id=\"editordiv\">inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(143,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline This is inline equation</div><div id=\"editordiv\"></div><div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; word-spacing: normal; font-size: 40px; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div></div><div id=\"editordiv\">equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(144,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px none; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div style=\"display: none;\" id=\"MathJax_Message\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px none; margin: 0px;\"><div style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px none; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-size-adjust: none; font-family: MathJax_Main,sans-serif;\" id=\"MathJax_Font_Test\"></div></div>'),(145,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px none; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div style=\"display: none;\" id=\"MathJax_Message\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px none; margin: 0px;\"><div style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px none; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-size-adjust: none; font-family: MathJax_Main,sans-serif;\" id=\"MathJax_Font_Test\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(146,0,1,1,'2015-08-25 21:20:41',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px none; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div style=\"display: none;\" id=\"MathJax_Message\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px none; margin: 0px;\"><div style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px none; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-size-adjust: none; font-family: MathJax_Main,sans-serif;\" id=\"MathJax_Font_Test\"></div></div>'),(148,1,0,1,'2015-08-25 21:35:18',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(149,1,0,1,'2015-08-25 21:36:49',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(150,1,0,1,'2015-08-25 21:37:51',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(156,1,0,1,'2015-08-25 21:46:04',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(158,0,0,1,'2015-08-25 21:51:47',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(159,0,0,1,'2015-08-25 21:53:15',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(160,0,0,1,'2015-08-25 21:53:19',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(161,0,0,1,'2015-08-26 09:13:20',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(162,0,0,1,'2015-08-26 18:54:33',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(163,0,0,1,'2015-08-26 18:55:18',1,'Testing by anand <div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(164,0,0,1,'2015-08-28 15:55:48',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(165,1,0,1,'2015-08-28 16:24:03',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(166,0,0,1,'2015-08-28 16:24:06',1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(167,0,0,1,'2015-08-28 22:44:07',1,'Testing by anand <div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(168,1,0,1,'2015-09-12 17:12:14',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(169,1,0,1,'2015-09-12 17:13:23',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(170,1,0,1,'2015-09-19 10:10:20',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(171,0,0,1,'2015-09-19 10:11:17',1,'Testing by anand <div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(172,0,0,1,'2015-09-19 10:13:31',1,'Testing by anand <div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(173,1,0,1,'2015-09-19 20:31:48',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(174,0,0,1,'2015-09-19 20:36:40',1,'Testing by anand <div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(175,1,0,1,'2015-09-19 21:42:36',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(182,1,0,1,'2015-09-19 23:47:35',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>'),(191,1,0,1,'2015-09-26 17:08:27',0,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: MathJax_Main, sans-serif;\"></div></div>');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postvote`
--

DROP TABLE IF EXISTS `postvote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postvote` (
  `PostVoteId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VotedByUserId` int(10) unsigned NOT NULL,
  `UpOrDown` tinyint(4) NOT NULL,
  `VoteDate` datetime NOT NULL,
  `postId` int(10) unsigned DEFAULT NULL,
  `PostType` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`PostVoteId`),
  KEY `QuestionFk_idx` (`postId`),
  KEY `AppUserfk_idx` (`VotedByUserId`),
  CONSTRAINT `QuestionFk` FOREIGN KEY (`postId`) REFERENCES `post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postvote`
--

LOCK TABLES `postvote` WRITE;
/*!40000 ALTER TABLE `postvote` DISABLE KEYS */;
INSERT INTO `postvote` VALUES (11,1,1,'2015-07-03 21:45:55',80,1),(12,1,0,'2015-07-03 21:45:59',80,1),(13,1,1,'2015-07-03 21:49:05',80,1),(14,1,0,'2015-07-03 21:49:11',80,1),(15,1,0,'2015-07-03 22:01:18',102,1),(16,1,1,'2015-07-03 22:01:33',102,1),(17,1,0,'2015-07-03 22:03:52',102,1),(18,1,1,'2015-07-03 22:04:04',102,1),(19,1,0,'2015-07-03 22:05:07',102,1),(20,1,1,'2015-07-03 22:05:22',102,1),(21,1,0,'2015-07-03 22:06:16',102,1),(22,1,1,'2015-07-03 22:06:26',102,1),(23,5,1,'2015-07-03 22:14:25',102,1),(24,1,0,'2015-08-28 13:05:14',102,1),(25,1,1,'2015-08-28 13:24:55',102,1),(26,4,1,'2015-08-28 13:27:21',102,1),(27,1,0,'2015-08-28 14:50:15',102,1),(28,1,1,'2015-08-28 14:50:26',102,1),(29,1,0,'2015-08-28 15:55:38',102,1),(30,1,1,'2015-08-28 16:26:02',102,1),(31,1,0,'2015-09-19 23:21:47',102,1);
/*!40000 ALTER TABLE `postvote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionId` int(10) unsigned NOT NULL,
  `RefSubjectId` int(10) unsigned NOT NULL,
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `RefQuestionStatusId` int(10) unsigned NOT NULL,
  `Title` varchar(50) NOT NULL,
  `NoOfViews` int(10) unsigned NOT NULL DEFAULT '0',
  `LastActiveDate` datetime NOT NULL,
  `IsPublic` tinyint(1) NOT NULL,
  `ClassroomId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  UNIQUE KEY `Title_UNIQUE` (`Title`),
  KEY `Question_FKIndex1` (`RefQuestionStatusId`),
  KEY `Question_FKIndex3` (`QuestionLevelId`),
  KEY `Question_FKIndex4` (`RefSubjectId`),
  KEY `question_ibfk_5_idx` (`ClassroomId`),
  KEY `questionPost_fk` (`questionId`),
  CONSTRAINT `questionPost_fk` FOREIGN KEY (`questionId`) REFERENCES `post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`QuestionLevelId`) REFERENCES `questionlevel` (`QuestionLevelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_4` FOREIGN KEY (`RefSubjectId`) REFERENCES `refsubject` (`RefSubjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_5` FOREIGN KEY (`ClassroomId`) REFERENCES `classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (45,1,1,1,'This is title',0,'2015-01-18 11:11:43',1,NULL),(47,1,1,1,'What is the factorial of zero?',0,'2015-01-18 13:48:05',1,NULL),(49,1,1,1,'This is title dddd',0,'2015-01-26 21:41:58',1,NULL),(50,1,1,1,'This is title gggg',0,'2015-01-26 21:50:10',1,NULL),(51,1,1,1,'aaaThis is title',0,'2015-01-26 22:27:16',1,NULL),(53,1,1,1,'This is title1234',0,'2015-06-12 11:03:37',1,NULL),(54,1,1,1,'This is title3454635',0,'2015-06-12 11:04:25',1,NULL),(55,1,1,1,'This is title359486340',0,'2015-06-12 11:17:03',1,NULL),(56,1,1,1,'This is a mathematical ',0,'2015-06-12 16:21:42',1,NULL),(57,1,1,1,'This is title 7898',0,'2015-06-13 16:57:04',1,NULL),(58,1,1,1,'1. 2. This is title',0,'2015-06-13 17:09:56',1,NULL),(59,1,1,1,'1This is title',0,'2015-06-13 17:14:54',1,NULL),(60,1,1,1,'1.This is title',0,'2015-06-13 17:19:08',1,NULL),(61,1,1,1,'2. This is title',0,'2015-06-16 16:05:30',1,NULL),(63,1,1,1,'9847598345 This is title',0,'2015-06-16 16:08:37',1,NULL),(65,1,1,1,'984798345 This is title',0,'2015-06-16 16:11:12',1,NULL),(66,1,1,1,'9872398547 This is title',0,'2015-06-16 16:12:02',1,NULL),(67,1,1,1,'ascdac This is title',0,'2015-06-16 16:15:56',1,NULL),(68,1,1,1,'mnjkh This is title',0,'2015-06-16 16:46:28',1,NULL),(69,1,1,1,'iuThis is title',0,'2015-06-16 19:17:20',1,NULL),(70,1,1,1,'lkojThis is title',1,'2015-06-16 19:18:54',1,NULL),(71,1,1,1,'alkkjh This is title',0,'2015-06-16 19:24:46',1,NULL),(72,1,1,1,'This is title jasdkashda',0,'2015-06-16 19:27:23',1,NULL),(73,1,1,1,'This is title jasdkashdad',0,'2015-06-16 19:34:01',1,NULL),(74,1,1,1,'This is titlelkjlj',0,'2015-06-16 19:47:22',1,NULL),(75,1,1,1,'This is titlesacqDAS',0,'2015-06-16 19:57:51',1,NULL),(76,1,1,1,'This xSXis title',0,'2015-06-16 20:16:49',1,NULL),(78,1,1,1,'This is titleWDWSDW',1,'2015-06-16 20:18:55',1,NULL),(79,1,1,1,'This is titleWDWXASSDW',9,'2015-06-16 20:19:18',1,NULL),(80,1,1,1,'7678 This is title',8,'2015-06-19 18:46:38',1,NULL),(82,1,1,1,'897987 This is title',0,'2015-06-19 18:47:57',1,NULL),(83,1,1,1,'89x7987 This is title',0,'2015-06-19 18:48:37',1,NULL),(84,1,1,1,'isdhhsvh This is title',0,'2015-06-19 18:49:21',1,NULL),(85,1,1,1,'alpha This is title',0,'2015-06-20 21:54:36',1,NULL),(86,1,1,1,'beta This is title',0,'2015-06-20 22:00:49',1,NULL),(87,1,1,1,'gamma  This is title',0,'2015-06-20 22:06:53',1,NULL),(88,1,1,1,'theta This is title',0,'2015-06-20 22:52:37',1,NULL),(89,1,1,1,'Hexa This is title',7,'2015-06-20 22:54:08',1,NULL),(92,1,1,1,'This is title - ee',0,'2015-07-02 12:05:00',1,NULL),(93,1,1,1,'This is title - ii ',0,'2015-07-02 12:06:55',1,NULL),(94,1,1,1,'This is title - pp',0,'2015-07-02 12:35:39',1,NULL),(95,1,1,1,'This is title -- ll',0,'2015-07-02 12:38:44',1,NULL),(96,1,1,1,'This is title - kk',6,'2015-07-02 15:20:16',1,NULL),(99,1,1,1,'This is title -- llll',0,'2015-07-03 09:28:49',1,NULL),(100,1,1,1,'This is title - 0000 ',7,'2015-07-03 14:39:38',1,NULL),(101,1,1,1,'This is title - kkk',7,'2015-07-03 20:46:40',1,NULL),(102,1,1,1,'This is title - voting test',23,'2015-07-03 22:00:28',1,NULL),(103,1,1,1,'This is title - 009988',2,'2015-07-09 22:21:28',1,NULL),(104,1,1,1,'This is title -- 980',3,'2015-07-10 07:51:51',1,NULL),(148,1,1,1,'This is title this is testing2',1,'2015-08-25 21:35:18',1,NULL),(149,1,1,1,'Software testing question',2,'2015-08-25 21:36:49',1,NULL),(150,1,1,1,'Software development question',2,'2015-08-25 21:37:51',1,NULL),(156,1,1,1,'Political correct solution by bjp',19,'2015-08-25 21:46:04',1,NULL),(165,1,1,1,'This is title - asking from war',2,'2015-08-28 16:24:03',1,NULL),(168,1,1,1,'This is title aaaa',1,'2015-09-12 17:12:14',1,NULL),(169,1,1,1,'This is title sss',1,'2015-09-12 17:13:23',1,NULL),(170,1,1,1,'This is title this is testin11',1,'2015-09-19 10:10:20',1,NULL),(173,1,1,1,'This is title this is testine45',0,'2015-09-19 20:31:48',1,NULL),(175,1,1,1,'This is title this is testing1',0,'2015-09-19 21:42:36',1,NULL),(182,1,1,1,'This is title this is testi0091',0,'2015-09-19 23:47:35',1,NULL),(191,1,1,1,'This is title this is testi0092',0,'2015-09-26 17:08:27',1,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionclassroom`
--

DROP TABLE IF EXISTS `questionclassroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionclassroom` (
  `QuestionClassroomId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ClassroomId` int(10) unsigned NOT NULL,
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionClassroomId`),
  KEY `QuestionClassroom_FKIndex1` (`questionId`),
  KEY `QuestionClassroom_FKIndex2` (`ClassroomId`),
  CONSTRAINT `questionclassroom_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `questionclassroom_ibfk_2` FOREIGN KEY (`ClassroomId`) REFERENCES `classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionclassroom`
--

LOCK TABLES `questionclassroom` WRITE;
/*!40000 ALTER TABLE `questionclassroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionclassroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionlevel`
--

DROP TABLE IF EXISTS `questionlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionlevel` (
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `LevelName` varchar(20) NOT NULL,
  `LevelOrder` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`QuestionLevelId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionlevel`
--

LOCK TABLES `questionlevel` WRITE;
/*!40000 ALTER TABLE `questionlevel` DISABLE KEYS */;
INSERT INTO `questionlevel` VALUES (1,'Medium',1),(2,'Hard',3),(3,'Easy',1);
/*!40000 ALTER TABLE `questionlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionrating`
--

DROP TABLE IF EXISTS `questionrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionrating` (
  `QuestionRatingId` int(10) NOT NULL,
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `RatedByUserId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionRatingId`),
  KEY `QuestionLevel_fk_idx` (`QuestionLevelId`),
  KEY `Appuser+fk_idx` (`RatedByUserId`),
  CONSTRAINT `Appuser+fk` FOREIGN KEY (`RatedByUserId`) REFERENCES `appuser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `QuestionLevel_fk` FOREIGN KEY (`QuestionLevelId`) REFERENCES `questionlevel` (`QuestionLevelId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionrating`
--

LOCK TABLES `questionrating` WRITE;
/*!40000 ALTER TABLE `questionrating` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionratingsummary`
--

DROP TABLE IF EXISTS `questionratingsummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionratingsummary` (
  `QuestionRatingSummaryId` int(10) unsigned NOT NULL,
  `NoOfEasyRatings` int(11) DEFAULT NULL,
  `NoOfMediumRatings` int(11) DEFAULT NULL,
  `NoOfHardRatings` int(11) DEFAULT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionRatingSummaryId`),
  KEY `Question_fk_idx` (`QuestionId`),
  CONSTRAINT `Question_fk` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionratingsummary`
--

LOCK TABLES `questionratingsummary` WRITE;
/*!40000 ALTER TABLE `questionratingsummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionratingsummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questiontag`
--

DROP TABLE IF EXISTS `questiontag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questiontag` (
  `QuestionTagId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `QuestionId` int(10) unsigned NOT NULL,
  `TagId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionTagId`),
  UNIQUE KEY `questionTagUniqueKey` (`QuestionId`,`TagId`),
  KEY `QuestionTag_FKIndex1` (`TagId`),
  KEY `QuestionTag_FKIndex2` (`QuestionId`),
  CONSTRAINT `questiontag_ibfk_1` FOREIGN KEY (`TagId`) REFERENCES `tag` (`TagId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `questiontag_ibfk_2` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questiontag`
--

LOCK TABLES `questiontag` WRITE;
/*!40000 ALTER TABLE `questiontag` DISABLE KEYS */;
INSERT INTO `questiontag` VALUES (3,182,1),(9,191,1);
/*!40000 ALTER TABLE `questiontag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refclassroommemberstatus`
--

DROP TABLE IF EXISTS `refclassroommemberstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refclassroommemberstatus` (
  `RefClassroomMemberStatusId` int(10) unsigned NOT NULL,
  `ClassroommemberStatusName` varchar(20) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`RefClassroomMemberStatusId`),
  UNIQUE KEY `ClassroommemberStatusName_UNIQUE` (`ClassroommemberStatusName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refclassroommemberstatus`
--

LOCK TABLES `refclassroommemberstatus` WRITE;
/*!40000 ALTER TABLE `refclassroommemberstatus` DISABLE KEYS */;
INSERT INTO `refclassroommemberstatus` VALUES (1,'Pending Approval','applied for membership'),(2,'Active','Request to join is approved'),(3,'Expired','Request to join is approved'),(4,'Rejected','Rejected');
/*!40000 ALTER TABLE `refclassroommemberstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refquestionstatus`
--

DROP TABLE IF EXISTS `refquestionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refquestionstatus` (
  `RefQuestionStatusId` int(10) unsigned NOT NULL,
  `QuestionStatusName` varchar(20) NOT NULL,
  PRIMARY KEY (`RefQuestionStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refquestionstatus`
--

LOCK TABLES `refquestionstatus` WRITE;
/*!40000 ALTER TABLE `refquestionstatus` DISABLE KEYS */;
INSERT INTO `refquestionstatus` VALUES (1,'new');
/*!40000 ALTER TABLE `refquestionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refsubject`
--

DROP TABLE IF EXISTS `refsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refsubject` (
  `RefSubjectId` int(10) unsigned NOT NULL,
  `SubjectName` varchar(20) NOT NULL,
  `SubjectDescription` varchar(20) NOT NULL,
  PRIMARY KEY (`RefSubjectId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refsubject`
--

LOCK TABLES `refsubject` WRITE;
/*!40000 ALTER TABLE `refsubject` DISABLE KEYS */;
INSERT INTO `refsubject` VALUES (1,'mathematics','mathematics');
/*!40000 ALTER TABLE `refsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `TagId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TagName` varchar(20) NOT NULL,
  `TagDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`TagId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'python','This is an OO programming language'),(2,'java','This is an OO programming language'),(3,'javascript','This is an OO programming language'),(4,'math','This is an OO programming language'),(5,'materials','This is an OO programming language'),(6,'chemistry','This is an OO programming language'),(7,'calculus','This is an OO programming language'),(8,'python','This is an OO programming language'),(9,'python','This is an OO programming language'),(10,'python','This is an OO programming language'),(11,'geography','This is an OO programming language'),(12,'java1','This is an OO programming language'),(13,'java12','This is an OO programming language'),(14,'java22','This is an OO programming language'),(15,'java32','This is an OO programming language');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userreputation`
--

DROP TABLE IF EXISTS `userreputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userreputation` (
  `UserReputationId` int(10) unsigned NOT NULL,
  `ReputationName` varchar(20) NOT NULL,
  PRIMARY KEY (`UserReputationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreputation`
--

LOCK TABLES `userreputation` WRITE;
/*!40000 ALTER TABLE `userreputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `userreputation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'learn-qa'
--
/*!50003 DROP PROCEDURE IF EXISTS `AnswerAddSproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AnswerAddSproc`(
	pQuestionId int,
	pAnsweredByUserId int,
	pContent text
)
BEGIN

DECLARE vPostId INT DEFAULT 0;
insert into post (postedBy, postType, Votes, Content, postdate ) values (pAnsweredByUserId, 0, 1, pContent, now());
SET vPostId = LAST_INSERT_ID();
	insert into Answer (answerId,QuestionId ) 
    values (vPostId, pQuestionId);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `classroomGetById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomGetById`(
	pClassroomId  int
)
BEGIN

	select 
    ClassroomId,
    MinReputationToJoinId,
    ClassOwner as PostedBy,
	u.Firstname,
	u.middleName,
	u.lastName,
    ClassName,
    IsPublic,
    LastUpdateDate
from
    classroom c
        join
    AppUser u ON u.appuserId = classowner
where
    c.classroomId = pClassroomId;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `classroomJoin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomJoin`(
	pUserId int,
	pClassroomId int

)
BEGIN

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `JoinClassroomSproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `JoinClassroomSproc`(
	pUserId int,
	pClassroomId int
)
BEGIN

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionAddSproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionAddSproc`(
pPostedBy int,
pTitle varchar(50),
pContent text,
pRefSubjectId int
)
BEGIN
	DECLARE vQuestionId INT DEFAULT 0;

	insert into post ( postdate, PostedBy, Content, posttype) 
	values ( now(), pPostedBy, pContent,  1);
    
    SET vQuestionId = LAST_INSERT_ID();

	insert into question (questionId, RefSubjectId, QuestionlevelId, refQuestionStatusId , 
     lastactivedate, Title, isPublic) 
	values (vQuestionId,pRefSubjectId,1, 1,  now(), pTitle, 1);
	
	-- Select questionId into vQuestionId from question where title = pTitle;

	call questionGet(vQuestionId);

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `QuestionGet` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `QuestionGet`(
	pQuestionId int
)
BEGIN

	select 
		questionId,
		RefSubjectId,
		QuestionLevelId,
		p.PostedBy,
		u.Firstname,
		u.middleName,
		u.lastName,
		RefQuestionStatusId,
		Title,
		p.Content,
		q.NoOfViews,
		p.PostDate,
		LastActiveDate,
		p.Votes,
		IsPublic
	from
		Question q
    join post p on p.postid = q.questionid
		join AppUser u on u.appuserId = p.postedby
	where 
		questionId = pQuestionId;

	Select 
		AnswerId, p.postedby as AnsweredByUserId, QuestionId, p.Votes, p.Content
	From
		Answer a
    join post p on p.postid = a.answerid
	where
		questionId = pQuestionId;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionUpdateStats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionUpdateStats`(
	pQuestionId int,
	pVotes int
)
BEGIN

	DECLARE vNoOfViews, vVotes INT DEFAULT 0;
	
	Select noOfViews into vNoOfViews from question where questionId = pQuestionId;
	Select votes into vVotes from question where questionId = pQuestionId;


	Update Question
	set NoOfViews = vNoOfViews +1,
		LastActiveDate = now();

	if(pVotes != 0)
		then
			Update Question
			set votes = vVotes +pVotes;
   
	end If;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userAddSproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userAddSproc`(
pEmail varchar(20), 
pPasword varchar(20),
pFirstName varchar(20), 
pMiddleName varchar(20),
pLastName varchar(20)
)
BEGIN

	INSERT INTO AppUser(Email,Pasword,FirstName,MiddleName,LastName)VALUES(pEmail,pPasword,pFirstName,pMiddleName,pLastName);

	Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
	from AppUser where Email = pEmail;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userGet` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userGet`(
pAppUserId int,
pEmail varchar(20)
)
BEGIN

	if(pAppUserId is not null)
	then
		Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
		from AppUser where AppUserId = pAppUserId;
	else
		Select AppUserId, UserReputationId, Email, Pasword, FirstName, MiddleName, LastName
		from AppUser where Email = pEmail;
	end if;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userUpdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userUpdate`(
pAppUserId int,
pUserReputationId int,
pEmail varchar(20)
)
BEGIN

	if(pUserReputationId is not null)
	then
		UPDATE AppUser SET UserReputationId = pUserReputationId where AppUserId = pAppUserId;
	end if;

	if(pEmail is not null)
	then
		UPDATE AppUser SET Email = pEmail,Pasword = pPasword where AppUserId = pAppUserId;
	end if;

	if(pPasword is not null)
	then
		UPDATE AppUser SET Pasword = pPasword where AppUserId = pAppUserId;
	end if;

	Select * from AppUser where Email = pEmail;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-15 20:25:21
