-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: crajee_db
-- ------------------------------------------------------
-- Server version	5.7.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (100,'demo','Account used for demo purposes'),(101,'test','account to be used for test automation'),(102,'test01','Test automation account'),(103,'internal_account','Internal account to which application admin can be assigned');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `account_preference`
--

LOCK TABLES `account_preference` WRITE;
/*!40000 ALTER TABLE `account_preference` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (9,4),(15,14),(19,18),(21,18),(24,18),(36,33),(37,33),(51,38),(49,48);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `answer_mc`
--

LOCK TABLES `answer_mc` WRITE;
/*!40000 ALTER TABLE `answer_mc` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_mc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `answercomment`
--

LOCK TABLES `answercomment` WRITE;
/*!40000 ALTER TABLE `answercomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `answercomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (1,NULL,'admin@learnqa.com','pass','Admin',NULL,'admin',2,100,NULL),(2,NULL,'abhi@crajee.com','pass','Anand','Kumar','Nigam',4,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(3,NULL,'motoe@crajee.com','pass','Cvent','Kumar','Nigam',4,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(4,NULL,'anigam@com','pass','Priya','Kumar','Nigam',4,100,NULL),(5,NULL,'varun@com','pass','Varun',NULL,'Kubba',4,100,NULL),(6,NULL,'a@c.com','pass','F','F','F',4,100,NULL),(7,NULL,'anupam@com','pass','Anupam','','Sharma',4,100,NULL),(8,NULL,'varuan@com','pass','Varun',NULL,'Kubba',4,100,NULL),(9,NULL,'varuBn@com','pass','Viraj',NULL,'Kubba',4,100,NULL),(10,NULL,'priya@com','pass','Priya','','Nigam',4,100,NULL),(11,NULL,'a.nigam','pass','Nitish','Kumar','Nigam',4,100,NULL),(12,NULL,'admin@crajee.com','pass','crajee','','admin',1,103,NULL),(13,NULL,'varu@crajee.com','pass','Varun',NULL,'Kubba',4,100,NULL),(14,NULL,'ridhi@crajee.com','pass','Ridhi',NULL,'Handa',4,100,NULL),(17,NULL,'anigam@crajee.com','pass','Hiteshi',NULL,'Jain',2,100,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(18,NULL,'Davinder@crajee.com','pass','Davinder',NULL,'Singh',2,100,NULL),(19,NULL,'aknigam@outlook.com','pass','Anand','Kumar','Nigam',4,101,NULL),(20,NULL,'motog5s@crajee.com','pass','Priya',NULL,'Nigam',3,100,'f1ZopkTtXas:APA91bFZVD3HVC9UHWCfwnp6IbOUQRdTM0TOKjuRy-6yoDDqJW9t1N1bncjRmvnp4qVGbH1Ncf32eOfdC6hImCIeFPgDI8pbQXs3PFnSCTmOgjBdiV_yyuYSUhK9IsRGvhq68ik0LQga'),(21,NULL,'anand@crajee.com','pass','Anand','','Nigam',4,100,'androidToken');
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `appuserdetail`
--

LOCK TABLES `appuserdetail` WRITE;
/*!40000 ALTER TABLE `appuserdetail` DISABLE KEYS */;
INSERT INTO `appuserdetail` VALUES (1,2,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(2,3,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(3,17,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(4,20,'f1ZopkTtXas:APA91bFZVD3HVC9UHWCfwnp6IbOUQRdTM0TOKjuRy-6yoDDqJW9t1N1bncjRmvnp4qVGbH1Ncf32eOfdC6hImCIeFPgDI8pbQXs3PFnSCTmOgjBdiV_yyuYSUhK9IsRGvhq68ik0LQga'),(5,21,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(8,1,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(9,4,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(10,5,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(11,6,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(12,7,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(13,8,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(14,9,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(15,10,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(16,11,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(17,12,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(18,13,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(19,14,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(20,18,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr'),(21,19,'ea3MHjI0W3E:APA91bH1LL8R1tG16JtkK4j3Up1CUAdvVhfdBfJC38zvdeGrLrsQ3bzFoVrgqlENRKM88f41VkM7pE5fMaUtCDn8BVHkDA_jmc74BOoO5tJHhKbkKGSnF3_J2nXWZNWfhFjzpT2nVDHr');
/*!40000 ALTER TABLE `appuserdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,NULL,20,'Chemistry classes',1,'2018-04-08 00:00:00','Chemistry classes',1,100),(2,NULL,20,'Physiscs 101 classes',0,'2019-03-06 00:00:00','Physiscs 101 classes',3,100),(3,NULL,20,'102 - Mathematics classes',0,'2019-05-02 00:00:00','102 - Mathematics 101 classes',1,100);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classroommember`
--

LOCK TABLES `classroommember` WRITE;
/*!40000 ALTER TABLE `classroommember` DISABLE KEYS */;
INSERT INTO `classroommember` VALUES (1,2,1,2,'2018-11-23 00:00:00','2038-11-23 00:00:00','2018-09-05 00:00:00','Appoved'),(2,3,1,2,'2019-03-03 00:00:00','2020-03-03 00:00:00','2019-03-03 00:00:00','Please approve my add request'),(3,2,2,2,'2019-03-10 00:00:00','2020-03-10 00:00:00','2019-03-10 00:00:00','Please approve my add request'),(4,6,1,2,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request'),(6,6,2,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request'),(7,11,1,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request'),(8,11,2,1,'2019-05-01 00:00:00','2020-05-01 00:00:00','2019-05-01 00:00:00','Please approve my add request'),(9,21,1,1,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request'),(10,21,2,1,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request'),(11,21,3,4,'2019-05-02 00:00:00','2020-05-02 00:00:00','2019-05-02 00:00:00','Please approve my add request'),(20,3,3,4,'2019-05-04 00:00:00','2020-05-04 00:00:00','2019-05-04 00:00:00','Please approve my add request'),(28,2,3,2,'2019-05-05 00:00:00','2020-05-05 00:00:00','2019-05-05 00:00:00','Please approve my add request'),(31,7,3,1,'2019-05-05 00:00:00','2020-05-05 00:00:00','2019-05-05 00:00:00','Please approve my add request');
/*!40000 ALTER TABLE `classroommember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classroomsubject`
--

LOCK TABLES `classroomsubject` WRITE;
/*!40000 ALTER TABLE `classroomsubject` DISABLE KEYS */;
INSERT INTO `classroomsubject` VALUES (1,1,1),(2,1,1),(3,1,2),(4,1,3);
/*!40000 ALTER TABLE `classroomsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'admin@learnqa.com','Admin',NULL,'admin'),(2,'anigam@expedia.com','Anand','Kumar','Nigam'),(3,'a.nigam@cvent.com','Cvent','Kumar','Nigam'),(4,'anigam@com','Anand','Kumar','Nigam'),(5,'varun@com','Varun',NULL,'Kubba'),(6,'a@c.com','F','F','F'),(7,'anupam@com','Anupam','','Sharma'),(8,'varuan@com','Varun',NULL,'Kubba'),(9,'varuBn@com','Varun',NULL,'Kubba'),(10,'priya@com','Priya','','Nigam'),(11,'a.nigam','Anand','Kumar','Nigam'),(16,'admin@crajee.com','crajee','','admin');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favoritepost`
--

LOCK TABLES `favoritepost` WRITE;
/*!40000 ALTER TABLE `favoritepost` DISABLE KEYS */;
INSERT INTO `favoritepost` VALUES (1,2,2),(2,2,12);
/*!40000 ALTER TABLE `favoritepost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,13,2,'2019-02-17 18:02:32',0,'First question content',1,NULL,1,100),(2,1,18,2,'2019-02-20 19:51:45',0,'Feb 20 question {{g-ca06c15a-1f4c-4970-a8ad-e3ceecb5cafd}}',1,NULL,1,100),(3,1,28,2,'2019-02-21 12:49:59',0,'Feb 21 question',1,NULL,1,100),(4,1,38,2,'2019-02-21 17:24:12',0,'Feb 21 ab question content',1,NULL,1,100),(9,0,0,2,'2019-02-23 16:56:18',1,'sample answer',1,NULL,NULL,100),(10,1,6,2,'2019-02-23 16:58:13',0,'23 content',1,NULL,1,100),(11,1,3,2,'2019-02-23 17:19:07',0,'Feb 23 without title',1,NULL,1,100),(12,1,8,2,'2019-02-23 17:47:12',0,' {{g-3f5c370b-3583-4351-a25b-fa869521078d}}',1,NULL,1,100),(13,1,2,2,'2019-02-23 18:46:14',0,' {{g-5d3c500c-3b34-495e-ae84-5f78488effd9}}',1,NULL,1,100),(14,1,16,2,'2019-02-23 19:13:21',0,'image question\n {{g-5fe4471a-bb5b-409d-b7a0-876689f0d62a}}',1,NULL,1,100),(15,0,0,2,'2019-02-27 14:31:25',1,'Answer to image question',1,NULL,NULL,100),(16,1,6,2,'2019-02-27 17:25:32',0,'irodov question\n {{g-9f89cccd-1ad4-4041-b7a9-46bcb31d4624}}',1,NULL,1,100),(17,1,21,2,'2019-02-27 17:40:29',0,'new edit 1 {{g-3d93583c-b324-463c-a9ab-a93e9e678f11}}',1,NULL,1,100),(18,1,131,2,'2019-02-27 17:45:13',0,'edit 8 {{g-1376f3be-7c87-4aa6-a4c4-2674dd9553ee}}',1,NULL,1,100),(19,0,0,2,'2019-03-01 18:37:36',1,' {{g-941619a8-fd34-43b1-a671-f17a314a9806}}',1,NULL,NULL,100),(20,1,11,2,'2019-03-02 16:39:18',0,'asking ng anew question\n\n {{g-56cd2d2d-6772-48bf-8017-23c9646ba050}}',1,NULL,1,100),(21,0,0,2,'2019-03-03 15:13:43',1,'new answer',1,NULL,NULL,100),(24,0,0,2,'2019-03-03 15:31:05',0,'new answer',1,NULL,NULL,100),(25,1,37,3,'2019-03-03 16:48:04',0,'new question 1',1,NULL,1,100),(26,1,9,3,'2019-03-03 18:20:49',0,'new question',1,NULL,1,100),(27,1,21,3,'2019-03-03 18:45:58',0,'testing approvak',1,NULL,1,100),(28,1,17,2,'2019-03-03 19:19:58',0,'new question on March 3',1,NULL,1,100),(29,1,9,2,'2019-03-09 20:35:59',0,'portrait question {{g-8372c9e3-48d7-4977-b1cd-b7c82acf15d9}}',1,NULL,1,100),(30,1,4,2,'2019-03-09 20:37:27',0,'landscape mode question\n\n {{g-f104fc2e-e9fd-4cc2-8d60-2f5afb3437c2}}',1,NULL,1,100),(31,1,16,2,'2019-03-09 20:42:43',0,' {{g-ef7b7b32-ab50-4482-81b3-e91fb3881e0a}}\n\n\n {{g-4388ff0f-3972-456c-b137-41deb92a52b8}}\n\n {{g-feb4889d-17b1-4541-bae2-2ac55b7fa1e1}}',1,NULL,1,100),(32,1,7,2,'2019-03-09 20:48:12',0,' {{g-aed2b1d9-02d6-4629-a023-a64004f2fd9e}}\n {{g-c034bba5-97b6-4816-b840-733ffb9d1317}}',1,NULL,1,100),(33,1,22,2,'2019-03-10 12:32:52',0,'physics question 1 {{g-6ff69f66-589d-422a-a953-dba457596720}}',1,NULL,1,100),(34,1,8,2,'2019-03-10 13:42:37',0,'new question',0,NULL,2,100),(35,1,22,2,'2019-03-10 13:50:43',0,'new question 2',0,NULL,2,100),(36,0,0,2,'2019-03-10 16:06:10',0,' {{g-f7ff49d4-5e3f-489a-96f2-27cae6c30261}}',1,NULL,NULL,100),(37,0,0,3,'2019-03-10 16:06:59',0,'answer from cvent',0,NULL,NULL,100),(38,1,6,2,'2019-03-10 22:03:28',0,'new question',1,NULL,2,100),(40,1,0,3,'2019-03-17 21:30:51',0,'Posting a question to test notifications',0,NULL,1,100),(42,1,0,3,'2019-03-17 21:33:45',0,'1- Posting a question to test notifications',0,NULL,1,100),(43,1,0,3,'2019-03-17 21:38:20',0,'2- Posting a question to test notifications',0,NULL,1,100),(44,1,0,3,'2019-03-17 21:39:11',0,'3- Posting a question to test notifications',1,NULL,1,100),(45,1,0,3,'2019-03-17 21:43:30',0,'4- Posting a question to test notifications',1,NULL,1,100),(46,1,0,3,'2019-03-17 21:46:44',0,'5- Posting a question to test notifications',1,NULL,1,100),(47,1,2,3,'2019-03-17 21:48:20',0,'6 - Posting a question to test notifications',1,NULL,1,100),(48,1,15,2,'2019-04-22 19:25:42',0,'S3 image question \n{{a-c863be5a-4604-4ce3-bd61-49fa23f49f90}}',1,NULL,1,100),(49,0,0,2,'2019-05-29 16:00:28',0,'lksjsjdf {{a-dc6ff4fc-7c55-4167-b4a3-d205795bd8f0}}',0,NULL,NULL,100),(50,1,6,2,'2019-05-29 16:01:10',0,'new question',0,NULL,1,100),(51,0,0,2,'2020-04-26 15:47:04',0,'anaswre',0,NULL,NULL,100);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post_extracted_text`
--

LOCK TABLES `post_extracted_text` WRITE;
/*!40000 ALTER TABLE `post_extracted_text` DISABLE KEYS */;
INSERT INTO `post_extracted_text` VALUES (1,18,'Q Sea\nmounted is equal to 1\nThe distance between the bearings in which the axle of the disc is\n15 cm. The axle is forced to oscillate about\na horizontal axis with a period T = 1.0 s and amplitude = 20\non the bearings.\nFind the maximum value of the gyroscopic forces exerted by the axle\n1.288. A ship moves with velocity v = 36 km per hour along an\narc of a circle of radius R = 200 m. Find the moment of the gyroscop-\nmoment of inertia relative to\nid forces exerted on the bearings by the shaft with a flywheel whose\nthe rotation axis equals 1-\n-3.8-10 kg.m\' and whose rotation velocity n = 300 rpm. The\nrotation axis is oriented along the length of the ship.\n1.289. A locomotive is propelled by a turbine whose axle is paral-\nlel to the axes of wheels. The turbine\'s rotation direction coincides!\nwith that of wheels. The moment of inertia of the turbine rotor rel-\nstive to its own axis is equal to 240 kg.m. Find the additional\nforce exerted by the gyroscopic forces on the rails when the locomo-\ntive moves along a circle of radius R 250 m with velocity\n-50 km per hour. The gauge is equal to 1-1.5 m. The analar.\nvelocity of the turbine equals n = 1500 rpm.\n1.6. ELASTIC DE FORMATIONS OF A SOLID BODY\n• Relation between tensile (compressive) straine and strena\n=oE,\nWhere\nYoung\'s modulus.\nRelation between lateral compressive (tensile) strain #sad longitud\ne compressive) strain\nwal\n	');
/*!40000 ALTER TABLE `post_extracted_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post_ocr_text`
--

LOCK TABLES `post_ocr_text` WRITE;
/*!40000 ALTER TABLE `post_ocr_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_ocr_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `postmedia`
--

LOCK TABLES `postmedia` WRITE;
/*!40000 ALTER TABLE `postmedia` DISABLE KEYS */;
/*!40000 ALTER TABLE `postmedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `postvote`
--

LOCK TABLES `postvote` WRITE;
/*!40000 ALTER TABLE `postvote` DISABLE KEYS */;
/*!40000 ALTER TABLE `postvote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,1,1,'First question','2019-02-17 18:02:32',1,3),(2,1,1,1,'Feb 20 question','2019-02-20 19:51:45',1,3),(3,1,1,1,'Feb 21 question','2019-02-21 12:49:58',1,3),(4,1,1,1,'Feb 21 a question','2019-02-21 17:24:11',1,3),(10,1,1,1,'Feb 23 question','2019-02-23 16:58:12',1,3),(11,1,1,1,NULL,'2019-02-23 17:19:07',1,3),(12,1,1,1,NULL,'2019-02-23 17:47:11',1,3),(13,1,1,1,NULL,'2019-02-23 18:46:13',1,3),(14,1,1,1,NULL,'2019-02-23 19:13:21',1,3),(16,1,1,1,NULL,'2019-02-27 17:25:32',1,NULL),(17,1,1,1,NULL,'2019-02-27 17:40:28',1,NULL),(18,1,1,1,NULL,'2019-02-27 17:45:12',1,NULL),(20,1,1,1,NULL,'2019-03-02 16:39:18',1,NULL),(25,1,1,1,NULL,'2019-03-03 16:48:04',1,NULL),(26,1,1,1,NULL,'2019-03-03 18:20:49',NULL,NULL),(27,1,1,1,NULL,'2019-03-03 18:45:57',NULL,NULL),(28,1,1,1,NULL,'2019-03-03 19:19:58',NULL,NULL),(29,1,1,1,NULL,'2019-03-09 20:35:59',NULL,NULL),(30,1,1,1,NULL,'2019-03-09 20:37:27',NULL,NULL),(31,1,1,1,NULL,'2019-03-09 20:42:42',NULL,NULL),(32,1,1,1,NULL,'2019-03-09 20:48:11',NULL,NULL),(33,1,1,1,NULL,'2019-03-10 12:32:52',NULL,NULL),(34,1,1,1,NULL,'2019-03-10 13:42:37',NULL,NULL),(35,1,1,1,NULL,'2019-03-10 13:50:43',NULL,NULL),(38,1,1,1,NULL,'2019-03-10 22:03:28',NULL,NULL),(40,1,1,1,'Posting a question','2019-03-17 21:30:55',NULL,NULL),(42,1,1,1,'1- Posting a question','2019-03-17 21:33:52',NULL,NULL),(43,1,1,1,'2 - Posting a question','2019-03-17 21:38:20',NULL,NULL),(44,1,1,1,'3 - Posting a question','2019-03-17 21:39:11',NULL,NULL),(45,1,1,1,'4 - Posting a question','2019-03-17 21:43:30',NULL,NULL),(46,1,1,1,'5 - Posting a question','2019-03-17 21:46:43',NULL,NULL),(47,1,1,1,'6 - Posting a question','2019-03-17 21:48:19',NULL,NULL),(48,1,1,1,NULL,'2019-04-22 19:25:42',NULL,NULL),(50,1,1,1,NULL,'2019-05-29 16:01:10',NULL,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question_choice`
--

LOCK TABLES `question_choice` WRITE;
/*!40000 ALTER TABLE `question_choice` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (1,'single choice','single choice question'),(2,'multiple choice','multiple choice'),(3,'subjective','subjective');
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionclassroom`
--

LOCK TABLES `questionclassroom` WRITE;
/*!40000 ALTER TABLE `questionclassroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionclassroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionnaire`
--

LOCK TABLES `questionnaire` WRITE;
/*!40000 ALTER TABLE `questionnaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionnaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionnaire_question`
--

LOCK TABLES `questionnaire_question` WRITE;
/*!40000 ALTER TABLE `questionnaire_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionnaire_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionrating`
--

LOCK TABLES `questionrating` WRITE;
/*!40000 ALTER TABLE `questionrating` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questionratingsummary`
--

LOCK TABLES `questionratingsummary` WRITE;
/*!40000 ALTER TABLE `questionratingsummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionratingsummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questiontag`
--

LOCK TABLES `questiontag` WRITE;
/*!40000 ALTER TABLE `questiontag` DISABLE KEYS */;
INSERT INTO `questiontag` VALUES (1,1,2),(2,2,2),(3,3,2),(6,4,2),(9,10,2),(10,11,2),(11,12,1),(12,13,2),(13,14,1),(14,16,2),(19,17,2),(27,18,2),(28,20,2),(31,25,2),(30,26,1),(32,27,2),(33,28,2),(34,29,1),(35,30,2),(36,31,1),(37,32,2),(45,33,2),(39,34,2),(44,35,2),(46,38,2),(48,40,2),(49,42,2),(50,43,2),(51,44,2),(52,45,2),(53,46,2),(54,47,2),(55,48,2),(56,50,2);
/*!40000 ALTER TABLE `questiontag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ref_questionnaire_status`
--

LOCK TABLES `ref_questionnaire_status` WRITE;
/*!40000 ALTER TABLE `ref_questionnaire_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `ref_questionnaire_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ref_questionnaire_visibility`
--

LOCK TABLES `ref_questionnaire_visibility` WRITE;
/*!40000 ALTER TABLE `ref_questionnaire_visibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `ref_questionnaire_visibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refclassroommemberstatus`
--

LOCK TABLES `refclassroommemberstatus` WRITE;
/*!40000 ALTER TABLE `refclassroommemberstatus` DISABLE KEYS */;
INSERT INTO `refclassroommemberstatus` VALUES (1,'Pending Approval','Applied for membership'),(2,'Active','request to join is approved'),(3,'Expired','request to join is approved'),(4,'Rejected','rejected');
/*!40000 ALTER TABLE `refclassroommemberstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refquestionlevel`
--

LOCK TABLES `refquestionlevel` WRITE;
/*!40000 ALTER TABLE `refquestionlevel` DISABLE KEYS */;
INSERT INTO `refquestionlevel` VALUES (1,'medium',1),(2,'hard',3),(3,'easy',1);
/*!40000 ALTER TABLE `refquestionlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refquestionstatus`
--

LOCK TABLES `refquestionstatus` WRITE;
/*!40000 ALTER TABLE `refquestionstatus` DISABLE KEYS */;
INSERT INTO `refquestionstatus` VALUES (1,'new');
/*!40000 ALTER TABLE `refquestionstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refsubject`
--

LOCK TABLES `refsubject` WRITE;
/*!40000 ALTER TABLE `refsubject` DISABLE KEYS */;
INSERT INTO `refsubject` VALUES (1,'Mathematics','mathematics'),(2,'Chemistry','chemistry'),(3,'Physics','physics');
/*!40000 ALTER TABLE `refsubject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refusertype`
--

LOCK TABLES `refusertype` WRITE;
/*!40000 ALTER TABLE `refusertype` DISABLE KEYS */;
INSERT INTO `refusertype` VALUES (1,'application_admin','User who creates new accounts'),(2,'account_admin','User who creates other users and classrooms in an account'),(3,'classroom_admin','User who is the admin of the classroom'),(4,'application_user','User who usese the application');
/*!40000 ALTER TABLE `refusertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'javascript3','This is an OO programming language'),(2,'java','This is an OO programming language');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `userreputation`
--

LOCK TABLES `userreputation` WRITE;
/*!40000 ALTER TABLE `userreputation` DISABLE KEYS */;
/*!40000 ALTER TABLE `userreputation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'crajee_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `answeraddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `answeraddsproc`(
 pquestionid int,
 pansweredbyuserid int,
 pcontent text,
 paccountId int
)
begin

declare vpostid int default 0;
insert into post (postedby, posttype, votes, content, postdate, accountId ) values (pansweredbyuserid, 0, 1, pcontent, now(), paccountId);
set vpostid = last_insert_id();
 insert into answer (answerid,questionid )
    values (vpostid, pquestionid);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `classroomjoin` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `classroomjoin`(
 puserid int,
 pclassroomid int

)
begin

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `joinclassroomsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `joinclassroomsproc`(
 puserid int,
 pclassroomid int
)
begin

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionaddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionaddsproc`(
ppostedby int,
ptitle varchar(50),
pcontent text,
prefsubjectid int,
pclassroomid int
)
begin
 declare vquestionid int default 0;

 insert into post ( postdate, postedby, content, posttype, classroomid)
 values ( now(), ppostedby, pcontent,  1, pclassroomid);

    set vquestionid = last_insert_id();

 insert into question (questionid, refsubjectid, questionlevelid, refquestionstatusid ,
     lastactivedate, title)
 values (vquestionid,prefsubjectid,1, 1,  now(), ptitle );

 -- select questionid into vquestionid from question where title = ptitle;

 call questionget(vquestionid);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionget` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionget`(
 pquestionid int
)
begin

 select
  questionid,
  refsubjectid,
  questionlevelid,
  p.postedby,
        p.posttype,
  u.firstname,
  u.middlename,
  u.lastname,
  refquestionstatusid,
  title,
  p.content,
  p.noofviews as noofviews,
  p.postdate,
  lastactivedate,
  p.votes,
    p.classroomid as classroomid,
  ispublic
 from
  question q
    join post p on p.postid = q.questionid
  join appuser u on u.appuserid = p.postedby
 where
  questionid = pquestionid;

 select
  answerid, p.postedby as answeredbyuserid, questionid, p.votes, p.content
 from
  answer a
    join post p on p.postid = a.answerid
 where
  questionid = pquestionid;

  select tagid from questiontag where questionid = pquestionid;


end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `questionupdatestats` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `questionupdatestats`(
 pquestionid int,
 pvotes int
)
begin

 declare vnoofviews, vvotes int default 0;

 select noofviews into vnoofviews from question where questionid = pquestionid;
 select votes into vvotes from question where questionid = pquestionid;


 update question
 set noofviews = vnoofviews +1,
  lastactivedate = now();

 if(pvotes != 0)
  then
   update question
   set votes = vvotes +pvotes;

 end if;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `useraddsproc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `useraddsproc`(
pemail varchar(20),
ppasword varchar(20),
pfirstname varchar(20),
pmiddlename varchar(20),
plastname varchar(20)
)
begin

 insert into appuser(email,pasword,firstname,middlename,lastname)values(pemail,ppasword,pfirstname,pmiddlename,plastname);

 select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
 from appuser where email = pemail;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userget` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userget`(
pappuserid int,
pemail varchar(20)
)
begin

 if(pappuserid is not null)
 then
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where appuserid = pappuserid;
 else
  select appuserid, userreputationid, email, pasword, firstname, middlename, lastname
  from appuser where email = pemail;
 end if;


end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userupdate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userupdate`(
pappuserid int,
puserreputationid int,
pemail varchar(20)
)
begin

 if(puserreputationid is not null)
 then
  update appuser set userreputationid = puserreputationid where appuserid = pappuserid;
 end if;

 if(pemail is not null)
 then
  update appuser set email = pemail,pasword = ppasword where appuserid = pappuserid;
 end if;

 if(ppasword is not null)
 then
  update appuser set pasword = ppasword where appuserid = pappuserid;
 end if;

 select * from appuser where email = pemail;

end ;;
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

-- Dump completed on 2020-05-09 20:14:21
