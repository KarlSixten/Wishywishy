DROP TABLE IF EXISTS wish;
DROP TABLE IF EXISTS wishlist;
DROP TABLE IF EXISTS users;

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
    isReserved BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (wishlistId) REFERENCES wishlist(wishlistId)
);

-- Inserting users
INSERT INTO users (username, password) VALUES ('gustavSo', 'Diller123');
INSERT INTO users (username, password) VALUES ('testUser', 'Test123');
INSERT INTO users (username, password) VALUES ('alice123', 'AlicePass');
INSERT INTO users (username, password) VALUES ('bob456', 'BobPass');

-- Inserting wishlists for Gustav's wishlists
INSERT INTO wishlist (username, wishlistName) VALUES ('gustavSo', 'Gustav Birthday Wishes');
INSERT INTO wishlist (username, wishlistName) VALUES ('gustavSo', 'Gustav Christmas List');
INSERT INTO wishlist (username, wishlistName) VALUES ('gustavSo', 'Gustav Travel Bucket List');

-- Inserting wishlists for Test User's wishlists
INSERT INTO wishlist (username, wishlistName) VALUES ('testUser', 'Test User Wedding Registry');
INSERT INTO wishlist (username, wishlistName) VALUES ('testUser', 'Test User Summer Essentials');

-- Inserting wishlists for Alice's wishlists
INSERT INTO wishlist (username, wishlistName) VALUES ('alice123', 'Alice Book Wishlist');
INSERT INTO wishlist (username, wishlistName) VALUES ('alice123', 'Alice Home Decor Dreams');
INSERT INTO wishlist (username, wishlistName) VALUES ('alice123', 'Alice Adventure Gear');

-- Inserting wishlists for Bob's wishlists
INSERT INTO wishlist (username, wishlistName) VALUES ('bob456', 'Bob Gaming Wishlist');
INSERT INTO wishlist (username, wishlistName) VALUES ('bob456', 'Bob Fitness Goals');
INSERT INTO wishlist (username, wishlistName) VALUES ('bob456', 'Bob DIY Projects');

-- Inserting wishes for Gustav's Birthday Wishes
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Watch', 'example.com/watch', 200, 1, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Headphones', 'example.com/headphones', 100, 1, true);

-- Inserting wishes for Gustav's Christmas List
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Sweater', 'example.com/sweater', 50, 2, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Book', 'example.com/book', 20, 2, false);

-- Inserting wishes for Gustav's Travel Bucket List
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Backpack', 'example.com/backpack', 100, 3, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Travel Guide', 'example.com/travel-guide', 30, 3, false);

-- Inserting wishes for Test User's Wedding Registry
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Dinnerware Set', 'example.com/dinnerware', 150, 4, true);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Blender', 'example.com/blender', 80, 4, false);

-- Inserting wishes for Test User's Summer Essentials
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Beach Towel', 'example.com/beach-towel', 20, 5, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Sunglasses', 'example.com/sunglasses', 50, 5, false);

-- Inserting wishes for Alice's Book Wishlist
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Fiction Novel', 'example.com/fiction-novel', 25, 6, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Non-Fiction Book', 'example.com/non-fiction-book', 30, 6, false);

-- Inserting wishes for Alice's Home Decor Dreams
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Throw Pillows', 'example.com/throw-pillows', 40, 7, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Wall Art', 'example.com/wall-art', 60, 7, false);

-- Inserting wishes for Alice's Adventure Gear
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Hiking Boots', 'example.com/hiking-boots', 120, 8, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Camping Tent', 'example.com/camping-tent', 200, 8, false);

-- Inserting wishes for Bob's Gaming Wishlist
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Gaming Console', 'example.com/gaming-console', 300, 9, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Video Games', 'example.com/video-games', 60, 9, false);

-- Inserting wishes for Bob's Fitness Goals
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Dumbbell Set', 'example.com/dumbbell-set', 100, 10, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Yoga Mat', 'example.com/yoga-mat', 30, 10, false);

-- Inserting wishes for Bob's DIY Projects
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Tool Set', 'example.com/tool-set', 80, 11, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Painting Supplies', 'example.com/painting-supplies', 50, 11, false);
