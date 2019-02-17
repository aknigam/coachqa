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
