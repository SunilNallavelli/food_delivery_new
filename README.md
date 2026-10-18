# Online Food Delivery System

A web application where customers register, browse a veg / non-veg menu, place orders and see their order history, and an admin manages the menu items. Built with **Java Servlets, JSP and JDBC** on **Apache Tomcat 9**, using a **MySQL 8** database.

## Tech stack

| Layer | Technology |
|---|---|
| View | JSP, HTML, CSS, Bootstrap 4 |
| Controller | Java Servlets (Servlet API 4.0, `javax.servlet`) |
| Business / data access | Plain Java classes in layers: Delegate, Service, DAO |
| Database | MySQL 8 via JDBC (MySQL Connector/J 8.4) |
| Server | Apache Tomcat 9 |
| IDE | Eclipse IDE for Enterprise Java and Web Developers |

## Features

**Customer**
- Register with personal details and a delivery address
- Log in / log out (session-based; session times out after 10 minutes)
- Browse veg and non-veg menus and order an item with a quantity
- See "Your orders" with price, delivery charge and total

**Admin** (role 1 in `tab_login`)
- Add a menu item with an image (multipart upload stored as a BLOB)
- Update the delivery charge
- Delete an item by id
- View all items

## Architecture

```
Browser
  │  HTTP (form submit)
  ▼
JSP pages (WebContent/*.jsp)
  │
  ▼
Controller (Servlets, com.online.food.controller)
  │  builds a VO from request parameters
  ▼
Delegate (com.online.food.deligates)     VO  → DTO
  ▼
Service  (com.online.food.serviceImpl)   DTO → BO
  ▼
DAO      (com.online.food.daoImpl)       BO  → SQL (PreparedStatement)
  ▼
DBConnection (util) reads config/db.properties ──► MySQL (database "mydb")
```

- **VO** (Value Object): raw values from the request, all Strings
- **DTO** (Data Transfer Object): carries data between the delegate and the service, with types converted (e.g. pincode → int)
- **BO** (Business Object): what the DAO works with

## Project structure

```
src/com/online/food/
  controller/   Servlets (Login, Registration, Orders, Admin item CRUD, Logout, Forgot password)
  deligates/    Delegate layer
  serviceI/     Service interface
  serviceImpl/  Service implementation
  daoI/         DAO interface
  daoImpl/      DAO implementation (JDBC)
  vo/ dto/ bo/  Data objects for each layer
  util/         DBConnection (reads db.properties)
  config/       db.properties.example (copy to db.properties)
WebContent/
  *.jsp         Pages (home, login, register, menus, admin, orders)
  css/ js/ images/
  WEB-INF/web.xml, WEB-INF/lib/ (JDBC driver jar)
database/
  schema.sql    Creates database "mydb", all tables and seed data
```

## Database

Nine tables in the `mydb` database: `tab_role`, `tab_login`, `tab_register`, `tab_address`, `tab_city`, `tab_state`, `tab_country`, `tab_items`, `tab_orders`.

```
tab_role 1──* tab_login 1──1 tab_register *──1 tab_address *──1 tab_city *──1 tab_state *──1 tab_country
tab_register 1──* tab_orders *──1 tab_items
```

`database/schema.sql` creates everything and seeds the two roles, the country India, a demo admin login and the 16 menu items shown on the menu pages.

## Running it locally

### Prerequisites
- JDK 8 or newer (JDK 17 recommended)
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat **9.x** (Tomcat 10+ uses `jakarta.servlet` and will not run this project)
- A MySQL 8 database (local or online, e.g. Aiven's free MySQL)
- `mysql-connector-j-8.4.0.jar` in `WebContent/WEB-INF/lib`

### 1. Create the database
Run `database/schema.sql` against your MySQL server (MySQL Workbench, DBeaver or the `mysql` client).

### 2. Configure the connection
Copy `src/com/online/food/config/db.properties.example` to `db.properties` in the same folder and fill in host, port, user and password. For an online database keep `?sslMode=REQUIRED` in the URL. `db.properties` is git-ignored so the password never reaches GitHub.

### 3. Import into Eclipse
1. **File → Import → General → Existing Projects into Workspace**, then select this folder.
2. **Window → Preferences → Server → Runtime Environments → Add → Apache Tomcat v9.0**. Name it exactly `Apache Tomcat v9.0` and point it at your Tomcat 9 folder.
3. Right-click the project → **Run As → Run on Server** → Tomcat v9.0.

### 4. Open the app
http://localhost:8080/Food_Delivery_System/

- Customer: click **Customer**, register, then log in
- Admin: use the demo admin login listed in `database/schema.sql`

## Known limitations / planned improvements
- Passwords are stored in plain text, and "Forgot password" shows the password on screen. Plan: BCrypt hashing and a reset link sent by email.
- Admin pages have no session/role check. Plan: a servlet `Filter` that allows only the admin role.
- Menu pages are static JSP. Plan: load items from `tab_items` with JSTL.
- No cart, payment or order status yet.
- A new JDBC connection is opened per request and not always closed. Plan: a connection pool (HikariCP / Tomcat JDBC) and try-with-resources.
- Registration runs several inserts without a transaction. Plan: `setAutoCommit(false)` + commit / rollback.
- "Update item" changes the delivery charge for all items.
