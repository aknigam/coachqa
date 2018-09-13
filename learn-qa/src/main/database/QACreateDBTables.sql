use `crajee`;
--
-- table structure for table `userreputation`
--




create table `userreputation` (
  `userreputationid` int(10) unsigned not null,
  `reputationname` varchar(20) not null,
  primary key (`userreputationid`)
) engine=innodb default charset=latin1;


--
-- table structure for table `refusertype`
--




create table `refusertype` (
  `usertypeid` tinyint(4) not null,
  `usertype` varchar(20) default null,
  primary key (`usertypeid`)
) engine=innodb default charset=latin1;


--
-- table structure for table `appuser`
--


create table `appuser` (
  `appuserid` int(10) unsigned not null auto_increment,
  `userreputationid` int(10) unsigned default null,
  `email` varchar(20) not null,
  `pasword` varchar(20) not null,
  `firstname` varchar(20) not null,
  `middlename` varchar(20) default null,
  `lastname` varchar(20) not null,
  `usertypeid` tinyint(4) default '1',
  primary key (`appuserid`),
  unique key `email_unique` (`email`),
  key `appuser_fkindex1` (`userreputationid`),
  key `appusertype___fk` (`usertypeid`),
  constraint `appuser_ibfk_1` foreign key (`userreputationid`) references `userreputation` (`userreputationid`) on delete no action on update no action,
  constraint `appusertype___fk` foreign key (`usertypeid`) references `refusertype` (`usertypeid`)
) engine=innodb auto_increment=9 default charset=latin1;

--
-- table structure for table `appuserdetail`
--


create table `appuserdetail` (
  `appuserdetailid` int(10) unsigned not null auto_increment,
  `appuserid` int(10) unsigned not null,
  `androidtoken` varchar(4000) character set utf8 default null,
  primary key (`appuserdetailid`),
  key `appuserdetail_appuser_appuserid_fk` (`appuserid`),
  constraint `appuserdetail_appuser_appuserid_fk` foreign key (`appuserid`) references `appuser` (`appuserid`)
) engine=innodb default charset=latin1;



--
-- table structure for table `classroom`
--


create table `classroom` (
  `classroomid` int(10) unsigned not null auto_increment,
  `minreputationtojoinid` int(10) unsigned default null,
  `classowner` int(10) unsigned not null,
  `classname` varchar(20) character set dec8 not null,
  `ispublic` tinyint(1) not null,
  `lastupdatedate` datetime not null,
  `description` varchar(200) not null,
  primary key (`classroomid`),
  unique key `classname_unique` (`classname`),
  key `classroom_fkindex1` (`classowner`),
  key `classroom_fkindex2` (`minreputationtojoinid`),
  constraint `classroom_ibfk_1` foreign key (`classowner`) references `appuser` (`appuserid`) on delete no action on update no action,
  constraint `classroom_ibfk_2` foreign key (`minreputationtojoinid`) references `userreputation` (`userreputationid`) on delete no action on update no action
) engine=innodb auto_increment=8 default charset=latin1;

--
-- table structure for table `post`
--


create table `post` (
  `postid` int(10) unsigned not null auto_increment,
  `posttype` tinyint(4) not null,
  `noofviews` int(10) not null default '0',
  `postedby` int(10) unsigned not null,
  `postdate` datetime not null,
  `votes` int(10) not null default '0',
  `content` text not null,
  `approvalstatus` tinyint(1) not null default '1',
  `approvalcomment` varchar(250) default null,
  `classroomid` int(10) unsigned default null,
  primary key (`postid`),
  key `fk_post_user` (`postedby`),
  key `postclassroom___fk` (`classroomid`),
  constraint `fk_post_user` foreign key (`postedby`) references `appuser` (`appuserid`) on delete no action on update no action,
  constraint `postclassroom___fk` foreign key (`classroomid`) references `classroom` (`classroomid`)
) engine=innodb auto_increment=342 default charset=latin1;

--
-- table structure for table `refquestionlevel`
--


create table `refquestionlevel` (
  `questionlevelid` int(10) unsigned not null,
  `levelname` varchar(20) not null,
  `levelorder` int(10) unsigned not null default '1',
  primary key (`questionlevelid`)
) engine=innodb default charset=latin1;


--
-- table structure for table `refsubject`
--


create table `refsubject` (
  `refsubjectid` int(10) unsigned not null,
  `subjectname` varchar(20) not null,
  `subjectdescription` varchar(20) not null,
  primary key (`refsubjectid`)
) engine=innodb default charset=latin1;

--
-- table structure for table `question`
--

ALTER TABLE classroom ADD subjectId INT(10) UNSIGNED not NULL;
ALTER TABLE classroom
ADD CONSTRAINT classroom_refsubject_RefSubjectId_fk
FOREIGN KEY (subjectId) REFERENCES refsubject (RefSubjectId);

create table `question` (
  `questionid` int(10) unsigned not null,
  `refsubjectid` int(10) unsigned not null,
  `questionlevelid` int(10) unsigned not null,
  `refquestionstatusid` int(10) unsigned not null,
  `title` varchar(50) not null,
  `lastactivedate` datetime not null,
  `ispublic` tinyint(1) not null,
  primary key (`questionid`),
  unique key `title_unique` (`title`),
  key `question_fkindex1` (`refquestionstatusid`),
  key `question_fkindex3` (`questionlevelid`),
  key `question_fkindex4` (`refsubjectid`),
  key `questionpost_fk` (`questionid`),
  constraint `questionpost_fk` foreign key (`questionid`) references `post` (`postid`) on delete no action on update no action,
  constraint `question_ibfk_3` foreign key (`questionlevelid`) references `refquestionlevel` (`questionlevelid`) on delete no action on update no action,
  constraint `question_ibfk_4` foreign key (`refsubjectid`) references `refsubject` (`refsubjectid`) on delete no action on update no action
) engine=innodb default charset=latin1;

--
-- table structure for table `favoritepost`
--


create table `favoritepost` (
  `id` int(10) unsigned not null auto_increment,
  `userid` int(10) unsigned not null,
  `questionid` int(10) unsigned not null,
  primary key (`id`),
  key `favoritepost_question_questionid_fk` (`questionid`),
  key `favoritepost_appuser_appuserid_fk` (`userid`),
  constraint `favoritepost_appuser_appuserid_fk` foreign key (`userid`) references `appuser` (`appuserid`),
  constraint `favoritepost_question_questionid_fk` foreign key (`questionid`) references `question` (`questionid`)
) engine=innodb auto_increment=17 default charset=latin1;

--
-- table structure for table `postmedia`
--


create table `postmedia` (
  `id` int(11) not null auto_increment,
  `imagecontent` longblob not null,
  primary key (`id`)
) engine=innodb auto_increment=47 default charset=latin1 comment='stores the media image';

--
-- table structure for table `answer`
--


create table `answer` (
  `answerid` int(10) unsigned not null,
  `questionid` int(10) unsigned not null,
  primary key (`answerid`),
  key `answer_fkindex1` (`questionid`),
  key `answerpost_fk` (`answerid`),
  constraint `answerpost_fk` foreign key (`answerid`) references `post` (`postid`) on delete no action on update no action,
  constraint `answer_ibfk_1` foreign key (`questionid`) references `question` (`questionid`) on delete no action on update no action
) engine=innodb default charset=latin1;

--
-- table structure for table `answercomment`
--


create table `answercomment` (
  `answercommentid` int(10) unsigned not null auto_increment,
  `answerid` int(10) unsigned not null,
  `answercomment` longtext not null,
  primary key (`answercommentid`),
  key `answercomment_fkindex1` (`answerid`),
  constraint `answercomment_ibfk_1` foreign key (`answerid`) references `answer` (`answerid`) on delete no action on update no action
) engine=innodb default charset=latin1;


--
-- table structure for table `refclassroommemberstatus`
--


create table `refclassroommemberstatus` (
  `refclassroommemberstatusid` int(10) unsigned not null,
  `classroommemberstatusname` varchar(20) not null,
  `description` varchar(50) not null,
  primary key (`refclassroommemberstatusid`),
  unique key `classroommemberstatusname_unique` (`classroommemberstatusname`)
) engine=innodb default charset=latin1;

--
-- table structure for table `classroommember`
--


create table `classroommember` (
  `classroommemberid` int(10) unsigned not null auto_increment,
  `appuserid` int(10) unsigned not null,
  `classroomid` int(10) unsigned not null,
  `status` int(10) unsigned not null,
  `membershipstartdate` datetime not null,
  `membershipexpirartiondate` datetime not null,
  `membershiprequestdate` datetime not null,
  `comments` varchar(150) not null,
  primary key (`classroommemberid`),
  unique key `classroommmebrukey` (`classroomid`,`appuserid`),
  key `classroommember_fkindex1` (`classroomid`),
  key `classroommember_fkindex2` (`appuserid`),
  key `classroommember_fk2` (`classroomid`),
  key `classroommember_fk3` (`status`),
  constraint `classroommember_fk2` foreign key (`classroomid`) references `classroom` (`classroomid`) on delete no action on update no action,
  constraint `classroommember_fk3` foreign key (`status`) references `refclassroommemberstatus` (`refclassroommemberstatusid`) on delete no action on update no action,
  constraint `classroommember_ibfk_2` foreign key (`appuserid`) references `appuser` (`appuserid`) on delete no action on update no action
) engine=innodb auto_increment=15 default charset=latin1;


--
-- table structure for table `postvote`
--


create table `postvote` (
  `postvoteid` int(10) unsigned not null auto_increment,
  `votedbyuserid` int(10) unsigned not null,
  `upordown` tinyint(4) not null,
  `votedate` datetime not null,
  `postid` int(10) unsigned default null,
  `posttype` tinyint(4) default null,
  primary key (`postvoteid`),
  key `questionfk_idx` (`postid`),
  key `appuserfk_idx` (`votedbyuserid`),
  constraint `questionfk` foreign key (`postid`) references `post` (`postid`) on delete no action on update no action
) engine=innodb auto_increment=39 default charset=latin1;



--
-- table structure for table `questionclassroom`
--


create table `questionclassroom` (
  `questionclassroomid` int(10) unsigned not null auto_increment,
  `classroomid` int(10) unsigned not null,
  `questionid` int(10) unsigned not null,
  primary key (`questionclassroomid`),
  key `questionclassroom_fkindex1` (`questionid`),
  key `questionclassroom_fkindex2` (`classroomid`),
  constraint `questionclassroom_ibfk_1` foreign key (`questionid`) references `question` (`questionid`) on delete no action on update no action,
  constraint `questionclassroom_ibfk_2` foreign key (`classroomid`) references `classroom` (`classroomid`) on delete no action on update no action
) engine=innodb default charset=latin1;



--
-- table structure for table `questionrating`
--


create table `questionrating` (
  `questionratingid` int(10) not null,
  `questionlevelid` int(10) unsigned not null,
  `ratedbyuserid` int(10) unsigned not null,
  primary key (`questionratingid`),
  key `questionlevel_fk_idx` (`questionlevelid`),
  key `appuser+fk_idx` (`ratedbyuserid`),
  constraint `appuser+fk` foreign key (`ratedbyuserid`) references `appuser` (`appuserid`) on delete no action on update no action,
  constraint `questionlevel_fk` foreign key (`questionlevelid`) references `refquestionlevel` (`questionlevelid`) on delete no action on update no action
) engine=innodb default charset=latin1;

--
-- table structure for table `questionratingsummary`
--


create table `questionratingsummary` (
  `questionratingsummaryid` int(10) unsigned not null,
  `noofeasyratings` int(11) default null,
  `noofmediumratings` int(11) default null,
  `noofhardratings` int(11) default null,
  `questionid` int(10) unsigned not null,
  primary key (`questionratingsummaryid`),
  key `question_fk_idx` (`questionid`),
  constraint `question_fk` foreign key (`questionid`) references `question` (`questionid`) on delete no action on update no action
) engine=innodb default charset=latin1;


--
-- table structure for table `refquestionstatus`
--


create table `refquestionstatus` (
  `refquestionstatusid` int(10) unsigned not null,
  `questionstatusname` varchar(20) not null,
  primary key (`refquestionstatusid`)
) engine=innodb default charset=latin1;


--
-- table structure for table `tag`
--


create table `tag` (
  `tagid` int(10) unsigned not null auto_increment,
  `tagname` varchar(20) not null,
  `tagdescription` varchar(255) not null,
  primary key (`tagid`)
) engine=innodb auto_increment=18 default charset=latin1;



--
-- table structure for table `questiontag`
--


create table `questiontag` (
  `questiontagid` int(10) unsigned not null auto_increment,
  `questionid` int(10) unsigned not null,
  `tagid` int(10) unsigned not null,
  primary key (`questiontagid`),
  unique key `questiontaguniquekey` (`questionid`,`tagid`),
  key `questiontag_fkindex1` (`tagid`),
  key `questiontag_fkindex2` (`questionid`),
  constraint `questiontag_ibfk_1` foreign key (`tagid`) references `tag` (`tagid`) on delete no action on update no action,
  constraint `questiontag_ibfk_2` foreign key (`questionid`) references `question` (`questionid`) on delete no action on update no action
) engine=innodb auto_increment=115 default charset=latin1;





