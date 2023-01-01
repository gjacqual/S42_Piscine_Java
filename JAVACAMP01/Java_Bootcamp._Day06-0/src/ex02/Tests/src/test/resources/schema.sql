DROP TABLE IF EXISTS products;

CREATE TABLE products (
    identifier INTEGER IDENTITY PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    price INTEGER
);