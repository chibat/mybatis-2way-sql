CREATE TABLE city (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR,
    state VARCHAR,
    country VARCHAR
);

CREATE SEQUENCE employee_seq START WITH 100 INCREMENT BY 1;

CREATE TABLE employee (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    version INTEGER NOT NULL);

