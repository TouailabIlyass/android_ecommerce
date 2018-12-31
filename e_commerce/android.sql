-- MySQL dump 10.16  Distrib 10.1.34-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: android
-- ------------------------------------------------------
-- Server version	10.1.34-MariaDB

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

--
-- Table structure for table `categoris`
--

DROP TABLE IF EXISTS `categoris`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoris` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoris`
--

LOCK TABLES `categoris` WRITE;
/*!40000 ALTER TABLE `categoris` DISABLE KEYS */;
INSERT INTO `categoris` VALUES (1,'pc'),(2,'vetements'),(3,'sous-vetements'),(4,'pc_accessories');
/*!40000 ALTER TABLE `categoris` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `addr` varchar(30) DEFAULT NULL,
  `ville` varchar(20) DEFAULT NULL,
  `cin` varchar(10) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `mail` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'ilyase','tou','06102030','new fes','fes','hh1244','ilyass07','123','ilyass@gmail.com'),(2,'anas','ahah','0639844','new fes','fes','hh606','anas01','123','anas@gmail.com'),(11,'il','il','hh12','safi','safi','061234','il7','111','il@gmail.com'),(12,'adnan','akkaoui','151380','fes','fes addr','0691915935','adnan1','555','adnan@gmail.com'),(21,'anas','anas','hcb12','safi','adr','060606','anas11','321','i.touailab007@gmail.com'),(22,'youness','youness','hh12','fes','fes addr','060606','youn1','111','anas@gmail.com');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmd`
--

DROP TABLE IF EXISTS `cmd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `qte` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_produit` (`id_produit`),
  CONSTRAINT `cmd_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`),
  CONSTRAINT `cmd_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmd`
--

LOCK TABLES `cmd` WRITE;
/*!40000 ALTER TABLE `cmd` DISABLE KEYS */;
INSERT INTO `cmd` VALUES (1,1,1,3),(2,11,15,2);
/*!40000 ALTER TABLE `cmd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `id_four` int(11) DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_produit` (`id_produit`),
  KEY `id_four` (`id_four`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`id_four`) REFERENCES `fournisseurs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,1,1,'nice'),(12,11,3,1,'nice2'),(13,12,8,3,'pop up'),(14,1,2,1,'20dh mslkak\n');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fournisseurs`
--

DROP TABLE IF EXISTS `fournisseurs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fournisseurs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `addr` varchar(20) DEFAULT NULL,
  `ville` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fournisseurs`
--

LOCK TABLES `fournisseurs` WRITE;
/*!40000 ALTER TABLE `fournisseurs` DISABLE KEYS */;
INSERT INTO `fournisseurs` VALUES (1,'marjan','fes add','fes'),(2,'carrfoure','fes add','fes'),(3,'bim','fes add','fes'),(4,'decathlon','fes addr','fes');
/*!40000 ALTER TABLE `fournisseurs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `id_four` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_produit` (`id_produit`),
  KEY `id_four` (`id_four`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`),
  CONSTRAINT `likes_ibfk_3` FOREIGN KEY (`id_four`) REFERENCES `fournisseurs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (7,1,3,1),(8,1,2,1),(9,2,2,1),(10,12,8,3),(11,1,1,1),(12,1,5,3),(14,1,6,3);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prixs`
--

DROP TABLE IF EXISTS `prixs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prixs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_produit` int(11) NOT NULL,
  `id_four` int(11) NOT NULL,
  `prix` double DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`id_four`,`id_produit`),
  KEY `id_produit` (`id_produit`),
  KEY `id_four` (`id_four`),
  CONSTRAINT `prixs_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`),
  CONSTRAINT `prixs_ibfk_2` FOREIGN KEY (`id_four`) REFERENCES `fournisseurs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prixs`
--

LOCK TABLES `prixs` WRITE;
/*!40000 ALTER TABLE `prixs` DISABLE KEYS */;
INSERT INTO `prixs` VALUES (1,1,1,1500,1500,'https://bit.ly/2QRR429'),(2,2,1,300,290,'https://bit.ly/2SMyAxd'),(3,3,1,400,400,'https://bit.ly/2QSOSY4'),(4,4,1,50,50,'https://bit.ly/2GgPUcu'),(5,5,3,99,80,'http://bit.ly/2POFv7v'),(6,6,3,99,99,'http://bit.ly/2SkHZME'),(7,7,3,99,99,'http://bit.ly/2T31mtM'),(8,8,3,99,85,'http://bit.ly/2Scx9IM'),(9,9,3,99,85,'http://bit.ly/2PTaTl2'),(10,10,3,99,85,'http://bit.ly/2R81dbb'),(11,11,3,99,85,'http://bit.ly/2PSHpUC'),(12,12,3,99,85,'http://bit.ly/2EB0qsv'),(13,13,1,20,20,'http://bit.ly/2EB225x'),(14,14,1,20,20,'http://bit.ly/2rPr9u2'),(15,15,1,50,50,'http://bit.ly/2A92gxZ'),(16,16,1,20,20,'http://bit.ly/2rNHvDt'),(17,17,1,100,100,'http://bit.ly/2SfqbCD'),(18,18,1,30,30,'http://bit.ly/2V1cSI3'),(19,19,1,30,30,'http://bit.ly/2ED6HDZ'),(20,20,3,150,150,'http://bit.ly/2T90ZxT'),(21,21,3,150,150,'http://bit.ly/2V777sn'),(22,22,3,150,150,'http://bit.ly/2BDsDMo'),(23,23,3,150,150,'http://bit.ly/2GE1YEL');
/*!40000 ALTER TABLE `prixs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produits`
--

DROP TABLE IF EXISTS `produits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produits` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) DEFAULT NULL,
  `description` text,
  `categoris` int(11) DEFAULT NULL,
  `ligne` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produits`
--

LOCK TABLES `produits` WRITE;
/*!40000 ALTER TABLE `produits` DISABLE KEYS */;
INSERT INTO `produits` VALUES (1,'lenovo','pc 4g ram',1,0),(2,'jacket cuire','cuire jacket',2,0),(3,'hp e300','hp pc',1,0),(4,'usb','usb3.0 16gb',1,1),(5,'maillot','sous vetements',3,0),(6,'maillot','sous vetements',3,0),(7,'maillot','sous vetements',3,0),(8,'maillot','sous vetements',3,0),(9,'maillot','sous vetements',3,0),(10,'maillot','sous vetements',3,0),(11,'maillot','sous vetements',3,0),(12,'maillot','sous vetements',3,0),(13,'sourie','sourie gaming',4,1),(14,'sourie','sourie sans file',4,1),(15,'casque','casque sans file',4,1),(16,'vga','vga cable',4,1),(17,'cartable','Dell cartable',4,1),(18,'sourie','sourie wirelesse',4,1),(19,'sourie','sourie wirelesse',4,1),(20,'jacket','jacket',2,0),(21,'jacket','jacket',2,0),(22,'jacket','jacket',2,0),(23,'jacket','jacket',2,0);
/*!40000 ALTER TABLE `produits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valideclient`
--

DROP TABLE IF EXISTS `valideclient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valideclient` (
  `id` int(11) NOT NULL,
  `code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `valideclient_ibfk_1` FOREIGN KEY (`id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valideclient`
--

LOCK TABLES `valideclient` WRITE;
/*!40000 ALTER TABLE `valideclient` DISABLE KEYS */;
/*!40000 ALTER TABLE `valideclient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-31  9:24:38
