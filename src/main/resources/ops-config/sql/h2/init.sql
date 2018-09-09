DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    id INT(11)  AUTO_INCREMENT PRIMARY KEY NOT NULL,
    email VARCHAR(50),
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    team_id INT(11)
);
CREATE UNIQUE INDEX user_email_uindex ON user (email);

DROP TABLE IF EXISTS mood;
CREATE TABLE IF NOT EXISTS mood
(
    id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    aid INT(11) NOT NULL,
    mood TINYINT(4) NOT NULL COMMENT '心情指数',
    message VARCHAR(1000),
    date DATE NOT NULL
);
CREATE UNIQUE INDEX mood_aid_date_uindex ON mood (aid, date);

DROP TABLE IF EXISTS sprint_date;
CREATE TABLE IF NOT EXISTS sprint_date
(
    id INT(11),
    sprint_name VARCHAR(20) NOT NULL,
    team_id INT(11) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);
