USE `learnqa`;
--
-- Dumping data for table `UserTypeMaster`
--

INSERT INTO `UserTypeMaster` VALUES (1,'application user'),(2,'administator');



--
-- Dumping data for table `questionlevel`
--


INSERT INTO `questionlevel` VALUES (1,'Medium',1),(2,'Hard',3),(3,'Easy',1);


--
-- Dumping data for table `refclassroommemberstatus`
--


INSERT INTO `refclassroommemberstatus` VALUES (1,'Pending Approval','applied for membership'),(2,'Active','Request to join is approved'),(3,'Expired','Request to join is approved'),(4,'Rejected','Rejected');


--
-- Dumping data for table `refquestionstatus`
--


INSERT INTO `refquestionstatus` VALUES (1,'new');


--
-- Dumping data for table `refsubject`
--

INSERT INTO `refsubject` VALUES (1,'mathematics','mathematics');



--
-- Dumping data for table `appuser`
--


INSERT INTO `appuser` VALUES (2,NULL,'anigam@expedia.com','pass','Anand','K','nigam',1),(1,NULL,'admin@learnqa.com','admin','Admin',NULL,'Admin',2);




