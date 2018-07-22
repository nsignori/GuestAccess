-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.7-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for guestaccess
CREATE DATABASE IF NOT EXISTS `guestaccess` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `guestaccess`;

-- Dumping structure for table guestaccess.accessrules
CREATE TABLE IF NOT EXISTS `accessrules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homeowner` varchar(15) NOT NULL,
  `guest` varchar(30) NOT NULL,
  `guestnumber` int(11) NOT NULL,
  `pin` char(4) NOT NULL,
  `starttime` char(16) NOT NULL,
  `endtime` char(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__users` (`homeowner`),
  CONSTRAINT `FK__users` FOREIGN KEY (`homeowner`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
-- Dumping structure for table guestaccess.users
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(15) NOT NULL,
  `password` char(32) NOT NULL,
  `salt` char(32) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
