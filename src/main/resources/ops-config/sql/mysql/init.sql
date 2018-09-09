CREATE DATABASE agile_lender DEFAULT CHARSET=utf8 ;

CREATE TABLE user
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(50),
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    team_id INT(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
CREATE UNIQUE INDEX user_email_uindex ON user (email);

CREATE TABLE mood
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    aid INT(11) NOT NULL,
    mood TINYINT(4) NOT NULL COMMENT '心情指数',
    message VARCHAR(1000),
    date DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
CREATE UNIQUE INDEX mood_aid_date_uindex ON mood (aid, date);

CREATE TABLE sprint_date
(
    id INT(11),
    sprint_name VARCHAR(20) NOT NULL,
    team_id INT(11) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;
