DROP SCHEMA IF EXISTS wishywishy;
CREATE SCHEMA wishywishy;
USE wishywishy;

CREATE TABLE users
(
    username VARCHAR(45) NOT NULL PRIMARY KEY,
    passcode VARCHAR(45) NOT NULL
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
    URL VARCHAR(45),
    wishPrice DOUBLE,
    wishlistId INT,
    FOREIGN KEY (wishlistId) REFERENCES wishlist(wishlistId)
);