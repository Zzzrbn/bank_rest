CREATE TABLE bankcards (
  id int NOT NULL AUTO_INCREMENT,
  number varchar(16),
  holder varchar(25),
  expdate date,
  status ENUM('ACTIVE', 'BLOCKED', 'EXPIRED'),
  balance int,
  PRIMARY KEY (id)
);


INSERT INTO my_db.bankcards (id, number, holder, expdate, status, balance)
VALUES
    (1,'1234567890001234', 'Zarubin', '2025-12-31', 'ACTIVE', 500),
    (2,'1234567890001235', 'Zarubin', '2025-11-30', 'ACTIVE', 1500),
    (3,'1234567890001236', 'Ivanov', '2025-11-30', 'ACTIVE', 1500),
    (4,'1234567890001237', 'Ivanov', '2025-11-30', 'BLOCKED', 1500),
    (5,'1234567890001238', 'Petrov', '2025-10-31', 'EXPIRED', 2500),
    (6,'1234567890001239', 'Petrov', '2025-10-31', 'EXPIRED', 3500);