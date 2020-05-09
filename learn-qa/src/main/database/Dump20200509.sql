-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: crajee_db
-- ------------------------------------------------------
-- Server version	5.7.27

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `accountId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accountName` varchar(50) NOT NULL,
  `accountDescription` text,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account_preference`
--

DROP TABLE IF EXISTS `account_preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `answer_mc`
--

DROP TABLE IF EXISTS `answer_mc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `answercomment`
--

DROP TABLE IF EXISTS `answercomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `appuserdetail`
--

DROP TABLE IF EXISTS `appuserdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `classroommember`
--

DROP TABLE IF EXISTS `classroommember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `classroomsubject`
--

DROP TABLE IF EXISTS `classroomsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `favoritepost`
--

DROP TABLE IF EXISTS `favoritepost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `post_extracted_text`
--

DROP TABLE IF EXISTS `post_extracted_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `post_ocr_text`
--

DROP TABLE IF EXISTS `post_ocr_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `postmedia`
--

DROP TABLE IF EXISTS `postmedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postmedia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagecontent` longblob NOT NULL,
  `accountId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `postmedia_account_accountId_fk` (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `postvote`
--

DROP TABLE IF EXISTS `postvote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `question_choice`
--

DROP TABLE IF EXISTS `question_choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_type` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `questionclassroom`
--

DROP TABLE IF EXISTS `questionclassroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `questionnaire`
--

DROP TABLE IF EXISTS `questionnaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `questionnaire_question`
--

DROP TABLE IF EXISTS `questionnaire_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `questionrating`
--

DROP TABLE IF EXISTS `questionrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `questionratingsummary`
--

DROP TABLE IF EXISTS `questionratingsummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `questiontag`
--

DROP TABLE IF EXISTS `questiontag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
-- Table structure for table `ref_questionnaire_status`
--

DROP TABLE IF EXISTS `ref_questionnaire_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_questionnaire_status` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ref_questionnaire_visibility`
--

DROP TABLE IF EXISTS `ref_questionnaire_visibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ref_questionnaire_visibility` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refclassroommemberstatus`
--

DROP TABLE IF EXISTS `refclassroommemberstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refclassroommemberstatus` (
  `refclassroommemberstatusid` int(10) unsigned NOT NULL,
  `classroommemberstatusname` varchar(20) NOT NULL,
  `description` varchar(50) NOT NULL,
  PRIMARY KEY (`refclassroommemberstatusid`),
  UNIQUE KEY `classroommemberstatusname_unique` (`classroommemberstatusname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refquestionlevel`
--

DROP TABLE IF EXISTS `refquestionlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refquestionlevel` (
  `questionlevelid` int(10) unsigned NOT NULL,
  `levelname` varchar(20) NOT NULL,
  `levelorder` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`questionlevelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refquestionstatus`
--

DROP TABLE IF EXISTS `refquestionstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refquestionstatus` (
  `refquestionstatusid` int(10) unsigned NOT NULL,
  `questionstatusname` varchar(20) NOT NULL,
  PRIMARY KEY (`refquestionstatusid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refsubject`
--

DROP TABLE IF EXISTS `refsubject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refsubject` (
  `refsubjectid` int(10) unsigned NOT NULL,
  `subjectname` varchar(20) NOT NULL,
  `subjectdescription` varchar(20) NOT NULL,
  PRIMARY KEY (`refsubjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `refusertype`
--

DROP TABLE IF EXISTS `refusertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refusertype` (
  `usertypeid` tinyint(4) NOT NULL,
  `usertype` varchar(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usertypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privilege` (
  `id` int(10) unsigned NOT NULL,
  `roleId` int(10) unsigned NOT NULL,
  `privilegeId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `tagid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tagname` varchar(20) NOT NULL,
  `tagdescription` varchar(255) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userreputation`
--

DROP TABLE IF EXISTS `userreputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userreputation` (
  `userreputationid` int(10) unsigned NOT NULL,
  `reputationname` varchar(20) NOT NULL,
  PRIMARY KEY (`userreputationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'crajee_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `answeraddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `answeraddsproc`(
 pquestionid int,
 pansweredbyuserid int,
 pcontent text,
 paccountId int
)
begin

declare vpostid int default 0;
insert into post (postedby, posttype, votes, content, postdate, accountId ) values (pansweredbyuserid, 0, 1, pcontent, now(), paccountId);
set vpostid = last_insert_id();
 insert into answer (answerid,questionid )
    values (vpostid, pquestionid);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `classroomjoin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomjoin`(
 puserid int,
 pclassroomid int

)
begin

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `joinclassroomsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `joinclassroomsproc`(
 puserid int,
 pclassroomid int
)
begin

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionaddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionaddsproc`(
ppostedby int,
ptitle varchar(50),
pcontent text,
prefsubjectid int,
pclassroomid int
)
begin
 declare vquestionid int default 0;

 insert into post ( postdate, postedby, content, posttype, classroomid)
 values ( now(), ppostedby, pcontent,  1, pclassroomid);

    set vquestionid = last_insert_id();

 insert into question (questionid, refsubjectid, questionlevelid, refquestionstatusid ,
     lastactivedate, title)
 values (vquestionid,prefsubjectid,1, 1,  now(), ptitle );

 -- select questionid into vquestionid from question where title = ptitle;

 call questionget(vquestionid);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionget` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionget`(
 pquestionid int
)
begin

 select
  questionid,
  refsubjectid,
  questionlevelid,
  p.postedby,
        p.posttype,
  u.firstname,
  u.middlename,
  u.lastname,
  refquestionstatusid,
  title,
  p.content,
  p.noofviews as noofviews,
  p.postdate,
  lastactivedate,
  p.votes,
    p.classroomid as classroomid,
  ispublic
 from
  question q
    join post p on p.postid = q.questionid
  join appuser u on u.appuserid = p.postedby
 where
  questionid = pquestionid;

 select
  answerid, p.postedby as answeredbyuserid, questionid, p.votes, p.content
 from
  answer a
    join post p on p.postid = a.answerid
 where
  questionid = pquestionid;

  select tagid from questiontag where questionid = pquestionid;


end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionupdatestats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionupdatestats`(
 pquestionid int,
 pvotes int
)
begin

 declare vnoofviews, vvotes int default 0;

 select noofviews into vnoofviews from question where questionid = pquestionid;
 select votes into vvotes from question where questionid = pquestionid;


 update question
 set noofviews = vnoofviews +1,
  lastactivedate = now();

 if(pvotes != 0)
  then
   update question
   set votes = vvotes +pvotes;

 end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `useraddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `useraddsproc`(
pemail varchar(20),
ppasword varchar(20),
pfirstname varchar(20),
pmiddlename varchar(20),
plastname varchar(20)
)
begin

 insert into appuser(email,pasword,firstname,middlename,lastname)values(pemail,ppasword,pfirstname,pmiddlename,plastname);

 select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
 from appuser where email = pemail;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userget` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userget`(
pappuserid int,
pemail varchar(20)
)
begin

 if(pappuserid is not null)
 then
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where appuserid = pappuserid;
 else
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where email = pemail;
 end if;


end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userupdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userupdate`(
pappuserid int,
puserreputationid int,
pemail varchar(20)
)
begin

 if(puserreputationid is not null)
 then
  update appuser set userreputationid = puserreputationid where appuserid = pappuserid;
 end if;

 if(pemail is not null)
 then
  update appuser set email = pemail,pasword = ppasword where appuserid = pappuserid;
 end if;

 if(ppasword is not null)
 then
  update appuser set pasword = ppasword where appuserid = pappuserid;
 end if;

 select * from appuser where email = pemail;

end ;;
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

-- Dump completed on 2020-05-09 20:13:35
