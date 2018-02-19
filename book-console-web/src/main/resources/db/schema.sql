DROP TABLE IF EXISTS user;
CREATE TABLE user(
 ID INT(11) AUTO_INCREMENT ,
 username VARCHAR(255),
 password varchar(255),
 USER_CODE VARCHAR(255),
 email VARCHAR(64),
 role VARCHAR(255),
 department VARCHAR(255),
 mobile VARCHAR(255),
 title VARCHAR(255),
 chinese_name VARCHAR(255),
 created_at TIMESTAMP,
 PRIMARY KEY(id)
);

CREATE TABLE
    fruit_category
    (
        ID INT(11) AUTO_INCREMENT,
        fruit_category VARCHAR(10) not null,
        fruit_type VARCHAR(255),
        fruit_level VARCHAR(255),
        fruit_DESC VARCHAR(255),
        crt_user VARCHAR(10),
        cup_user VARCHAR(10),
        crt_time TIMESTAMP,
        upt_time TIMESTAMP,
        PRIMARY KEY(id)
);

CREATE TABLE
    fruit_detail
    (
        ID INT(11) AUTO_INCREMENT,
        fruit_code VARCHAR(10) not null,
        fruit_nm VARCHAR(10) not null,
        fruit_DESC TEXT,
        fruit_category_id VARCHAR(255),
        fruit_category VARCHAR(255),
        fruit_man_id VARCHAR(255),
        fruit_man_nm VARCHAR(255),
        fruit_status VARCHAR(255),
        crt_user VARCHAR(10),
        cup_user VARCHAR(10),
        crt_time TIMESTAMP,
        upt_time TIMESTAMP,
        PRIMARY KEY(id)
);


