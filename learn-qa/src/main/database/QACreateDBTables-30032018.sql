USE `learnqa`;
--
-- Table structure for table `userreputation`
--




CREATE TABLE `userreputation` (
  `UserReputationId` int(10) unsigned NOT NULL,
  `ReputationName` varchar(20) NOT NULL,
  PRIMARY KEY (`UserReputationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `UserTypeMaster`
--




CREATE TABLE `UserTypeMaster` (
  `UserTypeId` tinyint(4) NOT NULL,
  `UserType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`UserTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `appuser`
--


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

--
-- Table structure for table `AppUserDetail`
--


CREATE TABLE `AppUserDetail` (
  `AppUserDetailId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AppUserId` int(10) unsigned NOT NULL,
  `AndroidToken` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`AppUserDetailId`),
  KEY `AppUserDetail_appuser_AppUserId_fk` (`AppUserId`),
  CONSTRAINT `AppUserDetail_appuser_AppUserId_fk` FOREIGN KEY (`AppUserId`) REFERENCES `appuser` (`AppUserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Table structure for table `classroom`
--


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

--
-- Table structure for table `post`
--


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
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=latin1;

--
-- Table structure for table `questionlevel`
--


CREATE TABLE `questionlevel` (
  `QuestionLevelId` int(10) unsigned NOT NULL,
  `LevelName` varchar(20) NOT NULL,
  `LevelOrder` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`QuestionLevelId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `refsubject`
--


CREATE TABLE `refsubject` (
  `RefSubjectId` int(10) unsigned NOT NULL,
  `SubjectName` varchar(20) NOT NULL,
  `SubjectDescription` varchar(20) NOT NULL,
  PRIMARY KEY (`RefSubjectId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `question`
--


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

--
-- Table structure for table `FavoritePost`
--


CREATE TABLE `FavoritePost` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `UserId` int(10) unsigned NOT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FavoritePost_question_questionId_fk` (`QuestionId`),
  KEY `FavoritePost_appuser_AppUserId_fk` (`UserId`),
  CONSTRAINT `FavoritePost_appuser_AppUserId_fk` FOREIGN KEY (`UserId`) REFERENCES `appuser` (`AppUserId`),
  CONSTRAINT `FavoritePost_question_questionId_fk` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Table structure for table `PostMedia`
--


CREATE TABLE `PostMedia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ImageContent` longblob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1 COMMENT='stores the media image';

--
-- Table structure for table `answer`
--


CREATE TABLE `answer` (
  `AnswerId` int(10) unsigned NOT NULL,
  `QuestionId` int(10) unsigned NOT NULL,
  PRIMARY KEY (`AnswerId`),
  KEY `Answer_FKIndex1` (`QuestionId`),
  KEY `answerPost_fk` (`AnswerId`),
  CONSTRAINT `answerPost_fk` FOREIGN KEY (`AnswerId`) REFERENCES `post` (`PostId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`questionId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `answercomment`
--


CREATE TABLE `answercomment` (
  `AnswerCommentId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `AnswerId` int(10) unsigned NOT NULL,
  `AnswerComment` longtext NOT NULL,
  PRIMARY KEY (`AnswerCommentId`),
  KEY `AnswerComment_FKIndex1` (`AnswerId`),
  CONSTRAINT `answercomment_ibfk_1` FOREIGN KEY (`AnswerId`) REFERENCES `answer` (`AnswerId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `refclassroommemberstatus`
--


CREATE TABLE `refclassroommemberstatus` (
  `RefClassroomMemberStatusId` int(10) unsigned NOT NULL,
  `ClassroommemberStatusName` varchar(20) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`RefClassroomMemberStatusId`),
  UNIQUE KEY `ClassroommemberStatusName_UNIQUE` (`ClassroommemberStatusName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `classroommember`
--


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


--
-- Table structure for table `postvote`
--


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



--
-- Table structure for table `questionclassroom`
--


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



--
-- Table structure for table `questionrating`
--


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

--
-- Table structure for table `questionratingsummary`
--


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


--
-- Table structure for table `refquestionstatus`
--


CREATE TABLE `refquestionstatus` (
  `RefQuestionStatusId` int(10) unsigned NOT NULL,
  `QuestionStatusName` varchar(20) NOT NULL,
  PRIMARY KEY (`RefQuestionStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `tag`
--


CREATE TABLE `tag` (
  `TagId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TagName` varchar(20) NOT NULL,
  `TagDescription` varchar(255) NOT NULL,
  PRIMARY KEY (`TagId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;



--
-- Table structure for table `questiontag`
--


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
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;





