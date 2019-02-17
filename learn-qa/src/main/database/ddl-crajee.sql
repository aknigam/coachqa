CREATE TABLE answer
(
    answerid INT(10) unsigned PRIMARY KEY NOT NULL,
    questionid INT(10) unsigned NOT NULL
);
CREATE TABLE answercomment
(
    answercommentid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    answerid INT(10) unsigned NOT NULL,
    answercomment LONGTEXT NOT NULL
);
CREATE TABLE appuser
(
    appuserid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userreputationid INT(10) unsigned,
    email VARCHAR(20) NOT NULL,
    pasword VARCHAR(20) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    middlename VARCHAR(20),
    lastname VARCHAR(20) NOT NULL,
    usertypeid TINYINT(4) DEFAULT '1'
);
CREATE TABLE appuserdetail
(
    appuserdetailid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    appuserid INT(10) unsigned NOT NULL,
    androidtoken VARCHAR(4000)
);
CREATE TABLE classroom
(
    classroomid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    minreputationtojoinid INT(10) unsigned,
    classowner INT(10) unsigned NOT NULL,
    classname VARCHAR(50) NOT NULL,
    ispublic TINYINT(1) NOT NULL,
    lastupdatedate DATETIME NOT NULL,
    description VARCHAR(200) NOT NULL,
    subjectId INT(10) unsigned NOT NULL
);
CREATE TABLE classroommember
(
    classroommemberid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    appuserid INT(10) unsigned NOT NULL,
    classroomid INT(10) unsigned NOT NULL,
    status INT(10) unsigned NOT NULL,
    membershipstartdate DATETIME NOT NULL,
    membershipexpirartiondate DATETIME NOT NULL,
    membershiprequestdate DATETIME NOT NULL,
    comments VARCHAR(150) NOT NULL
);
CREATE TABLE favoritepost
(
    id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userid INT(10) unsigned NOT NULL,
    questionid INT(10) unsigned NOT NULL
);
CREATE TABLE post
(
    postid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    posttype TINYINT(4) NOT NULL,
    noofviews INT(10) DEFAULT '0' NOT NULL,
    postedby INT(10) unsigned NOT NULL,
    postdate DATETIME NOT NULL,
    votes INT(10) DEFAULT '0' NOT NULL,
    content TEXT NOT NULL,
    approvalstatus TINYINT(1) DEFAULT '1' NOT NULL,
    approvalcomment VARCHAR(250),
    classroomid INT(10) unsigned
);
CREATE TABLE postmedia
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    imagecontent LONGBLOB NOT NULL
);
CREATE TABLE postvote
(
    postvoteid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    votedbyuserid INT(10) unsigned NOT NULL,
    upordown TINYINT(4) NOT NULL,
    votedate DATETIME NOT NULL,
    postid INT(10) unsigned,
    posttype TINYINT(4)
);
CREATE TABLE question
(
    questionid INT(10) unsigned PRIMARY KEY NOT NULL,
    refsubjectid INT(10) unsigned NOT NULL,
    questionlevelid INT(10) unsigned NOT NULL,
    refquestionstatusid INT(10) unsigned NOT NULL,
    title VARCHAR(50) NOT NULL,
    lastactivedate DATETIME NOT NULL,
    ispublic TINYINT(1) NOT NULL,
    classroomId INT(10) unsigned
);
CREATE TABLE questionclassroom
(
    questionclassroomid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    classroomid INT(10) unsigned NOT NULL,
    questionid INT(10) unsigned NOT NULL
);
CREATE TABLE questionrating
(
    questionratingid INT(10) PRIMARY KEY NOT NULL,
    questionlevelid INT(10) unsigned NOT NULL,
    ratedbyuserid INT(10) unsigned NOT NULL
);
CREATE TABLE questionratingsummary
(
    questionratingsummaryid INT(10) unsigned PRIMARY KEY NOT NULL,
    noofeasyratings INT(11),
    noofmediumratings INT(11),
    noofhardratings INT(11),
    questionid INT(10) unsigned NOT NULL
);
CREATE TABLE questiontag
(
    questiontagid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    questionid INT(10) unsigned NOT NULL,
    tagid INT(10) unsigned NOT NULL
);
CREATE TABLE refclassroommemberstatus
(
    refclassroommemberstatusid INT(10) unsigned PRIMARY KEY NOT NULL,
    classroommemberstatusname VARCHAR(20) NOT NULL,
    description VARCHAR(50) NOT NULL
);
CREATE TABLE refquestionlevel
(
    questionlevelid INT(10) unsigned PRIMARY KEY NOT NULL,
    levelname VARCHAR(20) NOT NULL,
    levelorder INT(10) unsigned DEFAULT '1' NOT NULL
);
CREATE TABLE refquestionstatus
(
    refquestionstatusid INT(10) unsigned PRIMARY KEY NOT NULL,
    questionstatusname VARCHAR(20) NOT NULL
);
CREATE TABLE refsubject
(
    refsubjectid INT(10) unsigned PRIMARY KEY NOT NULL,
    subjectname VARCHAR(20) NOT NULL,
    subjectdescription VARCHAR(20) NOT NULL
);
CREATE TABLE refusertype
(
    usertypeid TINYINT(4) PRIMARY KEY NOT NULL,
    usertype VARCHAR(20)
);
CREATE TABLE tag
(
    tagid INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tagname VARCHAR(20) NOT NULL,
    tagdescription VARCHAR(255) NOT NULL
);
CREATE TABLE userreputation
(
    userreputationid INT(10) unsigned PRIMARY KEY NOT NULL,
    reputationname VARCHAR(20) NOT NULL
);
CREATE TABLE classroomsubject
(
    id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    classroomId INT(10) unsigned NOT NULL,
    subjectId INT(10) unsigned NOT NULL
);
CREATE TABLE account
(
    accountId INT(10) unsigned PRIMARY KEY NOT NULL,
    accountName VARCHAR(50) NOT NULL,
    accountDescription TEXT
);
ALTER TABLE answer ADD FOREIGN KEY (answerid) REFERENCES post (postid);
ALTER TABLE answer ADD FOREIGN KEY (questionid) REFERENCES question (questionid);
CREATE INDEX answerpost_fk ON answer (answerid);
CREATE INDEX answer_fkindex1 ON answer (questionid);
ALTER TABLE answercomment ADD FOREIGN KEY (answerid) REFERENCES answer (answerid);
CREATE INDEX answercomment_fkindex1 ON answercomment (answerid);
ALTER TABLE appuser ADD FOREIGN KEY (userreputationid) REFERENCES userreputation (userreputationid);
ALTER TABLE appuser ADD FOREIGN KEY (usertypeid) REFERENCES refusertype (usertypeid);
CREATE INDEX appusertype___fk ON appuser (usertypeid);
CREATE INDEX appuser_fkindex1 ON appuser (userreputationid);
CREATE UNIQUE INDEX email_unique ON appuser (email);
ALTER TABLE appuserdetail ADD FOREIGN KEY (appuserid) REFERENCES appuser (appuserid);
CREATE INDEX appuserdetail_appuser_appuserid_fk ON appuserdetail (appuserid);
ALTER TABLE classroom ADD FOREIGN KEY (minreputationtojoinid) REFERENCES userreputation (userreputationid);
ALTER TABLE classroom ADD FOREIGN KEY (classowner) REFERENCES appuser (appuserid);
ALTER TABLE classroom ADD FOREIGN KEY (subjectId) REFERENCES refsubject (refsubjectid);
CREATE UNIQUE INDEX classname_unique ON classroom (classname);
CREATE INDEX classroom_fkindex1 ON classroom (classowner);
CREATE INDEX classroom_fkindex2 ON classroom (minreputationtojoinid);
CREATE INDEX classroom_refsubject_RefSubjectId_fk ON classroom (subjectId);
ALTER TABLE classroommember ADD FOREIGN KEY (appuserid) REFERENCES appuser (appuserid);
ALTER TABLE classroommember ADD FOREIGN KEY (classroomid) REFERENCES classroom (classroomid);
ALTER TABLE classroommember ADD FOREIGN KEY (status) REFERENCES refclassroommemberstatus (refclassroommemberstatusid);
CREATE INDEX classroommember_fk2 ON classroommember (classroomid);
CREATE INDEX classroommember_fk3 ON classroommember (status);
CREATE INDEX classroommember_fkindex1 ON classroommember (classroomid);
CREATE INDEX classroommember_fkindex2 ON classroommember (appuserid);
CREATE UNIQUE INDEX classroommmebrukey ON classroommember (classroomid, appuserid);
ALTER TABLE favoritepost ADD FOREIGN KEY (userid) REFERENCES appuser (appuserid);
ALTER TABLE favoritepost ADD FOREIGN KEY (questionid) REFERENCES question (questionid);
CREATE INDEX favoritepost_appuser_appuserid_fk ON favoritepost (userid);
CREATE INDEX favoritepost_question_questionid_fk ON favoritepost (questionid);
ALTER TABLE post ADD FOREIGN KEY (postedby) REFERENCES appuser (appuserid);
ALTER TABLE post ADD FOREIGN KEY (classroomid) REFERENCES classroom (classroomid);
CREATE INDEX fk_post_user ON post (postedby);
CREATE INDEX postclassroom___fk ON post (classroomid);
ALTER TABLE postvote ADD FOREIGN KEY (postid) REFERENCES post (postid);
CREATE INDEX appuserfk_idx ON postvote (votedbyuserid);
CREATE INDEX questionfk_idx ON postvote (postid);
ALTER TABLE question ADD FOREIGN KEY (questionid) REFERENCES post (postid);
ALTER TABLE question ADD FOREIGN KEY (refsubjectid) REFERENCES refsubject (refsubjectid);
ALTER TABLE question ADD FOREIGN KEY (questionlevelid) REFERENCES refquestionlevel (questionlevelid);
CREATE INDEX questionpost_fk ON question (questionid);
CREATE INDEX question_fkindex1 ON question (refquestionstatusid);
CREATE INDEX question_fkindex3 ON question (questionlevelid);
CREATE INDEX question_fkindex4 ON question (refsubjectid);
CREATE INDEX question___fk_2 ON question (classroomId);
CREATE UNIQUE INDEX title_unique ON question (title);
ALTER TABLE questionclassroom ADD FOREIGN KEY (classroomid) REFERENCES classroom (classroomid);
ALTER TABLE questionclassroom ADD FOREIGN KEY (questionid) REFERENCES question (questionid);
CREATE INDEX questionclassroom_fkindex1 ON questionclassroom (questionid);
CREATE INDEX questionclassroom_fkindex2 ON questionclassroom (classroomid);
ALTER TABLE questionrating ADD FOREIGN KEY (questionlevelid) REFERENCES refquestionlevel (questionlevelid);
ALTER TABLE questionrating ADD FOREIGN KEY (ratedbyuserid) REFERENCES appuser (appuserid);
CREATE INDEX `appuser+fk_idx` ON questionrating (ratedbyuserid);
CREATE INDEX questionlevel_fk_idx ON questionrating (questionlevelid);
ALTER TABLE questionratingsummary ADD FOREIGN KEY (questionid) REFERENCES question (questionid);
CREATE INDEX question_fk_idx ON questionratingsummary (questionid);
ALTER TABLE questiontag ADD FOREIGN KEY (questionid) REFERENCES question (questionid);
ALTER TABLE questiontag ADD FOREIGN KEY (tagid) REFERENCES tag (tagid);
CREATE UNIQUE INDEX questiontaguniquekey ON questiontag (questionid, tagid);
CREATE INDEX questiontag_fkindex1 ON questiontag (tagid);
CREATE INDEX questiontag_fkindex2 ON questiontag (questionid);
CREATE UNIQUE INDEX classroommemberstatusname_unique ON refclassroommemberstatus (classroommemberstatusname);
ALTER TABLE classroomsubject ADD FOREIGN KEY (classroomId) REFERENCES classroom (classroomid);
ALTER TABLE classroomsubject ADD FOREIGN KEY (subjectId) REFERENCES refsubject (refsubjectid);
CREATE INDEX classroomsubject_classroom_ClassroomId_fk ON classroomsubject (classroomId);
CREATE INDEX classroomsubject_refsubject_RefSubjectId_fk ON classroomsubject (subjectId)CREATE PROCEDURE answeraddsproc(pquestionid INT, pansweredbyuserid INT, pcontent TEXT);
CREATE PROCEDURE classroomgetbyid(pclassroomid INT);
CREATE PROCEDURE classroomjoin(puserid INT, pclassroomid INT);
CREATE PROCEDURE joinclassroomsproc(puserid INT, pclassroomid INT);
CREATE PROCEDURE questionaddsproc(ppostedby INT, ptitle VARCHAR, pcontent TEXT, prefsubjectid INT, pclassroomid INT, pispublic TINYINT);
CREATE PROCEDURE questionget(pquestionid INT);
CREATE PROCEDURE questionupdatestats(pquestionid INT, pvotes INT);
CREATE PROCEDURE useraddsproc(pemail VARCHAR, ppasword VARCHAR, pfirstname VARCHAR, pmiddlename VARCHAR, plastname VARCHAR);
CREATE PROCEDURE userget(pappuserid INT, pemail VARCHAR);
CREATE PROCEDURE userupdate(pappuserid INT, puserreputationid INT, pemail VARCHAR);