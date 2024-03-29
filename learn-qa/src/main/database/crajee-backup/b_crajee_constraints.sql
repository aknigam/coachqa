use crajee_db;


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
CREATE INDEX classroomsubject_refsubject_RefSubjectId_fk ON classroomsubject (subjectId);

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



