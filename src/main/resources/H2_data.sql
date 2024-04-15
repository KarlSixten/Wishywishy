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
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Watch', 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 200, 1, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Headphones', 'https://static-01.daraz.pk/p/2c1a63e56a5b6a6c8d7a48fce396cfc2.jpg_750x750.jpg_.webp', 100, 1, true);

-- Inserting wishes for Gustav's Christmas List
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Sweater', 'https://frejasknit.dk/cdn/shop/files/1_5d8c3993-47a2-483b-9d00-94f5c8b24395.png?v=1698082087&width=1445', 50, 2, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Book', 'https://s26162.pcdn.co/wp-content/uploads/sites/2/2022/05/Book.jpg', 20, 2, false);

-- Inserting wishes for Gustav's Travel Bucket List
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Backpack', 'https://xcdn.next.co.uk/COMMON/Items/Default/Default/ItemImages/AltItemShot/315x472/U10474s.jpg', 100, 3, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Travel Guide', 'https://api-v2.travelguidesfree.com/media/states/63d813e664699_TGF_VisitNewJersey_Travel-Guide-cover_2023_840x1120_23.01.20.jpg', 30, 3, false);

-- Inserting wishes for Test User's Wedding Registry
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Dinnerware Set', 'https://m.media-amazon.com/images/I/610ta31oH3L.jpg', 150, 4, true);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Blender', 'https://www.kunstogkokkentoj.dk/resources/product-images/3800300_2.jpg?mode=pad&width=1600&rnd=20240115114029', 80, 4, false);

-- Inserting wishes for Test User's Summer Essentials
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Beach Towel', 'https://summersand.com/cdn/shop/files/Family-Towel-180x160cm---Deep-Blue-7.jpg?v=1704394074&width=1946', 20, 5, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Sunglasses', 'https://hips.hearstapps.com/hmg-prod/images/pjthompson-163-philip-edsel-64c16cdd61133.jpg?crop=1.00xw:0.336xh;0,0.223xh&resize=1200:*', 50, 5, false);

-- Inserting wishes for Alice's Book Wishlist
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Fiction Novel', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhDcrCXQnEdyAhZmQMLFXrlbs_bOsV4hhff3OubhRyKA&s', 25, 6, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Non-Fiction Book', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQWGttdHUbIZZ95xcB2jn6ykTGOac8_rs06M7cYCrrDw&s', 30, 6, false);

-- Inserting wishes for Alice's Home Decor Dreams
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Throw Pillows', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQFVF_tmgxqIKOCHYUir3zcQ3Z1oS8hwPN3TAO3czBbXg&s', 40, 7, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Wall Art', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuW_490zKMZXFPOCiec6bBT_odfe7RLKy-Lf3IkreK7g&s', 60, 7, false);

-- Inserting wishes for Alice's Adventure Gear
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Hiking Boots', 'https://www.switchbacktravel.com/sites/default/files/articles%20/Hiking%20Boots%20%28Lowa%20Renegade%20GTX%20on%20rock%29%20%28m%29.jpg', 120, 8, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Camping Tent', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4sygXbLVfbDs0Mrf8MOFqkJuCwVAi2mZu9hnxXFFtNA&s', 200, 8, false);

-- Inserting wishes for Bob's Gaming Wishlist
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Gaming Console', 'https://www.ft.com/__origami/service/image/v2/images/raw/https%3A%2F%2Fd1e00ek4ebabms.cloudfront.net%2Fproduction%2F6652d25b-45ea-461d-8808-011e8e69b022.jpg?source=next-article&fit=scale-down&quality=highest&width=700&dpr=1', 300, 9, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Video Games', 'https://assets-global.website-files.com/618d852d383de946ce0e3fa5/6495da7241185414c12ddc02_videoGamePlanet.PNG', 60, 9, false);

-- Inserting wishes for Bob's Fitness Goals
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Dumbbell Set', 'https://m.media-amazon.com/images/I/81lN-xUd8oL._AC_UF1000,1000_QL80_.jpg', 100, 10, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Yoga Mat', 'https://m.media-amazon.com/images/I/71URfHPEanL._AC_SL1500_.jpg', 30, 10, false);

-- Inserting wishes for Bob's DIY Projects
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Tool Set', 'https://m.media-amazon.com/images/I/71fjUh2t-1L.jpg', 80, 11, false);
INSERT INTO wish (wishname, URL, wishprice, WISHLISTID, isReserved) VALUES ('Painting Supplies', 'https://m.media-amazon.com/images/I/91mGRAIBT3L.jpg', 50, 11, false);
