CREATE DATABASE IF NOT EXISTS Weight;
use Weight;

DROP TABLE IF EXISTS Roles_Users;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;

CREATE TABLE Users (
	id INT(11) UNSIGNED
		AUTO_INCREMENT
		NOT NULL
		PRIMARY KEY,
	name VARCHAR(255),
	ini VARCHAR(4),
	cpr VARCHAR(10),
	psswrd VARCHAR(255)
);
ALTER TABLE Users AUTO_INCREMENT=10;

CREATE TABLE Roles (
	id INT(11) UNSIGNED
		AUTO_INCREMENT
		NOT NULL
		PRIMARY KEY,
	name VARCHAR(255)
);
CREATE TABLE Roles_Users (
	user_id INT(11) UNSIGNED
		NOT NULL,
	role_id INT(11) UNSIGNED
		NOT NULL,
	FOREIGN KEY (user_id) REFERENCES Users(id)
		ON DELETE CASCADE,
	FOREIGN KEY (role_id) REFERENCES Roles(id)
		ON DELETE CASCADE
);
	