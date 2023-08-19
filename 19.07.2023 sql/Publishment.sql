--CREATE DATABASE Publishment
CREATE TABLE Themes (
    id INT PRIMARY KEY,
    name nvarchar(50)
)

CREATE TABLE Country (
    id INT PRIMARY KEY,
    name nvarchar(50),
)

CREATE TABLE Authors (
    id INT PRIMARY KEY,
    firstname nvarchar(50),
    lastname nvarchar(50),
	id_country int,
	FOREIGN KEY (id_country) REFERENCES Country(id)
)

CREATE TABLE Books (
    id INT PRIMARY KEY,
    name nvarchar(50),
    id_theme int,
	FOREIGN KEY (id_theme) REFERENCES Themes(id),
	id_author int,
    FOREIGN KEY (id_author) REFERENCES Authors(id),
	price int,
	date_of_publish datetime,
	pages int
)


CREATE TABLE Shops (
    id INT PRIMARY KEY,
    name nvarchar(50),
	id_country int,
	FOREIGN KEY (id_country) REFERENCES Country(id)
)

CREATE TABLE Sales (
    id INT PRIMARY KEY,
    id_book int,
	FOREIGN KEY (id_book) REFERENCES Books(id),
	date_of_sale date,
	price int,
	quantity int,
	id_shop int,
	FOREIGN KEY (id_shop) REFERENCES Shops(id)
)