-- Дамп структуры базы данных bookings
CREATE DATABASE IF NOT EXISTS `bookings`;
USE `bookings`;

-- Дамп структуры для таблица bookings.account
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `summa` int(11) unsigned NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп структуры для таблица bookings.room
CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(10) unsigned NOT NULL,
  `category` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `place` int(10) unsigned DEFAULT NULL,
  `price` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп структуры для таблица bookings.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` varchar(6) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'client',
  `login` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп структуры для таблица bookings.booking
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
  KEY `User` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Дамп данных таблицы bookings.account: ~3 rows (приблизительно)
INSERT INTO `account` (`account_id`, `summa`) VALUES
	(1, 150),
	(2, 700),
	(3, 80);

-- Дамп данных таблицы bookings.bookings: ~4 rows (приблизительно)
INSERT INTO `booking` (`booking_id`, `start_date`, `end_date`, `place`, `category`, `room_id`, `user_id`, `account_id`, `status`) VALUES
	(2, '2017-06-15', '2017-06-22', 2, 'lux', 40, 2, 2, 'paid'),
	(3, '2017-06-21', '2017-06-22', 2, 'standard', 20, 2, 3, 'billed'),
	(4, '2017-06-21', '2017-06-28', 1, 'standard', NULL, 2, NULL, 'rejected'),
	(5, '2017-06-29', '2017-06-30', 2, 'lux', NULL, 2, NULL, 'new');

-- Дамп данных таблицы bookings.room: ~40 rows (приблизительно)
INSERT INTO `room` (`room_id`, `category`, `place`, `price`) VALUES
	(1, 'standard', 1, 50),
	(2, 'standard', 1, 50),
	(3, 'standard', 1, 50),
	(4, 'standard', 1, 50),
	(5, 'standard', 1, 50),
	(6, 'standard', 1, 50),
	(7, 'standard', 1, 50),
	(8, 'standard', 1, 50),
	(9, 'standard', 1, 50),
	(10, 'standard', 1, 50),
	(11, 'standard', 2, 80),
	(12, 'standard', 2, 80),
	(13, 'standard', 2, 80),
	(14, 'standard', 2, 80),
	(15, 'standard', 2, 80),
	(16, 'standard', 2, 80),
	(17, 'standard', 2, 80),
	(18, 'standard', 2, 80),
	(19, 'standard', 2, 80),
	(20, 'standard', 2, 80),
	(21, 'lux', 1, 70),
	(22, 'lux', 1, 70),
	(23, 'lux', 1, 70),
	(24, 'lux', 1, 70),
	(25, 'lux', 1, 70),
	(26, 'lux', 1, 70),
	(27, 'lux', 1, 70),
	(28, 'lux', 1, 70),
	(29, 'lux', 1, 70),
	(30, 'lux', 1, 70),
	(31, 'lux', 2, 100),
	(32, 'lux', 2, 100),
	(33, 'lux', 2, 100),
	(34, 'lux', 2, 100),
	(35, 'lux', 2, 100),
	(36, 'lux', 2, 100),
	(37, 'lux', 2, 100),
	(38, 'lux', 2, 100),
	(39, 'lux', 2, 100),
	(40, 'lux', 2, 100);

-- Дамп данных таблицы bookings.user: ~2 rows (приблизительно)
INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `user_role`, `login`, `password`) VALUES
	(1, 'Админ', 'Админов', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
	(2, 'Юзер', 'Юзеров', 'client', 'user', 'ee11cbb19052e40b07aac0ca060c23ee');