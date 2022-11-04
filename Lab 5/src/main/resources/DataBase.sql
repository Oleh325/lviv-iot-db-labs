CREATE DATABASE IF NOT EXISTS yatskiv;
USE yatskiv;


DROP TABLE IF EXISTS `rent`;
DROP TABLE IF EXISTS `car`;
DROP TABLE IF EXISTS `parking`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `country`;
DROP TABLE IF EXISTS `fine`;
DROP TABLE IF EXISTS `driver`;
DROP TABLE IF EXISTS `transaction`;


CREATE TABLE `country` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE `city` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `country_id` int NOT NULL,
    PRIMARY KEY (`id`,`country_id`),
    KEY `fk_city_country1_idx` (`country_id`),
    CONSTRAINT `fk_city_country1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `parking` (
    `id` int NOT NULL AUTO_INCREMENT,
    `location` varchar(45) NOT NULL,
    `type` varchar(45) DEFAULT NULL,
    `city_id` int NOT NULL,
    PRIMARY KEY (`id`,`city_id`),
    KEY `fk_parking_city1_idx` (`city_id`),
    CONSTRAINT `fk_parking_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `car` (
    `id` int NOT NULL AUTO_INCREMENT,
    `model` varchar(45) NOT NULL,
    `color` varchar(45) DEFAULT NULL,
    `transmission_type` enum('manual','automatic') DEFAULT NULL,
    `seats_count` int NOT NULL,
    `has_ac` tinyint(1) DEFAULT NULL,
    `baggage_capacity_kg` float NOT NULL,
    `rent_cost_per_day_usd` float NOT NULL,
    `fuel_type` enum('gas','petrol','diesel','electric','other') NOT NULL,
    `additional_info` varchar(200) DEFAULT NULL,
    `parking_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_car_parking_idx` (`parking_id`)
) ENGINE=InnoDB;


CREATE TABLE `driver` (
    `license_number` varchar(15) NOT NULL,
    `name` varchar(50) NOT NULL,
    `surname` varchar(50) NOT NULL,
    `middlename` varchar(50) NOT NULL,
    `email` varchar(60) DEFAULT NULL,
    `phone_number` varchar(13) NOT NULL,
    PRIMARY KEY (`license_number`)
) ENGINE=InnoDB;


CREATE TABLE `fine` (
    `id` int NOT NULL AUTO_INCREMENT,
    `violation_type` varchar(45) NOT NULL,
    `driver_license_number` varchar(15) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_fine_driver1_idx` (`driver_license_number`),
    CONSTRAINT `fk_fine_driver1` FOREIGN KEY (`driver_license_number`) REFERENCES `driver` (`license_number`)
) ENGINE=InnoDB;


CREATE TABLE `transaction` (
    `id` varchar(50) NOT NULL,
    `total_usd` float NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE `rent` (
    `id` int NOT NULL AUTO_INCREMENT,
    `date_of_rent` datetime NOT NULL,
    `end_date_of_rent` datetime NOT NULL,
    `payment_type` enum('debit_card','cash','crypto') NOT NULL,
    `transaction_id` varchar(50) NOT NULL,
    `car_id` int NOT NULL,
    `driver_license_number` varchar(15) NOT NULL,
    PRIMARY KEY (`id`,`transaction_id`),
    KEY `fk_rent_transaction1_idx` (`transaction_id`),
    KEY `fk_rent_car1_idx` (`car_id`),
    KEY `fk_rent_driver1_idx` (`driver_license_number`),
    CONSTRAINT `fk_rent_car1` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
    CONSTRAINT `fk_rent_driver1` FOREIGN KEY (`driver_license_number`) REFERENCES `driver` (`license_number`),
    CONSTRAINT `fk_rent_transaction1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB;


INSERT INTO `country` VALUES
    (1,'Ukraine'),(2,'Canada'),
    (3,'USA'),(4,'UK'),
    (5,'Brazil'),(6,'Japan'),
    (7,'Germany'),(8,'Italy'),
    (9,'Spain'),(10,'Poland');


INSERT INTO `city` VALUES
    (1,'Lviv',1),(2,'Kyiv',1),
    (3,'Toronto',2),(4,'San Francisco',3),
    (5,'London',4),(6,'Sao Paulo',5),
    (7,'Kyoto',6),(8,'Frankfurt',7),
    (9,'Milan',8),(10,'Alicante',9),
    (11,'Warsaw',10);


INSERT INTO `parking` VALUES
    (1,'Bandery street 69',NULL,1),(2,'Shevchenka street 12',NULL,2),
    (3,'Isabella street 12','underground',3),(4,'Treat avenue 7',NULL,4),
    (5,'Chambers street 21',NULL,5),(6,'Brownswood road 3',NULL,5),
    (7,'Clelia street 325',NULL,6),(8,'Yamada street 8','underground',7),
    (9,'Zentmarkweg street 11',NULL,8),(10,'Plinio street 13','underground',9),
    (11,'Foguerer street 44',NULL,10),(12,'Mokotowska street 123',NULL,11);


INSERT INTO `car` VALUES
    (1,'Skoda Octavia','gray','manual',4,0,70,80,'gas',NULL,1),
    (2,'Volkswagen Golf','blue','automatic',4,0,80,90,'petrol',NULL,1),
    (3,'Bugatti Veyron','yellow','automatic',2,1,60,400,'petrol',NULL,1),
    (4,'Mercedes Benz','black','manual',4,1,55,110,'petrol',NULL,2),
    (5,'Nissan Pathfinder','gray','automatic',5,1,40,120,'petrol','There\'s some baggage in the back that should not be taken or displaced, simply put your thing alongside those ones.',4),
    (6,'Ford Maverick','white','manual',5,1,69.5,100,'diesel',NULL,5),
    (7,'Daewoo Lanos','black','manual',4,0,77,60,'gas',NULL,7),
    (8,'Nissan Pathfinder','red','automatic',5,1,80,120,'petrol','Hit the "A/C ON" button with a fist to make it work.',9),
    (9,'Daewoo Lanos','green','manual',4,0,77,60,'gas',NULL,10),
    (10,'Daewoo Lanos','black','manual',4,1,77,60,'gas',NULL,11);


INSERT INTO `driver` VALUES
    ('AK66325','Yatskiv','Oleh','Olehovych','planespotter325@gmail.com','+380631214697'),
    ('BA12735','Amar','Ahmood','Alahabdazi','agmodarab@gmail.com','+457105820593'),
    ('BB66617','Laba','Bohdan','Myroslavovych','blaba@ukr.net','+380663408904'),
    ('BK74010','Zelenskyi','Volodymyr','Oleksandrovych','zelenskyi@ukr.net','+380663457348'),
    ('CB17569','Kolesnyk','Yana','Andriivna','ykolesnyk44@gmail.com','+380674864876'),
    ('CE14944','Pavelchak','Andrii','Antonovych','pavelchuka@gmail.com','+380633256944'),
    ('EI82029','Habo','Escobar','Enpules','haboes13@gmail.com','+226534667865'),
    ('HT82060','Pavlyk','Vadym','Olehovych','vplviv13@gmail.com','+380963454355'),
    ('MP47297','Melnyk','Ivan','Orestovych','melnyki@gmail.com','+380994594395'),
    ('XC11819','Telko','Nadiia','Romanivna','nohope69@gmail.com','+380663256944');


INSERT INTO `fine` VALUES
    (1,'speeding','BB66617'),(2,'speeding','EI82029'),
    (3,'DUI','BB66617'),(4,'speeding','BB66617'),
    (5,'speeding','CE14944'),(6,'speeding','CE14944'),
    (7,'speeding','MP47297'),(8,'speeding','AK66325'),
    (9,'DUI','EI82029'),(10,'speeding','CB17569');


INSERT INTO `transaction` VALUES
    ('AK2114',190),('BD3257',300),
    ('DI2254',700),('MS4729',170),
    ('NA4444',400),('QU6969',150),
    ('RU1139',325),('TA6834',440),
    ('UA1234',200),('YK1217',360);


INSERT INTO `rent` VALUES
    (1,'2022-09-09 13:52:11','2022-09-11 14:53:00','cash','AK2114',1,'BA12735'),
    (2,'2022-09-10 08:05:23','2022-09-11 08:05:23','debit_card','YK1217',2,'CE14944'),
    (3,'2022-09-10 16:30:11','2022-09-12 11:20:32','debit_card','BD3257',7,'MP47297'),
    (4,'2022-09-10 17:13:49','2022-09-12 17:13:49','cash','UA1234',3,'EI82029'),
    (5,'2022-09-10 18:20:03','2022-09-11 20:45:44','debit_card','DI2254',6,'BK74010'),
    (6,'2022-09-10 19:02:57','2022-09-13 19:02:57','debit_card','TA6834',1,'HT82060'),
    (7,'2022-09-11 08:30:02','2022-09-13 09:20:00','debit_card','MS4729',7,'XC11819'),
    (8,'2022-09-11 09:16:37','2022-09-13 09:16:37','crypto','RU1139',9,'AK66325'),
    (9,'2022-09-11 11:56:09','2022-09-12 11:56:09','cash','NA4444',10,'CB17569'),
    (10,'2022-09-11 14:26:17','2022-09-14 15:36:49','debit_card','QU6969',2,'CE14944');


CREATE INDEX car_index ON car(model, transmission_type, seats_count, rent_cost_per_day_usd, fuel_type);
CREATE INDEX city_index ON city(name);
CREATE INDEX country_index ON country(name);
CREATE INDEX driver_index ON driver(license_number, name, surname, phone_number);
CREATE INDEX fine_index ON fine(violation_type, driver_license_number);
CREATE INDEX parking_index ON parking(location, city_id);
CREATE INDEX rent_index ON rent(date_of_rent, end_date_of_rent, transaction_id, car_id, driver_license_number);
CREATE INDEX transaction_index ON transaction(total_usd);