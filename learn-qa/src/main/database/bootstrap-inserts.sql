INSERT INTO account (accountName, accountDescription) VALUES ('internal_account', 'Internal account to which application admin can be assigned');

INSERT INTO appuser (userreputationid, email, pasword, firstname, middlename, lastname, usertypeid, accountId) VALUES (null, 'admin@crajee.com', 'pass', 'crajee', '', 'admin', 1, 100);

INSERT INTO question_type (id, name, description) VALUES (1, 'single choice', 'single choice question');
INSERT INTO question_type (id, name, description) VALUES (2, 'multiple choice', 'multiple choice');
INSERT INTO question_type (id, name, description) VALUES (3, 'subjective', 'subjective');

INSERT INTO refclassroommemberstatus (refclassroommemberstatusid, classroommemberstatusname, description) VALUES (1, 'Pending Approval', 'Applied for membership');
INSERT INTO refclassroommemberstatus (refclassroommemberstatusid, classroommemberstatusname, description) VALUES (2, 'Active', 'request to join is approved');
INSERT INTO refclassroommemberstatus (refclassroommemberstatusid, classroommemberstatusname, description) VALUES (3, 'Expired', 'request to join is approved');
INSERT INTO refclassroommemberstatus (refclassroommemberstatusid, classroommemberstatusname, description) VALUES (4, 'Rejected', 'rejected');

INSERT INTO refquestionlevel (questionlevelid, levelname, levelorder) VALUES (1, 'medium', 1);
INSERT INTO refquestionlevel (questionlevelid, levelname, levelorder) VALUES (2, 'hard', 3);
INSERT INTO refquestionlevel (questionlevelid, levelname, levelorder) VALUES (3, 'easy', 1);

INSERT INTO refquestionstatus (refquestionstatusid, questionstatusname) VALUES (1, 'new');

INSERT INTO refsubject (refsubjectid, subjectname, subjectdescription) VALUES (1, 'Mathematics', 'mathematics');
INSERT INTO refsubject (refsubjectid, subjectname, subjectdescription) VALUES (2, 'Chemistry', 'chemistry');
INSERT INTO refsubject (refsubjectid, subjectname, subjectdescription) VALUES (3, 'Physics', 'physics');

INSERT INTO refusertype (usertypeid, usertype, description) VALUES (1, 'application_admin', 'User who creates new accounts');
INSERT INTO refusertype (usertypeid, usertype, description) VALUES (2, 'account_admin', 'User who creates other users and classrooms in an account');
INSERT INTO refusertype (usertypeid, usertype, description) VALUES (3, 'classroom_admin', 'User who is the admin of the classroom');
INSERT INTO refusertype (usertypeid, usertype, description) VALUES (4, 'application_user', 'User who usese the application');

INSERT INTO tag (tagname, tagdescription) VALUES ('javascript3', 'This is an OO programming language');
INSERT INTO tag (tagname, tagdescription) VALUES ('java', 'This is an OO programming language');

