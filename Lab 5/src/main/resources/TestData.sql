USE yatskiv;

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
    (1,NULL,70,'gray','gas',0,'Skoda Octavia',80,4,'manual',1),
    (2,NULL,80,'blue','petrol',0,'Volkswagen Golf',90,4,'automatic',1),
    (3,NULL,60,'yellow','petrol',1,'Bugatti Veyron',400,2,'automatic',1),
    (4,NULL,55,'black','petrol',1,'Mercedes Benz',110,4,'manual',2),
    (5,'There\'s some baggage in the back that should not be taken or displaced, simply put your thing alongside those ones.',40,'gray','petrol',1,'Nissan Pathfinder',120,5,'automatic',4),
    (6,NULL,69.5,'white','diesel',1,'Ford Maverick',100,5,'manual',5),
    (7,NULL,77,'black','gas',0,'Daewoo Lanos',60,4,'manual',7),
    (8,'Hit the "A/C ON" button with a fist to make it work.',80,'red','petrol',1,'Nissan Pathfinder',120,5,'automatic',9),
    (9,NULL,77,'green','gas',0,'Daewoo Lanos',60,4,'manual',10),
    (10,NULL,77,'black','gas',1,'Daewoo Lanos',60,4,'manual',11);


INSERT INTO `driver` VALUES
    ('AK66325','planespotter325@gmail.com','Olehovych','Oleh','+380631214697','Yatskiv'),
    ('BA12735','agmodarab@gmail.com','Alahabdazi','Ahmood','+457105820593','Amar'),
    ('BB66617','blaba@ukr.net','Myroslavovych','Bohdan','+380663408904','Laba'),
    ('BK74010','zelenskyi@ukr.net','Oleksandrovych','Volodymyr','+380663457348','Zelenskyi'),
    ('CB17569','ykolesnyk44@gmail.com','Andriivna','Yana','+380674864876','Kolesnyk'),
    ('CE14944','pavelchuka@gmail.com','Antonovych','Andrii','+380633256944','Pavelchak'),
    ('EI82029','haboes13@gmail.com','Enpules','Escobar','+226534667865','Habo'),
    ('HT82060','vplviv13@gmail.com','Olehovych','Vadym','+380963454355','Pavlyk'),
    ('MP47297','melnyki@gmail.com','Orestovych','Ivan','+380994594395','Melnyk'),
    ('XC11819','nohope69@gmail.com','Romanivna','Nadiia','+380663256944','Telko');


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
    (1,'2022-09-09 13:52:11','2022-09-11 14:53:00','cash',1,'BA12735','AK2114'),
    (2,'2022-09-10 08:05:23','2022-09-11 08:05:23','debit_card',2,'CE14944','YK1217'),
    (3,'2022-09-10 16:30:11','2022-09-12 11:20:32','debit_card',7,'MP47297','BD3257'),
    (4,'2022-09-10 17:13:49','2022-09-12 17:13:49','cash',3,'EI82029','UA1234'),
    (5,'2022-09-10 18:20:03','2022-09-11 20:45:44','debit_card',6,'BK74010','DI2254'),
    (6,'2022-09-10 19:02:57','2022-09-13 19:02:57','debit_card',1,'HT82060','TA6834'),
    (7,'2022-09-11 08:30:02','2022-09-13 09:20:00','debit_card',7,'XC11819','MS4729'),
    (8,'2022-09-11 09:16:37','2022-09-13 09:16:37','crypto',9,'AK66325','RU1139'),
    (9,'2022-09-11 11:56:09','2022-09-12 11:56:09','cash',10,'CB17569','NA4444'),
    (10,'2022-09-11 14:26:17','2022-09-14 15:36:49','debit_card',2,'CE14944','QU6969');


CREATE INDEX car_index ON car(model, transmission_type, seats_count, rent_cost_per_day_usd, fuel_type);
CREATE INDEX city_index ON city(name);
CREATE INDEX country_index ON country(name);
CREATE INDEX driver_index ON driver(license_number, name, surname, phone_number);
CREATE INDEX fine_index ON fine(violation_type, driver_license_number);
CREATE INDEX parking_index ON parking(location, city_id);
CREATE INDEX rent_index ON rent(date_of_rent, end_date_of_rent, transaction_id, car_id, driver_license_number);
CREATE INDEX transaction_index ON transaction(total_usd);