CREATE DATABASE  IF NOT EXISTS `learn-qa-1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `learn-qa-1`;
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
-- Table structure for table `AppUserDetail`
--

DROP TABLE IF EXISTS `AppUserDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AppUserDetail` (
  `AppUserDetailId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AppUserId` int(10) unsigned NOT NULL,
  `AndroidToken` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`AppUserDetailId`),
  KEY `AppUserDetail_appuser_AppUserId_fk` (`AppUserId`),
  CONSTRAINT `AppUserDetail_appuser_AppUserId_fk` FOREIGN KEY (`AppUserId`) REFERENCES `appuser` (`AppUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FavoritePost`
--

DROP TABLE IF EXISTS `FavoritePost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FavoritePost` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(10) unsigned NOT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FavoritePost_question_questionId_fk` (`QuestionId`),
  KEY `FavoritePost_appuser_AppUserId_fk` (`UserId`),
  CONSTRAINT `FavoritePost_appuser_AppUserId_fk` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`AppUserId`),
  CONSTRAINT `FavoritePost_question_questionId_fk` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PostMedia`
--

DROP TABLE IF EXISTS `PostMedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PostMedia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ImageContent` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1 COMMENT='stores the media image';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserTypeMaster`
--

DROP TABLE IF EXISTS `UserTypeMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserTypeMaster` (
  `UserTypeId` tinyint(4) NOT NULL,
  `UserType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UserTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `UserTypeId` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`AppUserId`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `AppUser_FKIndex1` (`UserReputationId`),
  KEY `appusertype___fk` (`UserTypeId`),
  CONSTRAINT `appuser_ibfk_1` FOREIGN KEY (`UserReputationId`) REFERENCES `userreputation` (`UserReputationId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `appusertype___fk` FOREIGN KEY (`UserTypeId`) REFERENCES `UserTypeMaster` (`UserTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `ApprovalStatus` tinyint(1) NOT NULL DEFAULT '1',
  `ApprovalComment` varchar(250) DEFAULT NULL,
  `ClassroomId` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`PostId`),
  KEY `fk_post_user` (`PostedBy`),
  KEY `postclassroom___fk` (`ClassroomId`),
  CONSTRAINT `fk_post_user` FOREIGN KEY (`PostedBy`) REFERENCES `appuser` (`AppUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `postclassroom___fk` FOREIGN KEY (`ClassroomId`) REFERENCES `classroom` (`ClassroomId`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `LastActiveDate` datetime NOT NULL,
  `IsPublic` tinyint(1) NOT NULL,
  PRIMARY KEY (`questionId`),
  UNIQUE KEY `Title_UNIQUE` (`Title`),
  KEY `Question_FKIndex1` (`RefQuestionStatusId`),
  KEY `Question_FKIndex3` (`QuestionLevelId`),
  KEY `Question_FKIndex4` (`RefSubjectId`),
  KEY `questionPost_fk` (`questionId`),
  CONSTRAINT `questionPost_fk` FOREIGN KEY (`questionId`) REFERENCES `post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`QuestionLevelId`) REFERENCES `questionlevel` (`QuestionLevelId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_ibfk_4` FOREIGN KEY (`RefSubjectId`) REFERENCES `refsubject` (`RefSubjectId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionAddSproc`(
pPostedBy int,
pTitle varchar(50),
pContent text,
pRefSubjectId int,
pClassroomId int,
pIsPublic tinyint
)
BEGIN
	DECLARE vQuestionId INT DEFAULT 0;

	insert into post ( postdate, PostedBy, Content, posttype, classroomId) 
	values ( now(), pPostedBy, pContent,  1, pClassroomId);
    
    SET vQuestionId = LAST_INSERT_ID();

	insert into question (questionId, RefSubjectId, QuestionlevelId, refQuestionStatusId , 
     lastactivedate, Title, isPublic) 
	values (vQuestionId,pRefSubjectId,1, 1,  now(), pTitle, pIsPublic );
	
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
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
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
        p.postType,
		u.Firstname,
		u.middleName,
		u.lastName,
		RefQuestionStatusId,
		Title,
		p.Content,
		p.NoOfViews as NoOfViews,
		p.PostDate,
		LastActiveDate,
		p.Votes,
    p.ClassroomId as ClassroomId,
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
  
  select TagId from questionTag where QuestionId = pQuestionId;


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

-- Dump completed on 2018-01-13 20:21:27
