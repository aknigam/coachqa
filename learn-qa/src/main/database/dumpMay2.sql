-- MySQL dump 10.13  Distrib 5.7.27, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: crajee_db
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accountName` varchar(50) NOT NULL,
  `accountDescription` text,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`accountId`, `accountName`, `accountDescription`) VALUES (100,'demo','Account used for demo purposes');
INSERT INTO `account` (`accountId`, `accountName`, `accountDescription`) VALUES (101,'test','account to be used for test automation');
INSERT INTO `account` (`accountId`, `accountName`, `accountDescription`) VALUES (102,'test01','Test automation account');
INSERT INTO `account` (`accountId`, `accountName`, `accountDescription`) VALUES (103,'internal_account','Internal account to which application admin can be assigned');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_preference`
--

DROP TABLE IF EXISTS `account_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_preference` (
  `id` int(10) unsigned NOT NULL,
  `postsNeedApproval` tinyint(4) DEFAULT '1' COMMENT 'by default posts need approval	',
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_preference_account_accountId_fk` (`accountId`),
  CONSTRAINT `account_preference_account_accountId_fk` FOREIGN KEY (`accountId`) REFERENCES `crajee`.`account` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_preference`
--

LOCK TABLES `account_preference` WRITE;
/*!40000 ALTER TABLE `account_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerid` int(10) unsigned NOT NULL,
  `questionid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`answerid`),
  KEY `answerpost_fk` (`answerid`),
  KEY `answer_fkindex1` (`questionid`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`answerid`) REFERENCES `post` (`postid`),
  CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (9,4);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (15,14);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (19,18);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (21,18);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (24,18);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (36,33);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (37,33);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (51,38);
INSERT INTO `answer` (`answerid`, `questionid`) VALUES (49,48);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_mc`
--

DROP TABLE IF EXISTS `answer_mc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_mc` (
  `id` int(10) unsigned NOT NULL,
  `answerId` int(10) unsigned NOT NULL,
  `question_choice_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `answer_mc_question_choice_id_fk` (`question_choice_id`),
  CONSTRAINT `answer_mc_question_choice_id_fk` FOREIGN KEY (`question_choice_id`) REFERENCES `question_choice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_mc`
--

LOCK TABLES `answer_mc` WRITE;
/*!40000 ALTER TABLE `answer_mc` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_mc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answercomment`
--

DROP TABLE IF EXISTS `answercomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answercomment` (
  `answercommentid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answerid` int(10) unsigned NOT NULL,
  `answercomment` longtext NOT NULL,
  PRIMARY KEY (`answercommentid`),
  KEY `answercomment_fkindex1` (`answerid`),
  CONSTRAINT `answercomment_ibfk_1` FOREIGN KEY (`answerid`) REFERENCES `answer` (`answerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `appuserid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userreputationid` int(10) unsigned DEFAULT NULL,
  `email` varchar(20) NOT NULL,
  `pasword` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `middlename` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) NOT NULL,
  `usertypeid` tinyint(4) DEFAULT '1',
  `accountId` int(10) unsigned NOT NULL,
  `android_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appuserid`),
  UNIQUE KEY `email_unique` (`email`),
  KEY `appusertype___fk` (`usertypeid`),
  KEY `appuser_fkindex1` (`userreputationid`),
  KEY `appuser_account_accountId_fk` (`accountId`),
  CONSTRAINT `appuser_ibfk_1` FOREIGN KEY (`userreputationid`) REFERENCES `userreputation` (`userreputationid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (1,NULL,'admin@learnqa.com','pass','Admin',NULL,'admin',2,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (2,NULL,'abhi@crajee.com','pass','Anand','Kumar','Nigam',4,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (3,NULL,'motoe@crajee.com','pass','Cvent','Kumar','Nigam',4,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (4,NULL,'anigam@com','pass','Priya','Kumar','Nigam',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (5,NULL,'varun@com','pass','Varun',NULL,'Kubba',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (6,NULL,'a@c.com','pass','F','F','F',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (7,NULL,'anupam@com','pass','Anupam','','Sharma',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (8,NULL,'varuan@com','pass','Varun',NULL,'Kubba',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (9,NULL,'varuBn@com','pass','Viraj',NULL,'Kubba',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (10,NULL,'priya@com','pass','Priya','','Nigam',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (11,NULL,'a.nigam','pass','Nitish','Kumar','Nigam',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (12,NULL,'admin@crajee.com','pass','crajee','','admin',1,103,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (13,NULL,'varu@crajee.com','pass','Varun',NULL,'Kubba',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (14,NULL,'ridhi@crajee.com','pass','Ridhi',NULL,'Handa',4,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (17,NULL,'anigam@crajee.com','pass','Hiteshi',NULL,'Jain',2,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (18,NULL,'Davinder@crajee.com','pass','Davinder',NULL,'Singh',2,100,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (19,NULL,'aknigam@outlook.com','pass','Anand','Kumar','Nigam',4,101,NULL);
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (20,NULL,'motog5s@crajee.com','pass','Priya',NULL,'Nigam',3,100,'f1ZopkTtXas:APA91bFZVD3HVC9UHWCfwnp6IbOUQRdTM0TOKjuRy-6yoDDqJW9t1N1bncjRmvnp4qVGbH1Ncf32eOfdC6hImCIeFPgDI8pbQXs3PFnSCTmOgjBdiV_yyuYSUhK9IsRGvhq68ik0LQga');
INSERT INTO `appuser` (`appuserid`, `userreputationid`, `email`, `pasword`, `firstname`, `middlename`, `lastname`, `usertypeid`, `accountId`, `android_token`) VALUES (21,NULL,'anand@crajee.com','pass','Anand','','Nigam',4,100,'androidToken');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuserdetail`
--

DROP TABLE IF EXISTS `appuserdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserdetail` (
  `appuserdetailid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `appuserid` int(10) unsigned NOT NULL,
  `androidtoken` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`appuserdetailid`),
  KEY `appuserdetail_appuser_appuserid_fk` (`appuserid`),
  CONSTRAINT `appuserdetail_ibfk_1` FOREIGN KEY (`appuserid`) REFERENCES `appuser` (`appuserid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuserdetail`
--

LOCK TABLES `appuserdetail` WRITE;
/*!40000 ALTER TABLE `appuserdetail` DISABLE KEYS */;
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (1,2,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (2,3,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (3,17,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (4,20,'f1ZopkTtXas:APA91bFZVD3HVC9UHWCfwnp6IbOUQRdTM0TOKjuRy-6yoDDqJW9t1N1bncjRmvnp4qVGbH1Ncf32eOfdC6hImCIeFPgDI8pbQXs3PFnSCTmOgjBdiV_yyuYSUhK9IsRGvhq68ik0LQga');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (5,21,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (8,1,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (9,4,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (10,5,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (11,6,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (12,7,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (13,8,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (14,9,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (15,10,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (16,11,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (17,12,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (18,13,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (19,14,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (20,18,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
INSERT INTO `appuserdetail` (`appuserdetailid`, `appuserid`, `androidtoken`) VALUES (21,19,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
/*!40000 ALTER TABLE `appuserdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroom` (
  `classroomid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `minreputationtojoinid` int(10) unsigned DEFAULT NULL,
  `classowner` int(10) unsigned NOT NULL,
  `classname` varchar(50) NOT NULL,
  `ispublic` tinyint(1) NOT NULL,
  `lastupdatedate` datetime NOT NULL,
  `description` varchar(200) NOT NULL,
  `subjectId` int(10) unsigned NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`classroomid`),
  UNIQUE KEY `classname_unique` (`classname`),
  KEY `classroom_fkindex1` (`classowner`),
  KEY `classroom_fkindex2` (`minreputationtojoinid`),
  KEY `classroom_refsubject_RefSubjectId_fk` (`subjectId`),
  KEY `classroom_account_accountId_fk` (`accountId`),
  CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`minreputationtojoinid`) REFERENCES `userreputation` (`userreputationid`),
  CONSTRAINT `classroom_ibfk_2` FOREIGN KEY (`classowner`) REFERENCES `appuser` (`appuserid`),
  CONSTRAINT `classroom_ibfk_3` FOREIGN KEY (`subjectId`) REFERENCES `refsubject` (`refsubjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` (`classroomid`, `minreputationtojoinid`, `classowner`, `classname`, `ispublic`, `lastupdatedate`, `description`, `subjectId`, `accountId`) VALUES (1,NULL,20,'Chemistry classes',1,'2018-04-08 00:00:00','Chemistry classes',1,100);
INSERT INTO `classroom` (`classroomid`, `minreputationtojoinid`, `classowner`, `classname`, `ispublic`, `lastupdatedate`, `description`, `subjectId`, `accountId`) VALUES (2,NULL,20,'Physiscs 101 classes',0,'2019-03-06 00:00:00','Physiscs 101 classes',3,100);
INSERT INTO `classroom` (`classroomid`, `minreputationtojoinid`, `classowner`, `classname`, `ispublic`, `lastupdatedate`, `description`, `subjectId`, `accountId`) VALUES (3,NULL,20,'102 - Mathematics classes',0,'2019-05-02 00:00:00','102 - Mathematics 101 classes',1,100);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroommember`
--

DROP TABLE IF EXISTS `classroommember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroommember` (
  `classroommemberid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `appuserid` int(10) unsigned NOT NULL,
  `classroomid` int(10) unsigned NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `membershipstartdate` datetime NOT NULL,
  `membershipexpirartiondate` datetime NOT NULL,
  `membershiprequestdate` datetime NOT NULL,
  `comments` varchar(150) NOT NULL,
  PRIMARY KEY (`classroommemberid`),
  UNIQUE KEY `classroommmebrukey` (`classroomid`,`appuserid`),
  KEY `classroommember_fk2` (`classroomid`),
  KEY `classroommember_fk3` (`status`),
  KEY `classroommember_fkindex1` (`classroomid`),
  KEY `classroommember_fkindex2` (`appuserid`),
  CONSTRAINT `classroommember_ibfk_1` FOREIGN KEY (`appuserid`) REFERENCES `appuser` (`appuserid`),
  CONSTRAINT `classroommember_ibfk_2` FOREIGN KEY (`classroomid`) REFERENCES `classroom` (`classroomid`),
  CONSTRAINT `classroommember_ibfk_3` FOREIGN KEY (`status`) REFERENCES `refclassroommemberstatus` (`refclassroommemberstatusid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroommember`
--

LOCK TABLES `classroommember` WRITE;
/*!40000 ALTER TABLE `classroommember` DISABLE KEYS */;
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (1,2,1,2,'2018-11-23 00:00:00','2038-11-23 00:00:00','2018-09-05 00:00:00','Appoved');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (2,3,1,2,'2019-03-03 00:00:00','2020-03-03 00:00:00','2019-03-03 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (3,2,2,2,'2019-03-10 00:00:00','2020-03-10 00:00:00','2019-03-10 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (4,6,1,2,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (6,6,2,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (7,11,1,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (8,11,2,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (9,21,1,1,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (10,21,2,1,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (11,21,3,4,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (20,3,3,4,'2019-05-04 00:00:00','2020-05-04 00:00:00','2019-05-04 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (28,2,3,2,'2019-05-05 00:00:00','2020-05-05 00:00:00','2019-05-05 00:00:00','Please approve my add request');
INSERT INTO `classroommember` (`classroommemberid`, `appuserid`, `classroomid`, `status`, `membershipstartdate`, `membershipexpirartiondate`, `membershiprequestdate`, `comments`) VALUES (31,7,3,1,'2019-05-05 00:00:00','2020-05-05 00:00:00','2019-05-05 00:00:00','Please approve my add request');
/*!40000 ALTER TABLE `classroommember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroomsubject`
--

DROP TABLE IF EXISTS `classroomsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classroomsubject` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `classroomId` int(10) unsigned NOT NULL,
  `subjectId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `classroomsubject_classroom_ClassroomId_fk` (`classroomId`),
  KEY `classroomsubject_refsubject_RefSubjectId_fk` (`subjectId`),
  CONSTRAINT `classroomsubject_ibfk_1` FOREIGN KEY (`classroomId`) REFERENCES `classroom` (`classroomid`),
  CONSTRAINT `classroomsubject_ibfk_2` FOREIGN KEY (`subjectId`) REFERENCES `refsubject` (`refsubjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroomsubject`
--

LOCK TABLES `classroomsubject` WRITE;
/*!40000 ALTER TABLE `classroomsubject` DISABLE KEYS */;
INSERT INTO `classroomsubject` (`id`, `classroomId`, `subjectId`) VALUES (1,1,1);
INSERT INTO `classroomsubject` (`id`, `classroomId`, `subjectId`) VALUES (2,1,1);
INSERT INTO `classroomsubject` (`id`, `classroomId`, `subjectId`) VALUES (3,1,2);
INSERT INTO `classroomsubject` (`id`, `classroomId`, `subjectId`) VALUES (4,1,3);
/*!40000 ALTER TABLE `classroomsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contact_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (1,'admin@learnqa.com','Admin',NULL,'admin');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (2,'anigam@expedia.com','Anand','Kumar','Nigam');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (3,'a.nigam@cvent.com','Cvent','Kumar','Nigam');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (4,'anigam@com','Anand','Kumar','Nigam');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (5,'varun@com','Varun',NULL,'Kubba');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (6,'a@c.com','F','F','F');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (7,'anupam@com','Anupam','','Sharma');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (8,'varuan@com','Varun',NULL,'Kubba');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (9,'varuBn@com','Varun',NULL,'Kubba');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (10,'priya@com','Priya','','Nigam');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (11,'a.nigam','Anand','Kumar','Nigam');
INSERT INTO `contact` (`id`, `email`, `first_name`, `middle_name`, `last_name`) VALUES (16,'admin@crajee.com','crajee','','admin');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favoritepost`
--

DROP TABLE IF EXISTS `favoritepost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favoritepost` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `questionid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `favoritepost_appuser_appuserid_fk` (`userid`),
  KEY `favoritepost_question_questionid_fk` (`questionid`),
  CONSTRAINT `favoritepost_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `appuser` (`appuserid`),
  CONSTRAINT `favoritepost_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritepost`
--

LOCK TABLES `favoritepost` WRITE;
/*!40000 ALTER TABLE `favoritepost` DISABLE KEYS */;
INSERT INTO `favoritepost` (`id`, `userid`, `questionid`) VALUES (1,2,2);
INSERT INTO `favoritepost` (`id`, `userid`, `questionid`) VALUES (2,2,12);
/*!40000 ALTER TABLE `favoritepost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `postid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `posttype` tinyint(4) NOT NULL,
  `noofviews` int(10) NOT NULL DEFAULT '0',
  `postedby` int(10) unsigned NOT NULL,
  `postdate` datetime NOT NULL,
  `votes` int(10) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `approvalstatus` tinyint(1) NOT NULL DEFAULT '1',
  `approvalcomment` varchar(250) DEFAULT NULL,
  `classroomid` int(10) unsigned DEFAULT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`postid`),
  KEY `fk_post_user` (`postedby`),
  KEY `postclassroom___fk` (`classroomid`),
  KEY `post_account_accountId_fk` (`accountId`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`postedby`) REFERENCES `appuser` (`appuserid`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`classroomid`) REFERENCES `classroom` (`classroomid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (1,1,13,2,'2019-02-17 18:02:32',0,'First question content',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (2,1,18,2,'2019-02-20 19:51:45',0,'Feb 20 question {{g-ca06c15a-1f4c-4970-a8ad-e3ceecb5cafd}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (3,1,28,2,'2019-02-21 12:49:59',0,'Feb 21 question',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (4,1,38,2,'2019-02-21 17:24:12',0,'Feb 21 ab question content',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (9,0,0,2,'2019-02-23 16:56:18',1,'sample answer',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (10,1,6,2,'2019-02-23 16:58:13',0,'23 content',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (11,1,3,2,'2019-02-23 17:19:07',0,'Feb 23 without title',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (12,1,8,2,'2019-02-23 17:47:12',0,' {{g-3f5c370b-3583-4351-a25b-fa869521078d}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (13,1,2,2,'2019-02-23 18:46:14',0,' {{g-5d3c500c-3b34-495e-ae84-5f78488effd9}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (14,1,16,2,'2019-02-23 19:13:21',0,'image question\n {{g-5fe4471a-bb5b-409d-b7a0-876689f0d62a}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (15,0,0,2,'2019-02-27 14:31:25',1,'Answer to image question',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (16,1,6,2,'2019-02-27 17:25:32',0,'irodov question\n {{g-9f89cccd-1ad4-4041-b7a9-46bcb31d4624}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (17,1,21,2,'2019-02-27 17:40:29',0,'new edit 1 {{g-3d93583c-b324-463c-a9ab-a93e9e678f11}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (18,1,131,2,'2019-02-27 17:45:13',0,'edit 8 {{g-1376f3be-7c87-4aa6-a4c4-2674dd9553ee}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (19,0,0,2,'2019-03-01 18:37:36',1,' {{g-941619a8-fd34-43b1-a671-f17a314a9806}}',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (20,1,11,2,'2019-03-02 16:39:18',0,'asking ng anew question\n\n {{g-56cd2d2d-6772-48bf-8017-23c9646ba050}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (21,0,0,2,'2019-03-03 15:13:43',1,'new answer',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (24,0,0,2,'2019-03-03 15:31:05',0,'new answer',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (25,1,37,3,'2019-03-03 16:48:04',0,'new question 1',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (26,1,9,3,'2019-03-03 18:20:49',0,'new question',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (27,1,21,3,'2019-03-03 18:45:58',0,'testing approvak',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (28,1,17,2,'2019-03-03 19:19:58',0,'new question on March 3',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (29,1,9,2,'2019-03-09 20:35:59',0,'portrait question {{g-8372c9e3-48d7-4977-b1cd-b7c82acf15d9}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (30,1,4,2,'2019-03-09 20:37:27',0,'landscape mode question\n\n {{g-f104fc2e-e9fd-4cc2-8d60-2f5afb3437c2}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (31,1,16,2,'2019-03-09 20:42:43',0,' {{g-ef7b7b32-ab50-4482-81b3-e91fb3881e0a}}\n\n\n {{g-4388ff0f-3972-456c-b137-41deb92a52b8}}\n\n {{g-feb4889d-17b1-4541-bae2-2ac55b7fa1e1}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (32,1,7,2,'2019-03-09 20:48:12',0,' {{g-aed2b1d9-02d6-4629-a023-a64004f2fd9e}}\n {{g-c034bba5-97b6-4816-b840-733ffb9d1317}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (33,1,22,2,'2019-03-10 12:32:52',0,'physics question 1 {{g-6ff69f66-589d-422a-a953-dba457596720}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (34,1,8,2,'2019-03-10 13:42:37',0,'new question',0,NULL,2,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (35,1,22,2,'2019-03-10 13:50:43',0,'new question 2',0,NULL,2,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (36,0,0,2,'2019-03-10 16:06:10',0,' {{g-f7ff49d4-5e3f-489a-96f2-27cae6c30261}}',1,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (37,0,0,3,'2019-03-10 16:06:59',0,'answer from cvent',0,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (38,1,6,2,'2019-03-10 22:03:28',0,'new question',1,NULL,2,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (40,1,0,3,'2019-03-17 21:30:51',0,'Posting a question to test notifications',0,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (42,1,0,3,'2019-03-17 21:33:45',0,'1- Posting a question to test notifications',0,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (43,1,0,3,'2019-03-17 21:38:20',0,'2- Posting a question to test notifications',0,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (44,1,0,3,'2019-03-17 21:39:11',0,'3- Posting a question to test notifications',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (45,1,0,3,'2019-03-17 21:43:30',0,'4- Posting a question to test notifications',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (46,1,0,3,'2019-03-17 21:46:44',0,'5- Posting a question to test notifications',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (47,1,2,3,'2019-03-17 21:48:20',0,'6 - Posting a question to test notifications',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (48,1,15,2,'2019-04-22 19:25:42',0,'S3 image question \n{{a-c863be5a-4604-4ce3-bd61-49fa23f49f90}}',1,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (49,0,0,2,'2019-05-29 16:00:28',0,'lksjsjdf {{a-dc6ff4fc-7c55-4167-b4a3-d205795bd8f0}}',0,NULL,NULL,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (50,1,6,2,'2019-05-29 16:01:10',0,'new question',0,NULL,1,100);
INSERT INTO `post` (`postid`, `posttype`, `noofviews`, `postedby`, `postdate`, `votes`, `content`, `approvalstatus`, `approvalcomment`, `classroomid`, `accountId`) VALUES (51,0,0,2,'2020-04-26 15:47:04',0,'anaswre',0,NULL,NULL,100);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_extracted_text`
--

DROP TABLE IF EXISTS `post_extracted_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_extracted_text` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `postId` int(10) unsigned NOT NULL,
  `extracted_text` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_extracted_text_postId_uindex` (`postId`),
  CONSTRAINT `post_extracted_text_post_PostId_fk` FOREIGN KEY (`postId`) REFERENCES `post` (`postid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_extracted_text`
--

LOCK TABLES `post_extracted_text` WRITE;
/*!40000 ALTER TABLE `post_extracted_text` DISABLE KEYS */;
INSERT INTO `post_extracted_text` (`id`, `postId`, `extracted_text`) VALUES (1,18,'Q Sea\nmounted is equal to 1\nThe distance between the bearings in which the axle of the disc is\n15 cm. The axle is forced to oscillate about\na horizontal axis with a period T = 1.0 s and amplitude = 20\non the bearings.\nFind the maximum value of the gyroscopic forces exerted by the axle\n1.288. A ship moves with velocity v = 36 km per hour along an\narc of a circle of radius R = 200 m. Find the moment of the gyroscop-\nmoment of inertia relative to\nid forces exerted on the bearings by the shaft with a flywheel whose\nthe rotation axis equals 1-\n-3.8-10 kg.m\' and whose rotation velocity n = 300 rpm. The\nrotation axis is oriented along the length of the ship.\n1.289. A locomotive is propelled by a turbine whose axle is paral-\nlel to the axes of wheels. The turbine\'s rotation direction coincides!\nwith that of wheels. The moment of inertia of the turbine rotor rel-\nstive to its own axis is equal to 240 kg.m. Find the additional\nforce exerted by the gyroscopic forces on the rails when the locomo-\ntive moves along a circle of radius R 250 m with velocity\n-50 km per hour. The gauge is equal to 1-1.5 m. The analar.\nvelocity of the turbine equals n = 1500 rpm.\n1.6. ELASTIC DE FORMATIONS OF A SOLID BODY\n• Relation between tensile (compressive) straine and strena\n=oE,\nWhere\nYoung\'s modulus.\nRelation between lateral compressive (tensile) strain #sad longitud\ne compressive) strain\nwal\n	');
/*!40000 ALTER TABLE `post_extracted_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_ocr_text`
--

DROP TABLE IF EXISTS `post_ocr_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_ocr_text` (
  `id` int(10) unsigned NOT NULL,
  `extracted_text` longtext NOT NULL,
  `postId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_ocr_text_post_PostId_fk` (`postId`),
  CONSTRAINT `post_ocr_text_post_PostId_fk` FOREIGN KEY (`postId`) REFERENCES `post` (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_ocr_text`
--

LOCK TABLES `post_ocr_text` WRITE;
/*!40000 ALTER TABLE `post_ocr_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_ocr_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postmedia`
--

DROP TABLE IF EXISTS `postmedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postmedia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagecontent` longblob NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `postmedia_account_accountId_fk` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postmedia`
--

LOCK TABLES `postmedia` WRITE;
/*!40000 ALTER TABLE `postmedia` DISABLE KEYS */;
/*!40000 ALTER TABLE `postmedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postvote`
--

DROP TABLE IF EXISTS `postvote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postvote` (
  `postvoteid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `votedbyuserid` int(10) unsigned NOT NULL,
  `upordown` tinyint(4) NOT NULL,
  `votedate` datetime NOT NULL,
  `postid` int(10) unsigned DEFAULT NULL,
  `posttype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`postvoteid`),
  KEY `appuserfk_idx` (`votedbyuserid`),
  KEY `questionfk_idx` (`postid`),
  CONSTRAINT `postvote_ibfk_1` FOREIGN KEY (`postid`) REFERENCES `post` (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postvote`
--

LOCK TABLES `postvote` WRITE;
/*!40000 ALTER TABLE `postvote` DISABLE KEYS */;
/*!40000 ALTER TABLE `postvote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `questionid` int(10) unsigned NOT NULL,
  `refsubjectid` int(10) unsigned NOT NULL,
  `questionlevelid` int(10) unsigned NOT NULL,
  `refquestionstatusid` int(10) unsigned NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `lastactivedate` datetime NOT NULL,
  `classroomId` int(10) unsigned DEFAULT NULL,
  `question_type_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`questionid`),
  UNIQUE KEY `title_unique` (`title`),
  KEY `questionpost_fk` (`questionid`),
  KEY `question_fkindex1` (`refquestionstatusid`),
  KEY `question_fkindex3` (`questionlevelid`),
  KEY `question_fkindex4` (`refsubjectid`),
  KEY `question___fk_2` (`classroomId`),
  KEY `question_question_type_id_fk` (`question_type_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `post` (`postid`),
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`refsubjectid`) REFERENCES `refsubject` (`refsubjectid`),
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`questionlevelid`) REFERENCES `refquestionlevel` (`questionlevelid`),
  CONSTRAINT `question_question_type_id_fk` FOREIGN KEY (`question_type_id`) REFERENCES `question_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (1,1,1,1,'First question','2019-02-17 18:02:32',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (2,1,1,1,'Feb 20 question','2019-02-20 19:51:45',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (3,1,1,1,'Feb 21 question','2019-02-21 12:49:58',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (4,1,1,1,'Feb 21 a question','2019-02-21 17:24:11',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (10,1,1,1,'Feb 23 question','2019-02-23 16:58:12',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (11,1,1,1,NULL,'2019-02-23 17:19:07',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (12,1,1,1,NULL,'2019-02-23 17:47:11',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (13,1,1,1,NULL,'2019-02-23 18:46:13',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (14,1,1,1,NULL,'2019-02-23 19:13:21',1,3);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (16,1,1,1,NULL,'2019-02-27 17:25:32',1,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (17,1,1,1,NULL,'2019-02-27 17:40:28',1,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (18,1,1,1,NULL,'2019-02-27 17:45:12',1,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (20,1,1,1,NULL,'2019-03-02 16:39:18',1,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (25,1,1,1,NULL,'2019-03-03 16:48:04',1,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (26,1,1,1,NULL,'2019-03-03 18:20:49',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (27,1,1,1,NULL,'2019-03-03 18:45:57',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (28,1,1,1,NULL,'2019-03-03 19:19:58',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (29,1,1,1,NULL,'2019-03-09 20:35:59',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (30,1,1,1,NULL,'2019-03-09 20:37:27',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (31,1,1,1,NULL,'2019-03-09 20:42:42',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (32,1,1,1,NULL,'2019-03-09 20:48:11',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (33,1,1,1,NULL,'2019-03-10 12:32:52',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (34,1,1,1,NULL,'2019-03-10 13:42:37',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (35,1,1,1,NULL,'2019-03-10 13:50:43',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (38,1,1,1,NULL,'2019-03-10 22:03:28',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (40,1,1,1,'Posting a question','2019-03-17 21:30:55',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (42,1,1,1,'1- Posting a question','2019-03-17 21:33:52',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (43,1,1,1,'2 - Posting a question','2019-03-17 21:38:20',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (44,1,1,1,'3 - Posting a question','2019-03-17 21:39:11',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (45,1,1,1,'4 - Posting a question','2019-03-17 21:43:30',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (46,1,1,1,'5 - Posting a question','2019-03-17 21:46:43',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (47,1,1,1,'6 - Posting a question','2019-03-17 21:48:19',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (48,1,1,1,NULL,'2019-04-22 19:25:42',NULL,NULL);
INSERT INTO `question` (`questionid`, `refsubjectid`, `questionlevelid`, `refquestionstatusid`, `title`, `lastactivedate`, `classroomId`, `question_type_id`) VALUES (50,1,1,1,NULL,'2019-05-29 16:01:10',NULL,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_choice`
--

DROP TABLE IF EXISTS `question_choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_choice` (
  `id` int(10) unsigned NOT NULL,
  `choice` text NOT NULL,
  `questionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `question_choice_question_questionId_fk` (`questionId`),
  CONSTRAINT `question_choice_question_questionId_fk` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_choice`
--

LOCK TABLES `question_choice` WRITE;
/*!40000 ALTER TABLE `question_choice` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` (`id`, `name`, `description`) VALUES (1,'single choice','single choice question');
INSERT INTO `question_type` (`id`, `name`, `description`) VALUES (2,'multiple choice','multiple choice');
INSERT INTO `question_type` (`id`, `name`, `description`) VALUES (3,'subjective','subjective');
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionclassroom`
--

DROP TABLE IF EXISTS `questionclassroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionclassroom` (
  `questionclassroomid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `classroomid` int(10) unsigned NOT NULL,
  `questionid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`questionclassroomid`),
  KEY `questionclassroom_fkindex1` (`questionid`),
  KEY `questionclassroom_fkindex2` (`classroomid`),
  CONSTRAINT `questionclassroom_ibfk_1` FOREIGN KEY (`classroomid`) REFERENCES `classroom` (`classroomid`),
  CONSTRAINT `questionclassroom_ibfk_2` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionclassroom`
--

LOCK TABLES `questionclassroom` WRITE;
/*!40000 ALTER TABLE `questionclassroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionclassroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionnaire` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` text,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `visibilityId` int(10) unsigned DEFAULT NULL,
  `statusId` int(10) unsigned DEFAULT NULL,
  `instructions` text,
  PRIMARY KEY (`id`),
  KEY `questionnaire_ref_questionnaire_status_id_fk` (`statusId`),
  KEY `questionnaire_ref_questionnaire_visibility_id_fk` (`visibilityId`),
  CONSTRAINT `questionnaire_ref_questionnaire_status_id_fk` FOREIGN KEY (`statusId`) REFERENCES `ref_questionnaire_status` (`id`),
  CONSTRAINT `questionnaire_ref_questionnaire_visibility_id_fk` FOREIGN KEY (`visibilityId`) REFERENCES `ref_questionnaire_visibility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionnaire_question`
--

DROP TABLE IF EXISTS `questionnaire_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionnaire_question` (
  `id` int(10) unsigned NOT NULL,
  `questionId` int(10) unsigned NOT NULL,
  `questionnaireId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionnaire_question_question_questionId_fk` (`questionId`),
  KEY `questionnaire_question_questionnaire_id_fk` (`questionnaireId`),
  CONSTRAINT `questionnaire_question_question_questionId_fk` FOREIGN KEY (`questionId`) REFERENCES `question` (`questionid`),
  CONSTRAINT `questionnaire_question_questionnaire_id_fk` FOREIGN KEY (`questionnaireId`) REFERENCES `questionnaire` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionnaire_question`
--

LOCK TABLES `questionnaire_question` WRITE;
/*!40000 ALTER TABLE `questionnaire_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionnaire_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionrating`
--

DROP TABLE IF EXISTS `questionrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionrating` (
  `questionratingid` int(10) NOT NULL,
  `questionlevelid` int(10) unsigned NOT NULL,
  `ratedbyuserid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`questionratingid`),
  KEY `appuser+fk_idx` (`ratedbyuserid`),
  KEY `questionlevel_fk_idx` (`questionlevelid`),
  CONSTRAINT `questionrating_ibfk_1` FOREIGN KEY (`questionlevelid`) REFERENCES `refquestionlevel` (`questionlevelid`),
  CONSTRAINT `questionrating_ibfk_2` FOREIGN KEY (`ratedbyuserid`) REFERENCES `appuser` (`appuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `questionratingsummaryid` int(10) unsigned NOT NULL,
  `noofeasyratings` int(11) DEFAULT NULL,
  `noofmediumratings` int(11) DEFAULT NULL,
  `noofhardratings` int(11) DEFAULT NULL,
  `questionid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`questionratingsummaryid`),
  KEY `question_fk_idx` (`questionid`),
  CONSTRAINT `questionratingsummary_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `questiontagid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `questionid` int(10) unsigned NOT NULL,
  `tagid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`questiontagid`),
  UNIQUE KEY `questiontaguniquekey` (`questionid`,`tagid`),
  KEY `questiontag_fkindex1` (`tagid`),
  KEY `questiontag_fkindex2` (`questionid`),
  CONSTRAINT `questiontag_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`),
  CONSTRAINT `questiontag_ibfk_2` FOREIGN KEY (`tagid`) REFERENCES `tag` (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questiontag`
--

LOCK TABLES `questiontag` WRITE;
/*!40000 ALTER TABLE `questiontag` DISABLE KEYS */;
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (1,1,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (2,2,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (3,3,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (6,4,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (9,10,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (10,11,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (11,12,1);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (12,13,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (13,14,1);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (14,16,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (19,17,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (27,18,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (28,20,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (31,25,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (30,26,1);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (32,27,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (33,28,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (34,29,1);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (35,30,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (36,31,1);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (37,32,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (45,33,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (39,34,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (44,35,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (46,38,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (48,40,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (49,42,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (50,43,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (51,44,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (52,45,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (53,46,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (54,47,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (55,48,2);
INSERT INTO `questiontag` (`questiontagid`, `questionid`, `tagid`) VALUES (56,50,2);
/*!40000 ALTER TABLE `questiontag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_questionnaire_status`
--

DROP TABLE IF EXISTS `ref_questionnaire_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ref_questionnaire_status` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_questionnaire_status`
--

LOCK TABLES `ref_questionnaire_status` WRITE;
/*!40000 ALTER TABLE `ref_questionnaire_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ref_questionnaire_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ref_questionnaire_visibility`
--

DROP TABLE IF EXISTS `ref_questionnaire_visibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ref_questionnaire_visibility` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ref_questionnaire_visibility`
--

LOCK TABLES `ref_questionnaire_visibility` WRITE;
/*!40000 ALTER TABLE `ref_questionnaire_visibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `ref_questionnaire_visibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refclassroommemberstatus`
--

DROP TABLE IF EXISTS `refclassroommemberstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refclassroommemberstatus` (
  `refclassroommemberstatusid` int(10) unsigned NOT NULL,
  `classroommemberstatusname` varchar(20) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`refclassroommemberstatusid`),
  UNIQUE KEY `classroommemberstatusname_unique` (`classroommemberstatusname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refclassroommemberstatus`
--

LOCK TABLES `refclassroommemberstatus` WRITE;
/*!40000 ALTER TABLE `refclassroommemberstatus` DISABLE KEYS */;
INSERT INTO `refclassroommemberstatus` (`refclassroommemberstatusid`, `classroommemberstatusname`, `description`) VALUES (1,'Pending Approval','Applied for membership');
INSERT INTO `refclassroommemberstatus` (`refclassroommemberstatusid`, `classroommemberstatusname`, `description`) VALUES (2,'Active','request to join is approved');
INSERT INTO `refclassroommemberstatus` (`refclassroommemberstatusid`, `classroommemberstatusname`, `description`) VALUES (3,'Expired','request to join is approved');
INSERT INTO `refclassroommemberstatus` (`refclassroommemberstatusid`, `classroommemberstatusname`, `description`) VALUES (4,'Rejected','rejected');
/*!40000 ALTER TABLE `refclassroommemberstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refquestionlevel`
--

DROP TABLE IF EXISTS `refquestionlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refquestionlevel` (
  `questionlevelid` int(10) unsigned NOT NULL,
  `levelname` varchar(20) NOT NULL,
  `levelorder` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`questionlevelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refquestionlevel`
--

LOCK TABLES `refquestionlevel` WRITE;
/*!40000 ALTER TABLE `refquestionlevel` DISABLE KEYS */;
INSERT INTO `refquestionlevel` (`questionlevelid`, `levelname`, `levelorder`) VALUES (1,'medium',1);
INSERT INTO `refquestionlevel` (`questionlevelid`, `levelname`, `levelorder`) VALUES (2,'hard',3);
INSERT INTO `refquestionlevel` (`questionlevelid`, `levelname`, `levelorder`) VALUES (3,'easy',1);
/*!40000 ALTER TABLE `refquestionlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refquestionstatus`
--

DROP TABLE IF EXISTS `refquestionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refquestionstatus` (
  `refquestionstatusid` int(10) unsigned NOT NULL,
  `questionstatusname` varchar(20) NOT NULL,
  PRIMARY KEY (`refquestionstatusid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refquestionstatus`
--

LOCK TABLES `refquestionstatus` WRITE;
/*!40000 ALTER TABLE `refquestionstatus` DISABLE KEYS */;
INSERT INTO `refquestionstatus` (`refquestionstatusid`, `questionstatusname`) VALUES (1,'new');
/*!40000 ALTER TABLE `refquestionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refsubject`
--

DROP TABLE IF EXISTS `refsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refsubject` (
  `refsubjectid` int(10) unsigned NOT NULL,
  `subjectname` varchar(20) NOT NULL,
  `subjectdescription` varchar(20) NOT NULL,
  PRIMARY KEY (`refsubjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refsubject`
--

LOCK TABLES `refsubject` WRITE;
/*!40000 ALTER TABLE `refsubject` DISABLE KEYS */;
INSERT INTO `refsubject` (`refsubjectid`, `subjectname`, `subjectdescription`) VALUES (1,'Mathematics','mathematics');
INSERT INTO `refsubject` (`refsubjectid`, `subjectname`, `subjectdescription`) VALUES (2,'Chemistry','chemistry');
INSERT INTO `refsubject` (`refsubjectid`, `subjectname`, `subjectdescription`) VALUES (3,'Physics','physics');
/*!40000 ALTER TABLE `refsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refusertype`
--

DROP TABLE IF EXISTS `refusertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refusertype` (
  `usertypeid` tinyint(4) NOT NULL,
  `usertype` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usertypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refusertype`
--

LOCK TABLES `refusertype` WRITE;
/*!40000 ALTER TABLE `refusertype` DISABLE KEYS */;
INSERT INTO `refusertype` (`usertypeid`, `usertype`, `description`) VALUES (1,'application_admin','User who creates new accounts');
INSERT INTO `refusertype` (`usertypeid`, `usertype`, `description`) VALUES (2,'account_admin','User who creates other users and classrooms in an account');
INSERT INTO `refusertype` (`usertypeid`, `usertype`, `description`) VALUES (3,'classroom_admin','User who is the admin of the classroom');
INSERT INTO `refusertype` (`usertypeid`, `usertype`, `description`) VALUES (4,'application_user','User who usese the application');
/*!40000 ALTER TABLE `refusertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privilege` (
  `id` int(10) unsigned NOT NULL,
  `roleId` int(10) unsigned NOT NULL,
  `privilegeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tagid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tagname` varchar(20) NOT NULL,
  `tagdescription` varchar(255) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`tagid`, `tagname`, `tagdescription`) VALUES (1,'javascript3','This is an OO programming language');
INSERT INTO `tag` (`tagid`, `tagname`, `tagdescription`) VALUES (2,'java','This is an OO programming language');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userreputation`
--

DROP TABLE IF EXISTS `userreputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userreputation` (
  `userreputationid` int(10) unsigned NOT NULL,
  `reputationname` varchar(20) NOT NULL,
  PRIMARY KEY (`userreputationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreputation`
--

LOCK TABLES `userreputation` WRITE;
/*!40000 ALTER TABLE `userreputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `userreputation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-09 20:11:53
