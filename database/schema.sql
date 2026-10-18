-- =====================================================================
-- Food Delivery System - database schema + seed data (MySQL 8.0.23+)
--
-- Table and column names match the SQL in the Java code exactly
-- (DaoImpl, the controllers and YourOrdersController), so do not
-- rename anything here. All queries use the "mydb." prefix, so the
-- database must be called mydb.
--
-- Run it once:  mysql -h <host> -P <port> -u <user> -p < database/schema.sql
-- Re-running it drops and recreates all tables (all data is lost).
-- =====================================================================

CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

DROP TABLE IF EXISTS tab_orders;
DROP TABLE IF EXISTS tab_items;
DROP TABLE IF EXISTS tab_register;
DROP TABLE IF EXISTS tab_login;
DROP TABLE IF EXISTS tab_address;
DROP TABLE IF EXISTS tab_city;
DROP TABLE IF EXISTS tab_state;
DROP TABLE IF EXISTS tab_country;
DROP TABLE IF EXISTS tab_role;

-- 1 = admin, 2 = customer (DaoImpl.verify() checks these numbers)
CREATE TABLE tab_role (
    role_id   INT          NOT NULL PRIMARY KEY,
    role_name VARCHAR(20)  NOT NULL
);

CREATE TABLE tab_country (
    countryid INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    country   VARCHAR(60)  NOT NULL UNIQUE
);

CREATE TABLE tab_state (
    stateid               INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    state                 VARCHAR(60) NOT NULL,
    tab_country_countryid INT         NOT NULL,
    CONSTRAINT fk_state_country FOREIGN KEY (tab_country_countryid) REFERENCES tab_country (countryid)
);

CREATE TABLE tab_city (
    cityid            INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city              VARCHAR(60) NOT NULL,
    tab_state_stateid INT         NOT NULL,
    CONSTRAINT fk_city_state FOREIGN KEY (tab_state_stateid) REFERENCES tab_state (stateid)
);

-- addressid must stay the FIRST column: DaoImpl reads it with rs.getInt(1)
CREATE TABLE tab_address (
    addressid       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    flatno          VARCHAR(30),
    appartment      VARCHAR(100),
    landmark        VARCHAR(100),
    pincode         INT,
    tab_city_cityid INT          NOT NULL,
    CONSTRAINT fk_address_city FOREIGN KEY (tab_city_cityid) REFERENCES tab_city (cityid)
);

CREATE TABLE tab_login (
    loginid          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email            VARCHAR(100) NOT NULL UNIQUE,
    password         VARCHAR(100) NOT NULL,
    tab_role_role_id INT          NOT NULL,
    CONSTRAINT fk_login_role FOREIGN KEY (tab_role_role_id) REFERENCES tab_role (role_id)
);

CREATE TABLE tab_register (
    registerid            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fname                 VARCHAR(50),
    lname                 VARCHAR(50),
    dob                   DATE,
    mobileno              VARCHAR(15),
    tab_login_loginid     INT          NOT NULL,
    tab_address_addressid INT          NOT NULL,
    CONSTRAINT fk_register_login   FOREIGN KEY (tab_login_loginid)     REFERENCES tab_login (loginid),
    CONSTRAINT fk_register_address FOREIGN KEY (tab_address_addressid) REFERENCES tab_address (addressid)
);

-- itemname must match the dish names on veg.jsp / nonveg.jsp exactly,
-- because an order looks the item up by name.
CREATE TABLE tab_items (
    itemid         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemname       VARCHAR(100)  NOT NULL UNIQUE,
    price          DECIMAL(10,2) NOT NULL,
    deliverycharge INT           NOT NULL DEFAULT 0,
    discount       DECIMAL(5,2)  NOT NULL DEFAULT 0,
    image          LONGBLOB
);

-- The code inserts with "INSERT INTO mydb.tab_orders VALUES (?,?,?)",
-- so only these three columns may be visible. order_id is an INVISIBLE
-- primary key: it is skipped by that INSERT and by SELECT *, but it still
-- gives the table a primary key (many hosted MySQL services require one).
-- The column name "quentity" is spelled the way the Java code spells it.
CREATE TABLE tab_orders (
    order_id                BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY INVISIBLE,
    tab_register_registerid INT    NOT NULL,
    tab_items_itemid        INT    NOT NULL,
    quentity                INT    NOT NULL,
    CONSTRAINT fk_orders_register FOREIGN KEY (tab_register_registerid) REFERENCES tab_register (registerid),
    CONSTRAINT fk_orders_item     FOREIGN KEY (tab_items_itemid)        REFERENCES tab_items (itemid)
);

-- ---------------------------------------------------------------------
-- Seed data
-- ---------------------------------------------------------------------
INSERT INTO tab_role (role_id, role_name) VALUES (1, 'admin'), (2, 'customer');

-- Registration only works for a country that already exists in this table.
INSERT INTO tab_country (country) VALUES ('India');

-- Demo admin login for local testing only. Change the password before
-- using a shared or public database.
INSERT INTO tab_login (email, password, tab_role_role_id)
VALUES ('admin@food.com', 'admin123', 1);

-- Menu items shown on veg.jsp and nonveg.jsp (delivery charge Rs. 50 per the menu banner)
INSERT INTO tab_items (itemname, price, deliverycharge, discount) VALUES
    ('curd-rice',              90, 50, 0),
    ('beetroot-thoran-recipe', 100, 50, 0),
    ('Jeera-aloo',             50, 50, 0),
    ('dal fry',                50, 50, 0),
    ('dalrice',                90, 50, 0),
    ('dal makhani',            150, 50, 0),
    ('paneer-kebab',           150, 50, 0),
    ('paneer-makhani',         150, 50, 0),
    ('apollofish',             180, 50, 0),
    ('apollofishboneless',     100, 50, 0),
    ('chicken bryani',         150, 50, 0),
    ('chicken makhani',        150, 50, 0),
    ('chicken65',              180, 50, 0),
    ('chickencurry',           120, 50, 0),
    ('chicken-fried-rice',     60, 50, 0),
    ('chickentikka',           150, 50, 0);
