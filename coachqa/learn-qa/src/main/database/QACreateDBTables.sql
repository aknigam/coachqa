

CREATE TABLE RefClassroomMemberStatus (
  RefClassroomMemberStatusId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ClassroommemberStatusName VARCHAR(20) NOT NULL,
  Description VARCHAR(50) NULL,
  PRIMARY KEY(RefClassroomMemberStatusId)
);

CREATE TABLE RefSubject (
  RefSubjectId INTEGER UNSIGNED NOT NULL,
  SubjectName VARCHAR(20) NOT NULL,
  SubjectDescription VARCHAR(20) NOT NULL,
  PRIMARY KEY(RefSubjectId)
);

CREATE TABLE RefQuestionStatus (
  RefQuestionStatusId INTEGER UNSIGNED NOT NULL,
  QuestionStatusName VARCHAR(20) NOT NULL,
  PRIMARY KEY(RefQuestionStatusId)
);

CREATE TABLE QuestionLevel (
  QuestionLevelId INTEGER UNSIGNED NOT NULL,
  LevelName VARCHAR(20) NOT NULL,
  LevelOrder INTEGER UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY(QuestionLevelId)
);

CREATE TABLE Tag (
  TagId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  TagName VARCHAR(20) NOT NULL,
  PRIMARY KEY(TagId)
);

CREATE TABLE UserReputation (
  UserReputationId INTEGER UNSIGNED NOT NULL,
  ReputationName VARCHAR(20) NOT NULL,
  PRIMARY KEY(UserReputationId)
);

CREATE TABLE AppUser (
  AppUserId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  UserReputationId INTEGER UNSIGNED NULL,
  Email VARCHAR(20) NOT NULL,
  Pasword VARCHAR(20) NOT NULL,
  FirstName VARCHAR(20) NOT NULL,
  MiddleName VARCHAR(20) NULL,
  LastName VARCHAR(20) NOT NULL,
  PRIMARY KEY(AppUserId),
  INDEX AppUser_FKIndex1(UserReputationId),
  FOREIGN KEY(UserReputationId)
    REFERENCES UserReputation(UserReputationId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Classroom (
  ClassroomId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  MinReputationToJoinId INTEGER UNSIGNED NOT NULL,
  ClassOwner INTEGER UNSIGNED NOT NULL,
  ClassName VARCHAR(20) NOT NULL,
  IsPublic BOOL NOT NULL,
  PRIMARY KEY(ClassroomId),
  INDEX Classroom_FKIndex1(ClassOwner),
  INDEX Classroom_FKIndex2(MinReputationToJoinId),
  FOREIGN KEY(ClassOwner)
    REFERENCES AppUser(appUserId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(MinReputationToJoinId)
    REFERENCES UserReputation(UserReputationId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE ClassroomMember (
  ClassroomMemberId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AppUserId INTEGER UNSIGNED NOT NULL,
  ClassroomId INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(ClassroomMemberId),
  INDEX ClassroomMember_FKIndex1(ClassroomId),
  INDEX ClassroomMember_FKIndex2(AppUserId),
  FOREIGN KEY(ClassroomId)
    REFERENCES Classroom(ClassroomId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(AppUserId)
    REFERENCES AppUser(appUserId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Question (
  questionId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  RefSubjectId INTEGER UNSIGNED NOT NULL,
  QuestionLevelId INTEGER UNSIGNED NOT NULL,
  PostedBy INTEGER UNSIGNED NOT NULL,
  RefQuestionStatusId INTEGER UNSIGNED NOT NULL,
  Title VARCHAR(50) NOT NULL,
  Content LONGTEXT NOT NULL,
  NoOfViews INTEGER UNSIGNED NULL,
  PostDate DATETIME NOT NULL,
  LastActiveDate DATETIME NOT NULL,
  Votes INTEGER UNSIGNED NULL DEFAULT 0,
  IsPublic BOOL NOT NULL,
  PRIMARY KEY(questionId),
  INDEX Question_FKIndex1(RefQuestionStatusId),
  INDEX Question_FKIndex2(PostedBy),
  INDEX Question_FKIndex3(QuestionLevelId),
  INDEX Question_FKIndex4(RefSubjectId),
  FOREIGN KEY(RefQuestionStatusId)
    REFERENCES RefQuestionStatus(RefQuestionStatusId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(PostedBy)
    REFERENCES AppUser(appUserId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(QuestionLevelId)
    REFERENCES QuestionLevel(QuestionLevelId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(RefSubjectId)
    REFERENCES RefSubject(RefSubjectId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE QuestionTag (
  QuestionTagId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  QuestionId INTEGER UNSIGNED NOT NULL,
  TagId INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(QuestionTagId),
  INDEX QuestionTag_FKIndex1(TagId),
  INDEX QuestionTag_FKIndex2(QuestionId),
  FOREIGN KEY(TagId)
    REFERENCES Tag(TagId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(QuestionId)
    REFERENCES Question(questionId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE Answer (
  AnswerId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AnsweredByUserId INTEGER UNSIGNED NOT NULL,
  QuestionId INTEGER UNSIGNED NOT NULL,
  Votes INTEGER UNSIGNED NULL,
  Content LONGTEXT NOT NULL,
  PRIMARY KEY(AnswerId),
  INDEX Answer_FKIndex1(QuestionId),
  INDEX Answer_FKIndex2(AnsweredByUserId),
  FOREIGN KEY(QuestionId)
    REFERENCES Question(questionId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(AnsweredByUserId)
    REFERENCES AppUser(appUserId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE QuestionClassroom (
  QuestionClassroomId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  ClassroomId INTEGER UNSIGNED NOT NULL,
  questionId INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(QuestionClassroomId),
  INDEX QuestionClassroom_FKIndex1(questionId),
  INDEX QuestionClassroom_FKIndex2(ClassroomId),
  FOREIGN KEY(questionId)
    REFERENCES Question(questionId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(ClassroomId)
    REFERENCES Classroom(ClassroomId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE AnswerComment (
  AnswerCommentId INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  AnswerId INTEGER UNSIGNED NOT NULL,
  AnswerComment LONGTEXT NOT NULL,
  PRIMARY KEY(AnswerCommentId),
  INDEX AnswerComment_FKIndex1(AnswerId),
  FOREIGN KEY(AnswerId)
    REFERENCES Answer(answerId)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

