CREATE TABLE IF NOT EXISTS account (
  account_id INTEGER NOT NULL IDENTITY,
  summa      INTEGER NOT NULL,
  PRIMARY KEY (account_id)
);

CREATE TABLE IF NOT EXISTS room (
  room_id  INTEGER NOT NULL,
  category VARCHAR(50) DEFAULT NULL,
  place    INTEGER     DEFAULT NULL,
  price    INTEGER     DEFAULT NULL,
  PRIMARY KEY (room_id)
);

CREATE TABLE IF NOT EXISTS user (
  user_id    INTEGER     NOT NULL IDENTITY,
  first_name VARCHAR(40) NOT NULL,
  last_name  VARCHAR(40) NOT NULL,
  user_role  VARCHAR(6)  NOT NULL,
  login      VARCHAR(40) NOT NULL,
  password   VARCHAR(40) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS booking (
  booking_id INTEGER NOT NULL IDENTITY ,
  start_date DATE             DEFAULT NULL,
  end_date   DATE             DEFAULT NULL,
  place      INTEGER          DEFAULT NULL,
  category   VARCHAR(50)      DEFAULT NULL,
  room_id    INTEGER          DEFAULT NULL,
  user_id    INTEGER         DEFAULT NULL,
  account_id INTEGER         DEFAULT NULL,
  status     VARCHAR(50)      DEFAULT NULL,
  PRIMARY KEY (booking_id),
  FOREIGN KEY (account_id) REFERENCES account(account_id),
  FOREIGN KEY (room_id) REFERENCES room(room_id),
  FOREIGN KEY (user_id) REFERENCES user(user_id)
);