-- DB creation script for MySQL

DROP DATABASE IF EXISTS aroma_shop;
CREATE DATABASE aroma_shop;
USE aroma_shop;

-- Setting time zone
SET GLOBAL time_zone = '+2:00';

-- Creating tables

-- Table structure for table `users`

CREATE TABLE `users` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`role` ENUM ('admin', 'user') DEFAULT 'user',
	`login` VARCHAR(64) NOT NULL UNIQUE,
	`email` VARCHAR(64) NOT NULL UNIQUE,
	`password` VARCHAR(64) NOT NULL,
	`first_name` VARCHAR(64) NOT NULL,
	`last_name` VARCHAR(64) NOT NULL,
	`avatar` VARCHAR(64) NOT NULL DEFAULT 'default.png'
);


CREATE TABLE `categories`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE `manufacturers`(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(64) NOT NULL UNIQUE
);

-- Table structure for table `products`

CREATE TABLE `products` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(256) NOT NULL UNIQUE,
	`price` DOUBLE NOT NULL,
	`category_id` INT NOT NULL,
	`manufacturer_id` INT NOT NULL,
	 FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE,
	 FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `orders`(
   `id` INT PRIMARY KEY AUTO_INCREMENT,
   `user_id` INT NOT NULL,
   `status` ENUM ('accepted', 'confirmed','formed','exiled', 'completed', 'canceled') DEFAULT 'accepted',
   `state_detail` VARCHAR(255),
   `date_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
   );

CREATE TABLE `orders_products`(
    `order_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `quantity_product` INT NOT NULL,
	`price_product` DOUBLE NOT NULL,
	 FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
	 FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Filling in data for table `users`

INSERT INTO users 
 VALUES (DEFAULT,"admin","vlad_13","vladhaigarden@gmail.com",md5("secret"),"Evgeny","Barantsov",DEFAULT),
 (DEFAULT,DEFAULT,"grear_peace","peace@gmail.com",md5("tortinka"),"Katerina","Lyutaya",DEFAULT),
 (DEFAULT,DEFAULT,"irisha","irisha@mail.ua",md5("monkey1"),"Irina","Vashkevich",DEFAULT),
 (DEFAULT,DEFAULT,"artemon","artemon@gmail.com",md5("shadow"),"Artem","Khalin",DEFAULT),
 (DEFAULT,DEFAULT,"heisenberg","heisenberg@mail.ua",md5("master"),"Dmitry","Kravchenko",DEFAULT),
 (DEFAULT,DEFAULT,"geographer","geographer@mail.ru",md5("jackson"),"Vladimir","Klimenko",DEFAULT),
 (DEFAULT,DEFAULT,"garden","garden@mail.ru",md5("pool"),"Andrey","Zhivotovsky",DEFAULT),
 (DEFAULT,DEFAULT,"igor16","igor16@mail.ru",md5("pass"),"Igor","Golovin",DEFAULT),
 (DEFAULT,DEFAULT,"diesel","diesel@gmail.com",md5("burner"),"Vladimir","Samoilenko",DEFAULT),
 (DEFAULT,DEFAULT,"tirion","tirion@mail.ua",md5("lanister"),"Kirill","Zhivoglyad",DEFAULT);
 
 INSERT INTO categories 
 VALUES (DEFAULT,"Fridge"),
 (DEFAULT,"Washer"),
 (DEFAULT,"Shaver");
 
 INSERT INTO manufacturers
 VALUES (DEFAULT,"Samsung"),
 (DEFAULT,"Insignia"),
 (DEFAULT,"Frigidaire"),
 (DEFAULT,"LG"),
 (DEFAULT,"Whirlpool"),
 (DEFAULT,"Panasonic"),
 (DEFAULT,"Philips"),
 (DEFAULT,"Braun");
 
 INSERT INTO products
 VALUES (DEFAULT,"French Door Fridge with Thru-the-Door Ice and Water - Stainless steel",1499.99,1,1),
 (DEFAULT,"Top-Freezer Fridge - Stainless steel",529.99,1,2),
 (DEFAULT,"Top-Freezer Fridge - White",329.99,1,2),
 (DEFAULT,"Side-by-Side Fridge - Stainless steel",899.99,1,3),
 (DEFAULT,"Top-Freezer Fridge - Black",439.99,1,2),
 (DEFAULT,"French Door Smart Wi-Fi Enabled Fridge - PrintProof Stainless Steel",1799.99,1,4),
 (DEFAULT,"Bottom-Freezer Fridge - Stainless steel",1439.99,1,5),
 (DEFAULT,"French Door Fridge - Fingerprint Resistant Black Stainless Steel",2299.99,1,1),
 (DEFAULT,"Side-by-Side Fridge with Thru-the-Door Ice and Water - Stainless steel",1049.99,1,1),
 (DEFAULT,"Bottom-Freezer Refrigerator - Black",379.99,1,2),
 (DEFAULT,"Front-Loading Smart Wi-Fi Washer with 7Motion Technology - White",809.99,2,4),
 (DEFAULT,"High-Efficiency Front-Loading Washer with Steam - White",699.99,2,1),
 (DEFAULT,"High-Efficiency Front-Loading Washer with Steam - Champagne",749.99,2,1),
 (DEFAULT,"High-Efficiency Front-Loading Washer with Steam and Load & Go Dispenser - White",1111.99,2,5),
 (DEFAULT,"Addwash High-Efficiency Front-Loading Washer with Steam - Fingerprint Resistant Black Stainless Steel",1079.99,2,1),
 (DEFAULT,"Front-Loading Smart Wi-Fi Washer with 6Motion Technology - White",699.99,2,4),
 (DEFAULT,"High-Efficiency Front-Loading Washer with Steam - Black stainless steel",849.99,2,1),
 (DEFAULT,"Smart Wi-Fi Enabled Electric Dryer - White",809.99,2,4),
 (DEFAULT,"Addwash High-Efficiency Front-Loading Washer with Steam - White",1169.99,2,1),
 (DEFAULT,"High-Efficiency Front-Loading Washer with Steam - Chrome Shadow",749.99,2,5),
 (DEFAULT,"Arc5 Wet/Dry Electric Shaver - Silver",119.99,3,6),
 (DEFAULT,"Series 9 Wet/Dry Electric Shaver - Chrome",299.99,3,8),
 (DEFAULT,"Series 3 Solo Electric Shaver - Black/Blue",69.99,3,8),
 (DEFAULT,"Norelco Series 6000 SmartClick Wet/Dry Electric Shaver - Black",99.99,3,7),
 (DEFAULT,"Arc3 3-Blade Electric Shaver - Black",74.99,3,6),
 (DEFAULT,"Series 3 Electric Shaver - Red",44.99,3,8),
 (DEFAULT,"All-In-One Trimmer - Silver",59.99,3,6),
 (DEFAULT,"Series 5 Wet/Dry Electric Shaver - Black",134.99,3,8),
 (DEFAULT,"Beard Trimmer - Black",64.99,3,8),
 (DEFAULT,"Close Curves Wet/Dry Women's Shaver - Pink",18.99,3,6);
 
 INSERT INTO orders (status,user_id)
 VALUES ("accepted",1),
 ("confirmed",2),
 ("accepted",3),
 ("formed",1),
 ("completed",1),
 ("canceled",4);
 
  INSERT INTO orders_products (order_id,product_id,quantity_product,price_product)
 VALUES (1,1,3,1499.99),
 (2,2,3,529.99),
 (3,3,1,329.99),
 (4,4,2,899.99);
 
 SELECT * FROM users;
 SELECT * FROM categories;
 SELECT * FROM manufacturers;
 SELECT * FROM products;
 SELECT * FROM orders;
 SELECT * FROM orders_products;
 