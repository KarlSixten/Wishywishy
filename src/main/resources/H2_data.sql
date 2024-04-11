DROP SCHEMA IF EXISTS wishywishy;
CREATE SCHEMA wishywishy;
USE wishywishy;

CREATE TABLE users
(
    username VARCHAR(45) NOT NULL PRIMARY KEY,
    password VARCHAR(45) NOT NULL
);

CREATE TABLE wishlist
(
    wishlistId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(45),
    wishlistName VARCHAR(45),
    FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE wish
(
    wishId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    wishName VARCHAR(45),
    URL VARCHAR(255) NULL,
    wishPrice DOUBLE,
    wishlistId INT,
    FOREIGN KEY (wishlistId) REFERENCES wishlist(wishlistId)
);

INSERT into users (username, password) values ('gustavSo', 'Diller123');
INSERT into users (username, password) values (1,1);

insert into wishlist (username, wishlistName) values ('gustavSo', 'liste 1');
insert into wishlist (username, wishlistName) values ('gustavSo', 'liste 2');
insert into wish (wishname, URL, wishprice, WISHLISTID) values ('parfume', 'parfume.dk', 300, 1);
insert into wish (wishname, URL, wishprice, WISHLISTID) values ('parfume', 'parfume.dk', 900, 1);