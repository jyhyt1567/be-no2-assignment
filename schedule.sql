Use schedule;

DROP TABLE IF EXISTS schedule;

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    uid BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    password VARCHAR(100),
    email VARCHAR(100),
    createAt datetime,
    modifiedAt datetime
);

CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    todo TEXT,
    createAt datetime,
    modifiedAt datetime,
    uid BIGINT,
    FOREIGN KEY (uid) references users(uid)
);



