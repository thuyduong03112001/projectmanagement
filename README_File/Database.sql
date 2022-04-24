-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: workflowbox.cfx8ipmugw9b.us-east-1.rds.amazonaws.com    Database: workflowbox
-- ------------------------------------------------------
-- Server version 8.0.27

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FullName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DOB` date NOT NULL,
  `Gender` tinyint NOT NULL,
  `Email` varchar(150) NOT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `Account` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `JoinDate` date NOT NULL,
  `Image` varchar(200) DEFAULT NULL,
  `RoleID` int NOT NULL,
  `Bio` text,
  PRIMARY KEY (`ID`),
  KEY `Account_Role_idx` (`RoleID`),
  CONSTRAINT `Account_Role` FOREIGN KEY (`RoleID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'Tran Bao Bao','2001-11-21',12,'baobaotran@gmail.com','0987134444','baobao','baobao','2002-01-01','01.png',6,'Staff'),(2,'Nguyen Phuoc Thinh','2001-01-01',12,'phuocthinh@gmail.com','0912878345','phuocthinh','123','2002-01-01','01.png',6,'Developer'),(3,'Nguyen Minh Hai','2001-01-01',12,'minhhai@gmail.com','0238784445','minhhai','123','2002-01-01','01.png',6,'Staff'),(4,'Do Thuy Duong','2001-09-11',13,'duongdthe151367@fpt.edu.vn','0347973129','thuyduong','duong123','2009-01-01','user.png',7,'Student at FPT university'),(5,'Pham Luu Tuan Tai','1988-06-13',12,'iamisaac@gmail.com','0976049480','iamlion','lion123','2009-01-12','user.png',7,'Singer in Viet Nam'),(6,'Pham Bao Han','2001-12-09',13,'hanpham@gmail.com','0394317387','hanpham','hanpham','2010-12-10','user.png',7,'Actor'),(7,'Nguyen Thuy Chi','2002-10-10',13,'thuychi@gmail.com','0976343555','thuychi','123','2010-09-10','user.png',7,'Business Analyst'),(8,'Mai Hong Ngoc','1989-10-13',13,'dongnhi@gmail.com','0987479222','dongnhi','123','2010-10-01','user.png',7,'Project Manager'),(9,'Nguyen Minh Anh','2001-10-10',13,'minhanh2k1@gmail.com','0988456123','manh123','123','2010-10-11','user.png',7,'Student'),(10,'Truong Ngoc Trang','2000-12-12',13,'ngoctrang@gmail.com','0374026339','ngoctrang','ngoctrang','2015-01-15','user.png',7,'Student'),(11,'Nguyen Dieu Linh','1999-06-15',13,'dieulinh@gmail.com','0334794880','linh123','123','2012-12-12','user.png',7,'Staff in WorkFlowBox'),(12,'Dao Khanh Lam','1990-12-01',12,'lam123@gmail.com','0343793380','lam123','123','2010-08-12','user.png',7,'Project Manager'),(13,'Luong Tien Anh','2000-09-08',12,'tienanh@gmail.com','0987123456','tienanh','tienanh','2011-04-23','user.png',7,'Student'),(14,'Nguyen Dang Dung','2001-11-11',12,'dangdung@gmail.com','0912380380','dung123','dung123','2013-06-24','user.png',7,'Designer'),(15,'Tran Phuong Nam','2000-09-09',12,'phuongnam@gmail.com','0123456789','phuongnam','phuongnam','2015-10-10','user.png',7,'Designer'),(16,'Do Hoang Long','2001-11-21',13,'longdh21@gmail.com','0986431222','123','1234','2002-01-01','1.jpg',7,'UX UI designer '),(17,'Do Thuy Dung','2001-01-01',12,'hl@gmail.com','0374312380','555','123','2002-01-01','01.png',7,'Tester'),(18,'LTA','2001-09-09',13,'lta@atl.lta','456','lta','123','2002-01-01','https://cdn140.picsart.com/338923974038211.png',5,'Mister Tat'),(19,'MrTat','2001-09-09',13,'MrTat@Mr.Tat','789','mrtat','123','2021-09-06','https://cdn140.picsart.com/338923974038211.png',4,'Booooooooooyah!!'),(20,'Nguyen Anh Tuan','1987-12-25',12,'anhtuan@gmail.com','0987423121','anhtuan','anhtuan','2021-10-12','user.png',7,'Project Manager');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievements` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Icon` varchar(45) NOT NULL,
  `CateID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `achie_idx` (`CateID`),
  CONSTRAINT `Achie` FOREIGN KEY (`CateID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements`
--

LOCK TABLES `achievements` WRITE;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
INSERT INTO `achievements` VALUES (1,'Easy to Customize','Personalise your own workflow','lni-coffee-cup',1),(2,'Clean & Trendy Design','Clean design focuses on the careful and precise positioning of the important elements throughout the site','lni-invention',1),(3,'Free Future Updates','All future updates are FREE. Nothing to pay ever again!','lni-reload',1),(4,'Business Template','Right template for business','lni-briefcase',1),(5,'Tons of Sections','Loads of different predesigned sections to choose','lni-layers',1),(6,'Premier Support','Support all of things via Email','lni-support',1);
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `efforts`
--

DROP TABLE IF EXISTS `efforts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `efforts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `AccID` int NOT NULL,
  `Effort` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_AccID_idx` (`AccID`),
  CONSTRAINT `FK_AccID` FOREIGN KEY (`AccID`) REFERENCES `accounts` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `efforts`
--

LOCK TABLES `efforts` WRITE;
/*!40000 ALTER TABLE `efforts` DISABLE KEYS */;
INSERT INTO `efforts` VALUES (1,1,5.5),(2,2,3.4),(3,5,6.5),(4,4,6),(5,3,7.6),(6,6,5.4),(7,7,4.3),(8,8,3.9),(9,9,2.9),(10,10,5.6),(11,11,4.7),(12,12,4.9),(13,13,5.6),(14,14,5.8),(15,15,6.3),(16,16,7.4),(17,17,7.2),(18,18,7.1),(19,19,6.7),(20,20,5.8);
/*!40000 ALTER TABLE `efforts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedbacks`
--

DROP TABLE IF EXISTS `feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedbacks` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IssueID` int NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Rate` float NOT NULL,
  `Comment` varchar(300) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Issues_idx` (`IssueID`),
  CONSTRAINT `FK_Issues` FOREIGN KEY (`IssueID`) REFERENCES `issues` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedbacks`
--

LOCK TABLES `feedbacks` WRITE;
/*!40000 ALTER TABLE `feedbacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `JoinDate` date NOT NULL,
  `ParentID` int DEFAULT NULL,
  `StatusID` int NOT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `TypeID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Group_idx` (`ParentID`),
  KEY `stid_idx` (`StatusID`),
  KEY `typeid_idx` (`TypeID`),
  CONSTRAINT `FK_Group` FOREIGN KEY (`ParentID`) REFERENCES `groups` (`ID`),
  CONSTRAINT `stid` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`),
  CONSTRAINT `typeid` FOREIGN KEY (`TypeID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'BoBo Team','2022-02-09',NULL,25,NULL,22),(2,'inamo team','2022-02-10',NULL,25,NULL,22),(3,'Superman','2022-02-12',3,25,'Sub-Group of Leader',23),(4,'Spiderman','2022-02-15',4,24,'UM',22),(5,'Yellow Superman','2022-02-12',5,24,'Sub-Group',23);
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issues`
--

DROP TABLE IF EXISTS `issues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issues` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `ProjectID` int NOT NULL,
  `CreatedBy` int NOT NULL,
  `TypeID` int NOT NULL,
  `CreatedDate` date NOT NULL,
  `Deliverable` varchar(200) DEFAULT NULL,
  `StatusID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Project_idx` (`ProjectID`),
  KEY `FK_Create_idx` (`CreatedBy`),
  KEY `I_st_status_idx` (`StatusID`),
  KEY `I_st_type_idx` (`TypeID`),
  CONSTRAINT `FK_Create` FOREIGN KEY (`CreatedBy`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `FK_Project` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ID`),
  CONSTRAINT `I_st_status` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`),
  CONSTRAINT `I_st_type` FOREIGN KEY (`TypeID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issues`
--

LOCK TABLES `issues` WRITE;
/*!40000 ALTER TABLE `issues` DISABLE KEYS */;
INSERT INTO `issues` VALUES (6,'Change Suggestion2',2,2,74,'2022-01-01',NULL,67),(7,'Question2',2,2,75,'2022-01-02',NULL,68),(8,'issue2',2,2,76,'2022-01-03',NULL,69),(12,'Change Suggestion1',2,2,77,'2002-01-20',NULL,70),(13,'Change Suggestion3',2,2,78,'2002-01-20',NULL,71),(14,'Change Suggestion4',2,2,74,'2002-01-20',NULL,73);
/*!40000 ALTER TABLE `issues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manages`
--

DROP TABLE IF EXISTS `manages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manages` (
  `ID` int NOT NULL,
  `ManagerID` int NOT NULL,
  `GroupID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `grid_idx` (`GroupID`),
  KEY `maid_idx` (`ManagerID`),
  CONSTRAINT `grid` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`ID`),
  CONSTRAINT `maid` FOREIGN KEY (`ManagerID`) REFERENCES `accounts` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manages`
--

LOCK TABLES `manages` WRITE;
/*!40000 ALTER TABLE `manages` DISABLE KEYS */;
INSERT INTO `manages` VALUES (1,1,1),(2,2,2),(3,3,3);
/*!40000 ALTER TABLE `manages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `Date` date NOT NULL,
  `Content` mediumtext NOT NULL,
  `Brief` varchar(300) NOT NULL,
  `Thumbnail` varchar(200) NOT NULL,
  `HRID` int NOT NULL,
  `StatusID` int NOT NULL,
  `CateID` int NOT NULL,
  `Flag` tinyint NOT NULL DEFAULT '0' COMMENT 'This one for featured on/off',
  PRIMARY KEY (`ID`),
  KEY `Post_Setting_idx` (`StatusID`),
  KEY `Post_HR_idx` (`HRID`),
  KEY `Post_Cate_idx` (`CateID`),
  CONSTRAINT `Post_Cate` FOREIGN KEY (`CateID`) REFERENCES `settings` (`id`),
  CONSTRAINT `Post_HR` FOREIGN KEY (`HRID`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `Post_Status` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'New HR Changed By Other','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://nef.vn/wp-content/uploads/2019/12/hr-human-resources.jpg',1,3,18,1),(2,'New HR 2','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://wiki.tino.org/wp-content/uploads/2021/06/HR-career.png',1,1,18,0),(3,'New HR 3','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://acabiz.vn/backend/images/blog_images/15771801980.png',1,3,18,0),(4,'New HR 4','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://nef.vn/wp-content/uploads/2019/12/hr-human-resources.jpg',1,3,18,0),(5,'New HR 5','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://wiki.tino.org/wp-content/uploads/2021/06/HR-career.png',1,3,18,0),(6,'New HR 6','2021-12-27','Gender: Male/Female; Age: 25-45 years old; Graduated with college or higher degree major in law, human resources management, or other related fields; At least 3-5 years of experience in managing human resources in the fields of contracts, insurance, labor law, training and development in manufacturing factory; Good use of Chinese or English','Degree: College; Age: 25 - 45','https://acabiz.vn/backend/images/blog_images/15771801980.png',1,3,18,0),(7,'New Dev','2021-12-27','Opportunities for promotion and career-up with clear career path','Up to 19 days of paid leave; Foreign language allowance up to 500 USD/month','https://www.necam.com/images/logo_orchestrating_955x500.png',1,3,14,1),(8,'New Dev 2','2021-12-27','Business Development Project Manager','An attractive monthly salary (from VND50-62,000,000)','https://it.tdtu.edu.vn/sites/cntt/files/articles/gameloft-1024x661-1509002697963_0.jpg',1,3,14,1),(9,'SuperCar','2022-01-26','Most Expensive Cars In The World\r\nFerrari LaFerrari - $1.4 Million\r\nPagani Huayra - $1.4 Million\r\nMcLaren Elva - $1.7 Million\r\nFerrari Monza - $1.7 Million\r\nBentley Bacalar - $1.9 Million\r\nPininfarina Battista - $2.5 Million\r\nMercedes-AMG Project One - $2.7 Million\r\nAston Martin Victor - $3.0 Million\r\nBugatti Bolide - $3.0 Million\r\nAston Martin Valkyrie - $3.2 Million\r\nW Motors Lykan Hypersport - $3.4 Million\r\nPagani Huayra Roadster BC - $3.5 Million\r\nBugatti Chiron Pur Sport - $3.6 Million\r\nLamborghini Sian - $3.6 million\r\nBugatti Chiron Super Sport 300+ - $3.9 Million\r\nLamborghini Veneno - $4.5 Million\r\nKoenigsegg CCXR Trevita - $4.8 Million\r\nBugatti Divo - $5.8 Million\r\nMercedes-Maybach Exelero - $8.0 Million\r\nBugatti Centodieci - $9.0 Million\r\nBugatti La Voiture Noire - $18.7 Million\r\nRolls-Royce Boat Tail - $28.0 Million','$11B\r\nSale off 10%\r\nBest','https://images.hdqwalls.com/wallpapers/bmw-i8-crossfade-paint-garage-italia-new.jpg',1,3,21,1),(11,'Newone','2022-02-14','Future car','$1B','https://i.ytimg.com/vi/dip_8dmrcaU/maxresdefault.jpg',1,3,21,0),(12,'Newest','2022-02-14','Tester','Brief tester','https://axcelavietnam.com/wp-content/uploads/2020/04/the-big-picture-la-tu-vung-duoc-su-dung-pho-bien-trong-tieng-anh-thuong-mai.jpg',1,3,15,0),(13,'New Car Edited','2022-02-15','Test draft','brief','https://image.vtc.vn/resize/Da9xKKWytSrs1xd-RyErHayehg_Uq7SE0/upload/2020/04/05/sieu-xe-lamborghini-aventador-lp700-4-dat-nhat-viet-nam-doi-4-mau-chua-day-1-nam-1-16063966.jpg',1,3,14,0),(14,'lamborghini huracan','2022-02-15','lamborghini huracan','Nice car','http://cms-i.autodaily.vn/du-lieu/2019/01/08/2020-lamborghini-huracan-evo-lo-dien-4.jpg',1,3,17,0),(15,'Test Draft On','2022-02-15','test draft','On','https://giaxeoto.vn/admin/upload/images/resize/640-duoi-xe-lamborghini-huracan-evo.jpg',1,3,15,1),(16,'Test img','2022-02-16','Test img','Test img','imgs/null',3,1,15,0);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_deliverables`
--

DROP TABLE IF EXISTS `project_deliverables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_deliverables` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ProjectID` int NOT NULL,
  `Materials` varchar(200) NOT NULL,
  `StatusID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PD_P_idx` (`ProjectID`),
  KEY `PD_ST_idx` (`StatusID`),
  CONSTRAINT `FK_PD_P` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ID`),
  CONSTRAINT `PD_ST` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_deliverables`
--

LOCK TABLES `project_deliverables` WRITE;
/*!40000 ALTER TABLE `project_deliverables` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_deliverables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `GroupID` int NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date DEFAULT NULL,
  `Progess` float NOT NULL,
  `Status` tinyint NOT NULL,
  `Description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_P_G_idx` (`GroupID`),
  CONSTRAINT `FK_P_Gr` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Project1',3,'2009-07-01','2010-01-01',7.5,1,'Work In Progress'),(2,'WorkFlowBox',1,'2022-02-01','2022-02-28',0.8,1,'WorkFlowBox\r\n'),(3,'PrjManage',2,'2022-02-01','2022-02-28',0.5,1,'PrjManage');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requirements` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `ProjectID` int NOT NULL,
  `Owner` varchar(100) NOT NULL,
  `UpdatedDate` date NOT NULL,
  `StatusID` int NOT NULL,
  `TypeID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Re_Pr_idx` (`ProjectID`),
  KEY `Re_St_status_idx` (`StatusID`),
  KEY `Re_St_type_idx` (`TypeID`),
  CONSTRAINT `FK_Re_Pr` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ID`),
  CONSTRAINT `Re_St_status` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`),
  CONSTRAINT `Re_St_type` FOREIGN KEY (`TypeID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
INSERT INTO `requirements` VALUES (1,'Add Value',2,'Mr.Tat','2022-02-26',59,67),(2,'Add Name',2,'Mr.Daniel','2022-02-26',57,66),(3,'Edit Value',2,'Mr.Tat','2022-02-26',63,68);
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `risks`
--

DROP TABLE IF EXISTS `risks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `risks` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Cause` varchar(300) NOT NULL,
  `Impact` varchar(300) NOT NULL,
  `ProjectID` int NOT NULL,
  `Description` varchar(300) DEFAULT NULL,
  `StatusID` int NOT NULL,
  `CategoryID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_R_Pr_idx` (`ProjectID`),
  KEY `Ri_st_status_idx` (`StatusID`),
  KEY `Ri_st_category_idx` (`CategoryID`),
  CONSTRAINT `FK_R_Pr` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ID`),
  CONSTRAINT `Ri_st_category` FOREIGN KEY (`CategoryID`) REFERENCES `settings` (`id`),
  CONSTRAINT `Ri_st_status` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risks`
--

LOCK TABLES `risks` WRITE;
/*!40000 ALTER TABLE `risks` DISABLE KEYS */;
/*!40000 ALTER TABLE `risks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `TypeID` int NOT NULL,
  `Value` varchar(50) NOT NULL,
  `Order` int DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Status` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `st_type_idx` (`TypeID`),
  CONSTRAINT `st_type` FOREIGN KEY (`TypeID`) REFERENCES `type_settings` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,1,'Draft',1,NULL,1),(2,1,'Deleted',2,NULL,1),(3,1,'Published',3,NULL,1),(4,13,'admin',4,NULL,1),(5,13,'HR',5,NULL,1),(6,13,'Manager',6,NULL,1),(7,13,'Staff',7,NULL,1),(8,9,'Registered',8,NULL,1),(9,9,'Verified',9,NULL,1),(10,9,'Active',10,NULL,1),(11,9,'Inactive',11,NULL,1),(12,14,'Male',12,NULL,1),(13,14,'Female',13,NULL,1),(14,15,'Developer',14,NULL,1),(15,15,'Tester',15,NULL,1),(16,15,'Designer',16,NULL,1),(17,15,'PM',17,NULL,1),(18,15,'HR',18,NULL,1),(19,15,'QA',19,NULL,1),(20,15,'BA',20,NULL,1),(21,15,'Other',21,NULL,1),(22,16,'BA',22,NULL,1),(23,16,'Non-BA',23,NULL,1),(24,17,'Deleted',24,NULL,1),(25,17,'Working',25,NULL,1),(26,10,'Designer',26,NULL,1),(27,10,'Relaxer',27,NULL,1),(28,10,'Tester',28,NULL,1),(29,10,'Developer',29,NULL,1),(30,18,'Hide',30,NULL,1),(31,18,'Show',31,NULL,1),(32,11,'Submitted',32,NULL,1),(33,11,'assigned',33,NULL,1),(34,11,'avoided',34,NULL,1),(35,11,'transfering',35,NULL,1),(36,11,'mitigating',36,NULL,1),(37,11,'watching',37,NULL,1),(38,11,'completed',38,NULL,1),(39,11,'closed',39,NULL,1),(40,12,'Requirement',40,NULL,1),(41,12,'Technology',41,NULL,1),(42,12,'Complexity & Interface',42,NULL,1),(43,12,'Performance & Realability',43,NULL,1),(44,12,'Quality',44,NULL,1),(45,12,'External',45,NULL,1),(46,12,'Organizational',46,NULL,1),(47,12,'Project management',47,NULL,1),(48,7,'Trainning',48,'Trainning',1),(49,7,'Requirement',49,'Requirement',1),(50,7,'Design',50,'Design',1),(51,7,'Coding',51,'Coding',1),(52,7,'Testing',52,'Testing',1),(53,7,'Supporting',53,'Supporting',1),(54,8,'Submitted',54,NULL,1),(55,8,'Approved',55,NULL,1),(56,8,'Rejected',56,NULL,1),(57,3,'Pending',24,NULL,1),(58,3,'Committed',25,NULL,1),(59,3,'Analyzed',25,NULL,1),(60,3,'Designed',25,NULL,1),(61,3,'Coded',25,NULL,1),(62,3,'Tested',25,NULL,1),(63,3,'Completed',25,NULL,1),(64,3,'Accepted',25,NULL,1),(65,3,'Ancelled',25,NULL,1),(66,4,'New requirement',24,NULL,1),(67,4,'Change request',25,NULL,1),(68,4,'Process',25,NULL,1),(69,5,'Submitted',14,NULL,1),(70,5,'Assigned',15,NULL,1),(71,5,'Cancelled',16,NULL,1),(72,5,'Completed',17,NULL,1),(73,5,'Closed',18,NULL,1),(74,6,'Task',19,NULL,1),(75,6,'Issue',20,NULL,1),(76,6,'Question',21,NULL,1),(77,6,'Defect',22,NULL,1),(78,6,'Change Suggestion',22,NULL,1);
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sliders`
--

DROP TABLE IF EXISTS `sliders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sliders` (
  `ID` int NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Image` varchar(200) NOT NULL,
  `StatusID` int NOT NULL,
  `Backlink` varchar(200) NOT NULL,
  `Note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `slider_setting_idx` (`StatusID`),
  CONSTRAINT `slider_setting` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sliders`
--

LOCK TABLES `sliders` WRITE;
/*!40000 ALTER TABLE `sliders` DISABLE KEYS */;
INSERT INTO `sliders` VALUES (1,'Train Project Management','https://smartpro.vn/images/programes/768x1024/423247project_mobile.jpg',31,'localhost:8080/workflowbox/sliderdetail?id=1',NULL),(2,'What is Project Management?','https://topdev.vn/blog/wp-content/uploads/2020/09/project-manager-la-gi-1.jpg',31,'localhost:8080/workflowbox/sliderdetail?id=2',NULL),(3,'Role of PM in Project Management','https://topdev.vn/blog/wp-content/uploads/2020/09/project-manager-la-gi-4.jpg',31,'localhost:8080/workflowbox/sliderdetail?id=3',NULL),(4,'Aim','https://mediatemple.net/blog/wp-content/uploads/2021/12/042721-Blog-Heroes_Large-Scale-Projects_1665x705.jpg',30,'localhost:8080/workflowbox/sliderdetail?id=4','Need to change Title'),(5,'Future','https://www.simplilearn.com/ice9/free_resources_article_thumb/PM.jpg',31,'localhost:8080/workflowbox/sliderdetail?id=5',NULL);
/*!40000 ALTER TABLE `sliders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timesheets`
--

DROP TABLE IF EXISTS `timesheets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timesheets` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `AccID` int NOT NULL,
  `Date` date NOT NULL,
  `Title` varchar(100) NOT NULL,
  `ProjectID` int NOT NULL,
  `Process` float NOT NULL,
  `Duration` float NOT NULL,
  `WorkResult` varchar(50) NOT NULL,
  `RejectionReason` varchar(100) DEFAULT NULL,
  `StatusID` int NOT NULL,
  `ProcessID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Ti_Acc_idx` (`AccID`),
  KEY `FK_Ti_Pr_idx` (`ProjectID`),
  KEY `Ti_st_pro_idx` (`ProcessID`),
  KEY `Ti_st_status_idx` (`StatusID`),
  CONSTRAINT `FK_Ti_Acc` FOREIGN KEY (`AccID`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `FK_Ti_Pr` FOREIGN KEY (`ProjectID`) REFERENCES `projects` (`ID`),
  CONSTRAINT `Ti_st_pro` FOREIGN KEY (`ProcessID`) REFERENCES `settings` (`id`),
  CONSTRAINT `Ti_st_status` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timesheets`
--

LOCK TABLES `timesheets` WRITE;
/*!40000 ALTER TABLE `timesheets` DISABLE KEYS */;
INSERT INTO `timesheets` VALUES (1,1,'2022-02-01','write SRS',2,0.21,0.45,'',NULL,54,52),(2,1,'2022-02-23','Write Design',3,0.342,0.782,'',NULL,55,52),(3,8,'2022-02-15','Coding iter3',2,0.1,0.2,'well done',NULL,55,49),(4,1,'2022-02-19','Coding login',3,0.2,0.54,'good',NULL,55,53),(5,1,'2022-02-15','integrating branch',2,0.1,0.2,'well done',NULL,56,51);
/*!40000 ALTER TABLE `timesheets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_settings`
--

DROP TABLE IF EXISTS `type_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_settings` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_settings`
--

LOCK TABLES `type_settings` WRITE;
/*!40000 ALTER TABLE `type_settings` DISABLE KEYS */;
INSERT INTO `type_settings` VALUES (1,'post status'),(2,'deliverable status'),(3,'requirement status'),(4,'requirement type'),(5,'issue status'),(6,'issue type'),(7,'timesheet process'),(8,'timesheet status'),(9,'staff status'),(10,'project roles'),(11,'risk status'),(12,'risk category'),(13,'role'),(14,'gender'),(15,'post category'),(16,'group type'),(17,'group status'),(18,'slider status');
/*!40000 ALTER TABLE `type_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlist`
--

DROP TABLE IF EXISTS `userlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userlist` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ManagerID` int NOT NULL,
  `StaffID` int NOT NULL,
  `StatusID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `StaffStatus_idx` (`StatusID`),
  KEY `StaffID_idx` (`StaffID`),
  KEY `ManagerID_idx` (`ManagerID`),
  CONSTRAINT `ManagerID` FOREIGN KEY (`ManagerID`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `StaffID` FOREIGN KEY (`StaffID`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `StaffStatus` FOREIGN KEY (`StatusID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlist`
--

LOCK TABLES `userlist` WRITE;
/*!40000 ALTER TABLE `userlist` DISABLE KEYS */;
INSERT INTO `userlist` VALUES (1,1,8,10),(2,1,5,10),(3,1,4,10),(4,1,18,10),(5,1,6,10),(6,2,20,11),(7,2,9,10),(8,2,7,11),(9,2,11,10),(10,2,10,10),(11,3,12,10),(12,3,13,11),(13,3,14,10),(14,3,15,10),(15,3,17,10);
/*!40000 ALTER TABLE `userlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `works` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `StaffID` int NOT NULL,
  `GroupID` int NOT NULL,
  `ProjectRoleID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Acc_idx` (`StaffID`),
  KEY `FK_Gr_idx` (`GroupID`),
  KEY `StaffStatus_idx` (`ProjectRoleID`),
  CONSTRAINT `FK_Acc` FOREIGN KEY (`StaffID`) REFERENCES `accounts` (`ID`),
  CONSTRAINT `FK_Gr` FOREIGN KEY (`GroupID`) REFERENCES `groups` (`ID`),
  CONSTRAINT `ProjectRole` FOREIGN KEY (`ProjectRoleID`) REFERENCES `settings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (1,8,1,24),(2,6,1,24),(3,5,1,23),(4,1,1,6),(5,2,2,6),(6,3,3,6),(7,4,1,25),(8,7,2,7),(9,9,2,7),(10,10,2,7),(11,11,2,7),(12,12,3,7),(13,13,3,7),(14,14,3,7),(15,15,3,7),(16,18,1,26),(17,20,2,7),(18,17,3,7);
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-27 23:25:57
