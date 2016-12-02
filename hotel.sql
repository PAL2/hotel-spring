-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных booking
CREATE DATABASE IF NOT EXISTS `booking` /*!40100 DEFAULT CHARACTER SET cp1251 */;
USE `booking`;


-- Дамп структуры для таблица booking.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `summa` int(11) unsigned NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп данных таблицы booking.account: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`account_id`, `summa`) VALUES
	(88, 140),
	(89, 130),
	(92, 400),
	(93, 50);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- Дамп структуры для таблица booking.booking
CREATE TABLE IF NOT EXISTS `booking` (
  `booking_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `place` int(10) unsigned DEFAULT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `room_id` int(10) unsigned DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `account_id` int(10) unsigned DEFAULT NULL,
  `status` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `Account` (`account_id`),
  KEY `Room` (`room_id`),
  KEY `User` (`user_id`),
  CONSTRAINT `Account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `Room` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `User` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп данных таблицы booking.booking: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` (`booking_id`, `start_date`, `end_date`, `place`, `category`, `room_id`, `user_id`, `account_id`, `status`) VALUES
	(19, '2016-10-15', '2016-10-23', 1, 'standard', 2, 20, 92, 'billed'),
	(22, '2016-10-26', '2016-10-27', 4, 'standard', 10, 19, 88, 'billed'),
	(86, '2016-10-26', '2016-10-27', 1, 'standard', 1, 19, 93, 'paid'),
	(92, '2016-11-17', '2016-11-18', 3, 'lux', 21, 19, 89, 'paid'),
	(94, '2016-12-02', '2016-12-03', 4, 'lux', NULL, 19, NULL, 'new'),
	(95, '2016-11-16', '2016-11-17', 1, 'standard', NULL, 19, NULL, 'new'),
	(96, '2016-11-26', '2016-11-30', 1, 'standard', NULL, 19, NULL, 'new'),
	(97, '2016-11-30', '2016-12-01', 1, 'standard', NULL, 19, NULL, 'new'),
	(98, '2016-11-24', '2016-11-26', 2, 'lux', NULL, 19, NULL, 'new'),
	(99, '2016-11-30', '2016-12-03', 3, 'lux', NULL, 19, NULL, 'new');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;


-- Дамп структуры для таблица booking.room
CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(10) unsigned NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `place` int(10) unsigned DEFAULT NULL,
  `price` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп данных таблицы booking.room: ~24 rows (приблизительно)
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` (`room_id`, `category`, `place`, `price`) VALUES
	(1, 'standard', 1, 50),
	(2, 'standard', 1, 50),
	(3, 'standard', 1, 50),
	(4, 'standard', 2, 75),
	(5, 'standard', 2, 75),
	(6, 'standard', 2, 75),
	(7, 'standard', 3, 110),
	(8, 'standard', 3, 110),
	(9, 'standard', 3, 110),
	(10, 'standard', 4, 140),
	(11, 'standard', 4, 140),
	(12, 'standard', 4, 140),
	(13, 'lux', 1, 70),
	(14, 'lux', 1, 70),
	(15, 'lux', 1, 70),
	(16, 'lux', 2, 100),
	(17, 'lux', 2, 100),
	(18, 'lux', 2, 100),
	(19, 'lux', 3, 130),
	(20, 'lux', 3, 130),
	(21, 'lux', 3, 130),
	(22, 'lux', 4, 160),
	(23, 'lux', 4, 160),
	(24, 'lux', 4, 160);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;


-- Дамп структуры для таблица booking.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'client',
  `login` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(355) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп данных таблицы booking.user: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `user_role`, `login`, `password`) VALUES
	(17, 'Админ', 'Админов', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
	(19, 'Юзер', 'Юзеров', 'client', 'user', 'ee11cbb19052e40b07aac0ca060c23ee'),
	(20, 'user2', 'user2', 'client', 'user2', '7e58d63b60197ceb55a1c487989a3720');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
