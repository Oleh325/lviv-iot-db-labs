CREATE SCHEMA IF NOT EXISTS db_jdbc_template;
USE `db_jdbc_template`;

DROP TABLE IF EXISTS person_book;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS city;


CREATE TABLE city
(
    city VARCHAR(25) PRIMARY KEY
);

CREATE TABLE person
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    surname VARCHAR(25) NOT NULL,
    name    VARCHAR(25) NOT NULL,
    city    VARCHAR(25) NOT NULL,
    email   VARCHAR(45) NULL,
    CONSTRAINT FOREIGN KEY (city) REFERENCES city (city)
);

CREATE TABLE book
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(45) NOT NULL,
    author    VARCHAR(45) NOT NULL,
    amount    INT         NOT NULL
);

CREATE TABLE person_book
(
    person_id INT NOT NULL,
    book_id   INT NOT NULL,
    PRIMARY KEY (person_id, book_id),
    CONSTRAINT FOREIGN KEY (person_id) REFERENCES person (id),
    CONSTRAINT FOREIGN KEY (book_id) REFERENCES book (id)
);

INSERT INTO `book`
VALUES (1, 'Bible', 'St. mans', 5),
       (2, 'Kobzar', 'Shevchenko ', 4),
       (3, 'Harry Potter', 'J. K. Rowling', 1),
       (4, 'Zakhar Berkut', 'I. Franko', 2),
       (5, 'The Jungle Book', 'Rudyard Kipling', 1);

INSERT INTO `city`
VALUES ('Herson'),
       ('Kyiv'),
       ('Lviv'),
       ('Poltava'),
       ('Ternopil');

INSERT INTO `person`
VALUES (1, 'Koldovskyy', 'Vyacheslav', 'Lviv', 'koldovsky@gmail.com'),
       (2, 'Pavelchak', 'Andrii', 'Poltava', 'apavelchak@gmail.com'),
       (3, 'Soluk', 'Andrian', 'Herson', 'andriansoluk@gmail.com'),
       (4, 'Dubyniak', 'Bohdan', 'Ternopil', 'bohdan.dub@gmail.com'),
       (5, 'Faryna', 'Igor', 'Kyiv', 'farynaihor@gmail.com'),
       (6, 'Kurylo', 'Volodymyr', 'Poltava', 'kurylo.volodymyr@gmail.com'),
       (7, 'Matskiv', 'Marian', 'Herson', 'marian3912788@gmail.com'),
       (8, 'Shyika', 'Tamara', 'Kyiv', 'tamara.shyika@gmail.com'),
       (9, 'Tkachyk', 'Volodymyr', 'Ternopil', 'vova1234.tkachik@gmail.com');

INSERT INTO `person_book`
VALUES (4, 1),
       (5, 1),
       (8, 1),
       (2, 2),
       (6, 2),
       (7, 2),
       (1, 3),
       (1, 4),
       (9, 4),
       (3, 5);


# =====================================================================================

DELIMITER //
CREATE PROCEDURE InsertPersonBook(
    IN SurnamePersonIn varchar(25),
    IN BookNameIN varchar(45),
    OUT msg varchar(40)
)
BEGIN
    -- checks for present Surname
    IF NOT EXISTS(SELECT * FROM person WHERE surname = SurnamePersonIn)
    THEN
        SET msg = 'This Surname is absent';

        -- checks for present Book
    ELSEIF NOT EXISTS(SELECT * FROM book WHERE book_name = BookNameIN)
    THEN
        SET msg = 'This Book is absent';

        -- checks if there are this combination already
    ELSEIF EXISTS(SELECT *
                  FROM person_book
                  WHERE person_id = (SELECT id FROM person WHERE surname = SurnamePersonIn)
                    AND book_id = (SELECT id FROM book WHERE book_name = BookNameIN)
        )
    THEN
        SET msg = 'This Person already has this book';

        -- checks whether there is still such a book
    ELSEIF (SELECT amount FROM book WHERE book_name = BookNameIN)
        <= (SELECT COUNT(*) FROM person_book WHERE book_id = (SELECT id FROM book WHERE book_name = BookNameIN))
    THEN
        SET msg = 'There are no this Book already';

        -- makes insert
    ELSE
        INSERT person_book (person_id, book_id)
            Value ((SELECT id FROM person WHERE surname = SurnamePersonIn),
                   (SELECT id FROM Book WHERE book_name = BookNameIN));
        SET msg = 'OK';

    END IF;

END //
DELIMITER ;
