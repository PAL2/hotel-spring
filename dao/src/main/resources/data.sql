INSERT INTO room VALUES
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

INSERT INTO account VALUES
	(1, 150),
	(2, 700),
	(3, 80);

INSERT INTO user VALUES
	(1, 'Админ', 'Админов', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3'),
	(2, 'Юзер', 'Юзеров', 'client', 'user', 'ee11cbb19052e40b07aac0ca060c23ee');

INSERT INTO booking VALUES
	(2, '2017-06-15', '2017-06-22', 2, 'lux', 40, 2, 2, 'paid'),
	(3, '2017-06-21', '2017-06-22', 2, 'standard', 20, 2, 3, 'billed'),
	(4, '2017-06-21', '2017-06-28', 1, 'standard', NULL, 2, NULL, 'rejected'),
	(5, '2017-06-29', '2017-06-30', 2, 'lux', NULL, 2, NULL, 'new');