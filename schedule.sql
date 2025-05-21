CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    todo TEXT,
    password VARCHAR(100),
    createAt datetime,
    modifiedAt datetime
);
