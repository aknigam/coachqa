CREATE DATABASE  IF NOT EXISTS `learn-qa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `learn-qa`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: learn-qa
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `Answer`
--

DROP TABLE IF EXISTS `Answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Answer` (
  `AnswerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnsweredByUserId` int(10) unsigned NOT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  `Votes` int(10) unsigned DEFAULT NULL,
  `Content` longtext NOT NULL,
  PRIMARY KEY (`AnswerId`),
  KEY `Answer_FKIndex1` (`QuestionId`),
  KEY `Answer_FKIndex2` (`AnsweredByUserId`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QuestionId`) REFERENCES `Question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`AnsweredByUserId`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Answer`
--

LOCK TABLES `Answer` WRITE;
/*!40000 ALTER TABLE `Answer` DISABLE KEYS */;
INSERT INTO `Answer` VALUES (14,4,45,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(15,4,45,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(16,4,45,1,''),(17,4,45,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(18,4,45,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(19,4,50,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(20,4,51,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(21,1,54,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inlinzcdasce equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(22,1,54,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is iassdadadanline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(23,1,56,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div>This is Avijit\'s answer $\\sqrt[2]{3}$ </div>'),(24,1,84,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(25,1,84,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(26,1,84,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(27,1,84,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(28,1,84,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(29,1,70,1,'\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(30,1,89,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(31,1,89,1,'\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n'),(32,1,89,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(33,1,96,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(34,1,100,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(35,1,96,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>'),(36,1,100,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(37,1,100,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(38,1,100,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is This is inline equation</div><div id=\"editordiv\"></div><div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; word-spacing: normal; font-size: 40px; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div></div><div id=\"editordiv\">inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>'),(39,1,100,1,'<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline This is inline equation</div><div id=\"editordiv\"></div><div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; word-spacing: normal; font-size: 40px; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div></div><div id=\"editordiv\">equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>');
/*!40000 ALTER TABLE `Answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AnswerComment`
--

DROP TABLE IF EXISTS `AnswerComment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AnswerComment` (
  `AnswerCommentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnswerId` int(10) unsigned NOT NULL,
  `AnswerComment` longtext NOT NULL,
  PRIMARY KEY (`AnswerCommentId`),
  KEY `AnswerComment_FKIndex1` (`AnswerId`),
  CONSTRAINT `answercomment_ibfk_1` FOREIGN KEY (`AnswerId`) REFERENCES `Answer` (`AnswerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AnswerComment`
--

LOCK TABLES `AnswerComment` WRITE;
/*!40000 ALTER TABLE `AnswerComment` DISABLE KEYS */;
/*!40000 ALTER TABLE `AnswerComment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AppUser`
--

DROP TABLE IF EXISTS `AppUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AppUser` (
  `AppUserId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserReputationId` int(10) unsigned DEFAULT NULL,
  `Email` varchar(20) NOT NULL,
  `Pasword` varchar(20) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `MiddleName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) NOT NULL,
  PRIMARY KEY (`AppUserId`),
  KEY `AppUser_FKIndex1` (`UserReputationId`),
  CONSTRAINT `appuser_ibfk_1` FOREIGN KEY (`UserReputationId`) REFERENCES `UserReputation` (`UserReputationId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AppUser`
--

LOCK TABLES `AppUser` WRITE;
/*!40000 ALTER TABLE `AppUser` DISABLE KEYS */;
INSERT INTO `AppUser` VALUES (1,NULL,'anigam@expedia.com','pass','Anand','K','nigam'),(2,NULL,'anigam@gmail.com','pass','Anand','K','nigam'),(3,NULL,'anigam@gmail.com','sasx','Anand','K','nigam'),(4,NULL,'anand@gmail.com','pass','Anand','K','nigam'),(5,NULL,'priya@yahoo.com','password','priya','','Nigam');
/*!40000 ALTER TABLE `AppUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Classroom`
--

DROP TABLE IF EXISTS `Classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Classroom` (
  `ClassroomId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MinReputationToJoinId` int(10) unsigned NOT NULL,
  `ClassOwner` int(10) unsigned NOT NULL,
  `ClassName` varchar(20) NOT NULL,
  `IsPublic` tinyint(1) NOT NULL,
  `RefClassroomMemberStatusId` int(10) unsigned NOT NULL,
  `CreateDate` datetime NOT NULL,
  `LastUpdateDate` datetime NOT NULL,
  PRIMARY KEY (`ClassroomId`),
  KEY `Classroom_FKIndex1` (`ClassOwner`),
  KEY `Classroom_FKIndex2` (`MinReputationToJoinId`),
  KEY `classroom_ibfk_3_idx` (`RefClassroomMemberStatusId`),
  CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`ClassOwner`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroom_ibfk_2` FOREIGN KEY (`MinReputationToJoinId`) REFERENCES `UserReputation` (`UserReputationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroom_ibfk_3` FOREIGN KEY (`RefClassroomMemberStatusId`) REFERENCES `RefClassroomMemberStatus` (`RefClassroomMemberStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Classroom`
--

LOCK TABLES `Classroom` WRITE;
/*!40000 ALTER TABLE `Classroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `Classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClassroomMember`
--

DROP TABLE IF EXISTS `ClassroomMember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClassroomMember` (
  `ClassroomMemberId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AppUserId` int(10) unsigned NOT NULL,
  `ClassroomId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ClassroomMemberId`),
  KEY `ClassroomMember_FKIndex1` (`ClassroomId`),
  KEY `ClassroomMember_FKIndex2` (`AppUserId`),
  CONSTRAINT `classroommember_ibfk_1` FOREIGN KEY (`ClassroomId`) REFERENCES `Classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classroommember_ibfk_2` FOREIGN KEY (`AppUserId`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClassroomMember`
--

LOCK TABLES `ClassroomMember` WRITE;
/*!40000 ALTER TABLE `ClassroomMember` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClassroomMember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Question`
--

DROP TABLE IF EXISTS `Question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Question` (
  `questionId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RefSubjectId` int(10) unsigned NOT NULL,
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `PostedBy` int(10) unsigned NOT NULL,
  `RefQuestionStatusId` int(10) unsigned NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Content` text NOT NULL,
  `NoOfViews` int(10) unsigned NOT NULL DEFAULT '0',
  `PostDate` datetime NOT NULL,
  `LastActiveDate` datetime NOT NULL,
  `Votes` int(10) unsigned NOT NULL DEFAULT '0',
  `IsPublic` tinyint(1) NOT NULL,
  `ClassroomId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  UNIQUE KEY `Title_UNIQUE` (`Title`),
  KEY `Question_FKIndex1` (`RefQuestionStatusId`),
  KEY `Question_FKIndex2` (`PostedBy`),
  KEY `Question_FKIndex3` (`QuestionLevelId`),
  KEY `Question_FKIndex4` (`RefSubjectId`),
  KEY `question_ibfk_5_idx` (`ClassroomId`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`RefQuestionStatusId`) REFERENCES `RefQuestionStatus` (`RefQuestionStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`PostedBy`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`QuestionLevelId`) REFERENCES `QuestionLevel` (`QuestionLevelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_4` FOREIGN KEY (`RefSubjectId`) REFERENCES `RefSubject` (`RefSubjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_5` FOREIGN KEY (`ClassroomId`) REFERENCES `Classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
INSERT INTO `Question` VALUES (45,1,1,4,1,'This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-01-18 11:11:43','2015-01-18 11:11:43',0,1,NULL),(47,1,1,4,1,'What is the factorial of zero?','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div><p>\r\n$$\r\n\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a}\r\n$$\r\n</p></div>',0,'2015-01-18 13:48:05','2015-01-18 13:48:05',0,1,NULL),(49,1,1,4,1,'This is title dddd','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-01-26 21:41:58','2015-01-26 21:41:58',0,1,NULL),(50,1,1,4,1,'This is title gggg','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-01-26 21:50:10','2015-01-26 21:50:10',0,1,NULL),(51,1,1,4,1,'aaaThis is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-01-26 22:27:16','2015-01-26 22:27:16',0,1,NULL),(53,1,1,1,1,'This is title1234','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-12 11:03:37','2015-06-12 11:03:37',0,1,NULL),(54,1,1,1,1,'This is title3454635','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-12 11:04:25','2015-06-12 11:04:25',0,1,NULL),(55,1,1,1,1,'This is title359486340','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-12 11:17:03','2015-06-12 11:17:03',0,1,NULL),(56,1,1,1,1,'This is a mathematical ','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$   $\\sqrt[2]{3}$</div>',0,'2015-06-12 16:21:42','2015-06-12 16:21:42',0,1,NULL),(57,1,1,1,1,'This is title 7898','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-13 16:57:04','2015-06-13 16:57:04',0,1,NULL),(58,1,1,1,1,'1. 2. This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-13 17:09:56','2015-06-13 17:09:56',0,1,NULL),(59,1,1,1,1,'1This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-13 17:14:54','2015-06-13 17:14:54',0,1,NULL),(60,1,1,1,1,'1.This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-13 17:19:08','2015-06-13 17:19:08',0,1,NULL),(61,1,1,1,1,'2. This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:05:30','2015-06-16 16:05:30',0,1,NULL),(63,1,1,1,1,'9847598345 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:08:37','2015-06-16 16:08:37',0,1,NULL),(65,1,1,1,1,'984798345 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:11:12','2015-06-16 16:11:12',0,1,NULL),(66,1,1,1,1,'9872398547 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:12:02','2015-06-16 16:12:02',0,1,NULL),(67,1,1,1,1,'ascdac This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:15:56','2015-06-16 16:15:56',0,1,NULL),(68,1,1,1,1,'mnjkh This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 16:46:28','2015-06-16 16:46:28',0,1,NULL),(69,1,1,1,1,'iuThis is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:17:20','2015-06-16 19:17:20',0,1,NULL),(70,1,1,1,1,'lkojThis is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:18:54','2015-06-16 19:18:54',0,1,NULL),(71,1,1,1,1,'alkkjh This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:24:46','2015-06-16 19:24:46',0,1,NULL),(72,1,1,1,1,'This is title jasdkashda','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:27:23','2015-06-16 19:27:23',0,1,NULL),(73,1,1,1,1,'This is title jasdkashdad','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:34:01','2015-06-16 19:34:01',0,1,NULL),(74,1,1,1,1,'This is titlelkjlj','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:47:22','2015-06-16 19:47:22',0,1,NULL),(75,1,1,1,1,'This is titlesacqDAS','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 19:57:51','2015-06-16 19:57:51',0,1,NULL),(76,1,1,1,1,'This xSXis title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 20:16:49','2015-06-16 20:16:49',0,1,NULL),(78,1,1,1,1,'This is titleWDWSDW','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 20:18:55','2015-06-16 20:18:55',0,1,NULL),(79,1,1,1,1,'This is titleWDWXASSDW','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-16 20:19:18','2015-06-16 20:19:18',0,1,NULL),(80,1,1,1,1,'7678 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>',6,'2015-06-19 18:46:38','2015-06-19 18:46:38',0,1,NULL),(82,1,1,1,1,'897987 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>',0,'2015-06-19 18:47:57','2015-06-19 18:47:57',0,1,NULL),(83,1,1,1,1,'89x7987 This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>',0,'2015-06-19 18:48:37','2015-06-19 18:48:37',0,1,NULL),(84,1,1,1,1,'isdhhsvh This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-19 18:49:21','2015-06-19 18:49:21',0,1,NULL),(85,1,1,1,1,'alpha This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-20 21:54:36','2015-06-20 21:54:36',0,1,NULL),(86,1,1,1,1,'beta This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-20 22:00:49','2015-06-20 22:00:49',0,1,NULL),(87,1,1,1,1,'gamma  This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-20 22:06:53','2015-06-20 22:06:53',0,1,NULL),(88,1,1,1,1,'theta This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-20 22:52:37','2015-06-20 22:52:37',0,1,NULL),(89,1,1,1,1,'Hexa This is title','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-06-20 22:54:08','2015-06-20 22:54:08',0,1,NULL),(92,1,1,1,1,'This is title - ee','',0,'2015-07-02 12:05:00','2015-07-02 12:05:00',0,1,NULL),(93,1,1,1,1,'This is title - ii ','',0,'2015-07-02 12:06:55','2015-07-02 12:06:55',0,1,NULL),(94,1,1,1,1,'This is title - pp','',0,'2015-07-02 12:35:39','2015-07-02 12:35:39',0,1,NULL),(95,1,1,1,1,'This is title -- ll','',0,'2015-07-02 12:38:44','2015-07-02 12:38:44',0,1,NULL),(96,1,1,1,1,'This is title - kk','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',2,'2015-07-02 15:20:16','2015-07-02 15:20:16',0,1,NULL),(99,1,1,1,1,'This is title -- llll','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div>',0,'2015-07-03 09:28:49','2015-07-03 09:28:49',0,1,NULL),(100,1,1,1,1,'This is title - 0000 ','<div style=\"visibility: hidden; overflow: hidden; position: absolute; top: 0px; height: 1px; width: auto; padding: 0px; border: 0px; margin: 0px; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal;\"><div id=\"MathJax_Hidden\"></div></div><div id=\"MathJax_Message\" style=\"display: none;\"></div>\r\n\r\n<!-- copy paste always happens inside body. new element added is same as the next or the previous element.\r\nit also retains styling, need to think of a way in which styling can be removing while copy pasting.  -->\r\n<div id=\"editordiv\">\r\nThis is inline equation $\\sqrt[v]{wefd}$\r\n</div>\r\n\r\n\r\n<div style=\"position: absolute; width: 0px; height: 0px; overflow: hidden; padding: 0px; border: 0px; margin: 0px;\"><div id=\"MathJax_Font_Test\" style=\"position: absolute; visibility: hidden; top: 0px; left: 0px; width: auto; padding: 0px; border: 0px; margin: 0px; white-space: nowrap; text-align: left; text-indent: 0px; text-transform: none; line-height: normal; letter-spacing: normal; word-spacing: normal; font-size: 40px; font-weight: normal; font-style: normal; font-family: STIXSizeOneSym, sans-serif;\"></div></div><div> $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$  $\\sqrt[2]{3}$ </div>',6,'2015-07-03 14:39:38','2015-07-03 14:39:38',0,1,NULL);
/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionClassroom`
--

DROP TABLE IF EXISTS `QuestionClassroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionClassroom` (
  `QuestionClassroomId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ClassroomId` int(10) unsigned NOT NULL,
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionClassroomId`),
  KEY `QuestionClassroom_FKIndex1` (`questionId`),
  KEY `QuestionClassroom_FKIndex2` (`ClassroomId`),
  CONSTRAINT `questionclassroom_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `Question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `questionclassroom_ibfk_2` FOREIGN KEY (`ClassroomId`) REFERENCES `Classroom` (`ClassroomId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionClassroom`
--

LOCK TABLES `QuestionClassroom` WRITE;
/*!40000 ALTER TABLE `QuestionClassroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `QuestionClassroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionLevel`
--

DROP TABLE IF EXISTS `QuestionLevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionLevel` (
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `LevelName` varchar(20) NOT NULL,
  `LevelOrder` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`QuestionLevelId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionLevel`
--

LOCK TABLES `QuestionLevel` WRITE;
/*!40000 ALTER TABLE `QuestionLevel` DISABLE KEYS */;
INSERT INTO `QuestionLevel` VALUES (1,'Medium',1),(2,'Hard',3),(3,'Easy',1);
/*!40000 ALTER TABLE `QuestionLevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionRating`
--

DROP TABLE IF EXISTS `QuestionRating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionRating` (
  `QuestionRatingId` int(10) NOT NULL,
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `RatedByUserId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionRatingId`),
  KEY `QuestionLevel_fk_idx` (`QuestionLevelId`),
  KEY `Appuser+fk_idx` (`RatedByUserId`),
  CONSTRAINT `Appuser+fk` FOREIGN KEY (`RatedByUserId`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `QuestionLevel_fk` FOREIGN KEY (`QuestionLevelId`) REFERENCES `QuestionLevel` (`QuestionLevelId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionRating`
--

LOCK TABLES `QuestionRating` WRITE;
/*!40000 ALTER TABLE `QuestionRating` DISABLE KEYS */;
/*!40000 ALTER TABLE `QuestionRating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionRatingSummary`
--

DROP TABLE IF EXISTS `QuestionRatingSummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionRatingSummary` (
  `QuestionRatingSummaryId` int(10) unsigned NOT NULL,
  `NoOfEasyRatings` int(11) DEFAULT NULL,
  `NoOfMediumRatings` int(11) DEFAULT NULL,
  `NoOfHardRatings` int(11) DEFAULT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionRatingSummaryId`),
  KEY `Question_fk_idx` (`QuestionId`),
  CONSTRAINT `Question_fk` FOREIGN KEY (`QuestionId`) REFERENCES `Question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionRatingSummary`
--

LOCK TABLES `QuestionRatingSummary` WRITE;
/*!40000 ALTER TABLE `QuestionRatingSummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `QuestionRatingSummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionTag`
--

DROP TABLE IF EXISTS `QuestionTag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionTag` (
  `QuestionTagId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `QuestionId` int(10) unsigned NOT NULL,
  `TagId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`QuestionTagId`),
  KEY `QuestionTag_FKIndex1` (`TagId`),
  KEY `QuestionTag_FKIndex2` (`QuestionId`),
  CONSTRAINT `questiontag_ibfk_1` FOREIGN KEY (`TagId`) REFERENCES `Tag` (`TagId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `questiontag_ibfk_2` FOREIGN KEY (`QuestionId`) REFERENCES `Question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionTag`
--

LOCK TABLES `QuestionTag` WRITE;
/*!40000 ALTER TABLE `QuestionTag` DISABLE KEYS */;
/*!40000 ALTER TABLE `QuestionTag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QuestionVote`
--

DROP TABLE IF EXISTS `QuestionVote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QuestionVote` (
  `QuestionVoteId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `VotedByUserId` int(10) unsigned NOT NULL,
  `UpOrDown` tinyint(4) NOT NULL,
  `VoteDate` datetime NOT NULL,
  `QuestionId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`QuestionVoteId`),
  KEY `QuestionFk_idx` (`QuestionId`),
  KEY `AppUserfk_idx` (`VotedByUserId`),
  CONSTRAINT `AppUserfk` FOREIGN KEY (`VotedByUserId`) REFERENCES `AppUser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `QuestionFk` FOREIGN KEY (`QuestionId`) REFERENCES `Question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QuestionVote`
--

LOCK TABLES `QuestionVote` WRITE;
/*!40000 ALTER TABLE `QuestionVote` DISABLE KEYS */;
INSERT INTO `QuestionVote` VALUES (2,1,0,'2015-07-03 00:00:00',80),(3,1,0,'2015-07-03 00:00:00',80),(4,1,0,'2015-07-03 00:00:00',80);
/*!40000 ALTER TABLE `QuestionVote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RefClassroomMemberStatus`
--

DROP TABLE IF EXISTS `RefClassroomMemberStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RefClassroomMemberStatus` (
  `RefClassroomMemberStatusId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ClassroommemberStatusName` varchar(20) NOT NULL,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`RefClassroomMemberStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RefClassroomMemberStatus`
--

LOCK TABLES `RefClassroomMemberStatus` WRITE;
/*!40000 ALTER TABLE `RefClassroomMemberStatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `RefClassroomMemberStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RefQuestionStatus`
--

DROP TABLE IF EXISTS `RefQuestionStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RefQuestionStatus` (
  `RefQuestionStatusId` int(10) unsigned NOT NULL,
  `QuestionStatusName` varchar(20) NOT NULL,
  PRIMARY KEY (`RefQuestionStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RefQuestionStatus`
--

LOCK TABLES `RefQuestionStatus` WRITE;
/*!40000 ALTER TABLE `RefQuestionStatus` DISABLE KEYS */;
INSERT INTO `RefQuestionStatus` VALUES (1,'new');
/*!40000 ALTER TABLE `RefQuestionStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RefSubject`
--

DROP TABLE IF EXISTS `RefSubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RefSubject` (
  `RefSubjectId` int(10) unsigned NOT NULL,
  `SubjectName` varchar(20) NOT NULL,
  `SubjectDescription` varchar(20) NOT NULL,
  PRIMARY KEY (`RefSubjectId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RefSubject`
--

LOCK TABLES `RefSubject` WRITE;
/*!40000 ALTER TABLE `RefSubject` DISABLE KEYS */;
INSERT INTO `RefSubject` VALUES (1,'mathematics','mathematics');
/*!40000 ALTER TABLE `RefSubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tag`
--

DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tag` (
  `TagId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TagName` varchar(20) NOT NULL,
  `TagDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`TagId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tag`
--

LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES (1,'python','This is an OO programming language'),(2,'java','This is an OO programming language'),(3,'javascript','This is an OO programming language'),(4,'math','This is an OO programming language'),(5,'materials','This is an OO programming language'),(6,'chemistry','This is an OO programming language'),(7,'calculus','This is an OO programming language'),(8,'python','This is an OO programming language'),(9,'python','This is an OO programming language'),(10,'python','This is an OO programming language');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserReputation`
--

DROP TABLE IF EXISTS `UserReputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserReputation` (
  `UserReputationId` int(10) unsigned NOT NULL,
  `ReputationName` varchar(20) NOT NULL,
  PRIMARY KEY (`UserReputationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserReputation`
--

LOCK TABLES `UserReputation` WRITE;
/*!40000 ALTER TABLE `UserReputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserReputation` ENABLE KEYS */;
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AnswerAddSproc`(
	pQuestionId int,
	pAnsweredByUserId int,
	pContent text
)
BEGIN

	insert into Answer (AnsweredByUserId,QuestionId, Votes, Content  ) values (pAnsweredByUserId, pQuestionId, 1, pContent);

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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomGetById`(
	pClassroomId  int
)
BEGIN

	select 
    ClassroomId,
    MinReputationToJoinId,
    ClassOwner,
	u.Firstname,
	u.middleName,
	u.lastName,
    ClassName,
    IsPublic,
    RefClassroomMemberStatusId,
    CreateDate,
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionAddSproc`(
pPostedBy int,
pTitle varchar(50),
pContent text,
pRefSubjectId int
)
BEGIN
	DECLARE vQuestionId INT DEFAULT 0;
	
	insert into question (RefSubjectId, QuestionlevelId, refQuestionStatusId , postdate, lastactivedate, PostedBy, Title, Content, isPublic) 
	values (pRefSubjectId,1, 1, now(), now(), pPostedBy, pTitle, pContent, 1);
	
	Select questionId into vQuestionId from question where title = pTitle;

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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `QuestionGet`(
	pQuestionId int
)
BEGIN

	select 
		questionId,
		RefSubjectId,
		QuestionLevelId,
		PostedBy,
		u.Firstname,
		u.middleName,
		u.lastName,
		RefQuestionStatusId,
		Title,
		Content,
		NoOfViews,
		PostDate,
		LastActiveDate,
		Votes,
		IsPublic
	from
		Question q
		join AppUser u on u.appuserId = q.postedby
	where 
		questionId = pQuestionId;

	Select 
		AnswerId, AnsweredByUserId, QuestionId, Votes, Content
	From
		Answer
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

-- Dump completed on 2015-07-03 15:02:06
