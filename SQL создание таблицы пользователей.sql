USE my_db;

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  password varchar(100),
  role varchar(15),
  PRIMARY KEY (id)
) ;

INSERT INTO my_db.users (id, name, password, role)
VALUES
    (1, 'Zarubin', '$2y$05$RZUyjJBgFd3xN19k4in5D.zpAlrOVOd9t7VALUzyBJE8POwuVuSXu', 'ROLE_ADMIN'),
    (2, 'Ivanov', '$2y$05$14yPmv7l1noycewcczxfAOAyIoOf2XzqCaxbQAxn1xHfmQIt2qQ92', 'ROLE_USER'),
    (3, 'Petrov', '$2y$05$bap6h.hrKr6/AFlIvu1BW.SCZtt1ynnosYDF9gCscmxWt/drF4GtO', 'ROLE_USER');
    
