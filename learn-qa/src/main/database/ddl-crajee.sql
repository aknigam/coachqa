CREATE TABLE account
(
    accountId INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    accountName VARCHAR(50) NOT NULL,
    accountDescription TEXT
);
ALTER TABLE account AUTO_INCREMENT = 100;

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
    usertypeid TINYINT(4) DEFAULT '1',
    accountId INT(10) unsigned NOT NULL
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
    subjectId INT(10) unsigned NOT NULL,
    accountId INT(10) unsigned NOT NULL
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
CREATE TABLE classroomsubject
(
    id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    classroomId INT(10) unsigned NOT NULL,
    subjectId INT(10) unsigned NOT NULL
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
    classroomid INT(10) unsigned,
    accountId INT(10) unsigned NOT NULL
);
CREATE TABLE postmedia
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    imagecontent LONGBLOB NOT NULL,
    accountId INT(10) unsigned NOT NULL
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
    title VARCHAR(50),
    lastactivedate DATETIME NOT NULL,
    classroomId INT(10) unsigned,
    question_type_id INT(10) unsigned
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
    usertype VARCHAR(20),
    description VARCHAR(255)
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
CREATE TABLE account_preference
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    postsNeedApproval TINYINT(4) DEFAULT '1' COMMENT 'by default posts need approval',
    accountId INT(10) unsigned NOT NULL
);
CREATE TABLE questionnaire
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    start_time DATETIME,
    end_time DATETIME,
    visibilityId INT(10) unsigned,
    statusId INT(10) unsigned,
    instructions TEXT
);
CREATE TABLE questionnaire_question
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    questionId INT(10) unsigned NOT NULL,
    questionnaireId INT(10) unsigned NOT NULL
);
CREATE TABLE ref_questionnaire_visibility
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE ref_questionnaire_status
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE question_type
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(250)
);
CREATE TABLE question_choice
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    choice TEXT NOT NULL,
    questionId INT(10) unsigned NOT NULL
);
CREATE TABLE answer_mc
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    answerId INT(10) unsigned NOT NULL,
    question_choice_id INT(10) unsigned NOT NULL
);
CREATE TABLE role
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE role_privilege
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    roleId INT(10) unsigned NOT NULL,
    privilegeId INT(10) unsigned NOT NULL
);
CREATE TABLE post_extracted_text
(
    id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    postId INT(10) unsigned NOT NULL,
    extracted_text TEXT NOT NULL
);
CREATE TABLE post_ocr_text
(
    id INT(10) unsigned PRIMARY KEY NOT NULL,
    extracted_text LONGTEXT NOT NULL,
    postId INT(10) unsigned NOT NULL
);
CREATE TABLE contact
(
    id INT(10) unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL
);
