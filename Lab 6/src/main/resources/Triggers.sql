USE yatskiv;

DROP TRIGGER IF EXISTS after_insert_person;
DROP TRIGGER IF EXISTS after_update_person;
DROP TRIGGER IF EXISTS after_update_city;
DROP TRIGGER IF EXISTS after_delete_city;
DROP TRIGGER IF EXISTS after_delete_city_log;
DROP TRIGGER IF EXISTS after_update_city_log;
DROP TRIGGER IF EXISTS before_insert_driver;

# Task 1
# Додати до БД 1 додаткову довільну таблицю і зв’язати з іншою існуючою таблицею зв’язком  1:M.
# Однак для забезпечення цілісності значень використати тригери замість фізичного зовнішнього ключа.
DELIMITER //
CREATE TRIGGER after_insert_person
    AFTER INSERT
    ON person
    FOR EACH ROW
BEGIN
    IF (NEW.city_id NOT IN (SELECT id FROM city)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such city for this person';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_update_person
    AFTER UPDATE
    ON person
    FOR EACH ROW
BEGIN
    IF (NEW.city_id NOT IN (SELECT id FROM city)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s no such city for this person';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_update_city
    AFTER UPDATE
    ON city
    FOR EACH ROW
BEGIN
    IF (OLD.id IN (SELECT city_id FROM person)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s people in this city';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_delete_city
    AFTER DELETE
    ON city
    FOR EACH ROW
BEGIN
    IF (OLD.id IN (SELECT city_id FROM person)) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'There\'s people in this city';
    END IF;
END //
DELIMITER ;

# Task 3
# Створити таблицю-журнал, в якій вести логи зі штампом часу при видаленні даних для певної таблиці
DELIMITER //
CREATE TRIGGER after_delete_city_log
    AFTER DELETE
    ON city
    FOR EACH ROW
BEGIN
    INSERT INTO city_log (city_id, new_name, old_name, action, timestamp) VALUES (OLD.id, null, OLD.name, 'delete', NOW());
END //
DELIMITER ;

# Створити таблицю-журнал, в якій вести логи зі штампом часу при модифікації даних для таблиці
DELIMITER //
CREATE TRIGGER after_update_city_log
    AFTER UPDATE
    ON city
    FOR EACH ROW
BEGIN
    INSERT INTO city_log (city_id, new_name, old_name, action, timestamp) VALUES (OLD.id, NEW.name, OLD.name, 'update', NOW());
END //
DELIMITER ;

# Для певного стовпця допускається ввід лише таких імен: 'Svitlana', 'Petro', 'Olha', 'Taras'.
DELIMITER //
CREATE TRIGGER before_insert_driver
    BEFORE INSERT
    ON driver
    FOR EACH ROW
BEGIN
    IF (NEW.name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Can\'t insert this name. Only Svitlana, Petro, Olha, Taras are allowed';
    END IF;
 END //
DELIMITER ;
