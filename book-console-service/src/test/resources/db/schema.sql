DROP TABLE IF EXISTS user;
CREATE TABLE user(
ID INT PRIMARY KEY,
 username VARCHAR(255),
 role VARCHAR(255),
 department VARCHAR(255),
 mobile VARCHAR(255),
 title VARCHAR(255),
 chinese_name VARCHAR(255),
 created_at TIMESTAMP
);
DROP TABLE IF EXISTS t_business;
CREATE TABLE t_business(
ID INT PRIMARY KEY,
 name VARCHAR(255),
 code VARCHAR(255),
 author VARCHAR(255),
 modifier VARCHAR(255),
 updated_at TIMESTAMP,
 created_at TIMESTAMP

);