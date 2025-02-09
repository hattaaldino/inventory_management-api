CREATE DATABASE "DbInventoryManagement";

CREATE TABLE inventory (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	category VARCHAR(255),
	quantity INT NOT NULL CHECK(quantity >= 0),
	unit_price DECIMAL(10,2) CHECK (unit_price >= 0),
	supplier VARCHAR(255),
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);