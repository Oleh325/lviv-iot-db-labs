USE yatskiv;

DROP PROCEDURE IF EXISTS person_insertion;
DROP PROCEDURE IF EXISTS city_person;
DROP PROCEDURE IF EXISTS person_add_nonames;
DROP PROCEDURE IF EXISTS select_avg_car_price;
DROP PROCEDURE IF EXISTS car_cursor_procedure;
DROP FUNCTION IF EXISTS get_avg_car_price;

# Task 2a
# Забезпечити параметризовану вставку нових значень у довільну таблицю.
DELIMITER //
CREATE PROCEDURE person_insertion(
    IN city_id INT,
    IN email VARCHAR(50),
    IN middlename VARCHAR(45),
    IN name VARCHAR(45),
    IN phone_number VARCHAR(13),
    IN surname VARCHAR(45),
    OUT person_id INT)
BEGIN
    INSERT INTO person VALUES (null, city_id, email, middlename, name, phone_number, surname);
    SELECT LAST_INSERT_ID() INTO person_id;
END //
DELIMITER ;

# Task 2b
# Забезпечити реалізацію зв’язку М:М між 2ма таблицями, тобто вставити в стикувальну таблицю відповідну стрічку
# за реально-існуючими значеннями (напр. surname, name) в цих основних таблицях.
DELIMITER //
CREATE PROCEDURE city_person(
    IN city_id_ INT,
    IN person_id_ INT,
    OUT city_person_id_ INT)
BEGIN
    DECLARE person_city_id INT;
    IF (city_id_ NOT IN (SELECT id FROM city)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such city';
    END IF;
    IF (person_id_ NOT IN (SELECT id FROM person)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such person';
    END IF;
    SET person_city_id = (SELECT city_id FROM person WHERE id = person_id_);
    IF city_id_ != person_city_id THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This person doesn\'t live in this city';
    END IF;
    CREATE TABLE IF NOT EXISTS `city_person` (
        `city_id` int NOT NULL,
        `person_id` int NOT NULL,
        PRIMARY KEY (`city_id`, person_id)
    );
    INSERT INTO city_person VALUES (city_id_, person_id_);
    SELECT LAST_INSERT_ID() INTO city_person_id_;
END //
DELIMITER ;

# Task 2c
# Створити пакет, який вставляє 10 стрічок у довільну таблицю БД у форматі <Noname+№>
# наприклад: Noname5, Noname6, Noname7 і т.д.
DELIMITER //
CREATE PROCEDURE person_add_nonames()
BEGIN
    DECLARE n INT;
    SET n = 1;
    label1: WHILE n < 11 DO
        INSERT INTO person VALUES (null, 1, '', '', CONCAT('Noname', n), '', '');
        SET n = n + 1;
    END WHILE label1;
END //
DELIMITER ;

# Task 2d
# Написати користувацьку функцію, яка буде шукати Max, Min, Sum чи Avg для стовпця довільної таблиці у БД.
# Написати процедуру, яка буде у SELECT викликати цю функцію.
DELIMITER //
CREATE FUNCTION get_avg_car_price()
    RETURNS double
    DETERMINISTIC
BEGIN
    DECLARE avg double;
    SET avg = (SELECT AVG(rent_cost_per_day_usd) FROM car);
    RETURN avg;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE select_avg_car_price(
    OUT avg double)
BEGIN
    SET avg = get_avg_car_price();
END //
DELIMITER ;

# Task 2e
# Використовуючи курсор, забезпечити динамічне створення таблиць з назвами+штамп часу, взятими зі стовпця з довільної
# таблиці БД, з випадковою кількістю стовпців (від 1 до 9). Імена та тип стовпців довільні.
DELIMITER //
CREATE PROCEDURE car_cursor_procedure()
BEGIN
    DECLARE done int DEFAULT false;
    DECLARE model_ varchar(45);
    DECLARE n int;
    DECLARE name varchar(45);

    DECLARE model_cursor CURSOR
        FOR SELECT model FROM car;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    SET n = 1;
    OPEN model_cursor;
    myLoop: LOOP
        FETCH model_cursor INTO model_;
        IF done=true THEN LEAVE myLoop;
        END IF;
        SET name = CONCAT(model_, NOW());
        SET @first=CONCAT('DROP TABLE IF EXISTS `', name, '`;');
        SET @second=CONCAT('CREATE TABLE `', name, '` (`id` int NOT NULL AUTO_INCREMENT, `some_field` int NOT NULL, PRIMARY KEY (`id`));');
        SET @third=CONCAT('INSERT INTO `', name, '` VALUES (null, ', n, ');');
        PREPARE my_query1 FROM @first;
        EXECUTE my_query1;
        DEALLOCATE PREPARE my_query1;
        PREPARE my_query2 FROM @second;
        EXECUTE my_query2;
        DEALLOCATE PREPARE my_query2;
        PREPARE my_query3 FROM @third;
        EXECUTE my_query3;
        DEALLOCATE PREPARE my_query3;
        SET n = n + 1;
    END LOOP;
    CLOSE model_cursor;
END //
DELIMITER ;

