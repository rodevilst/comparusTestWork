ALTER SESSION SET CONTAINER = XEPDB1;

ALTER SESSION SET CURRENT_SCHEMA = TESTUSER;

CREATE TABLE users (
                       id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
                       username VARCHAR2(100) NOT NULL UNIQUE,
                       name VARCHAR2(100) NOT NULL,
                       surname VARCHAR2(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, name, surname) VALUES ('oracle_admin', 'Oracle', 'Admin');
INSERT INTO users (username, name, surname) VALUES ('david_lee', 'David', 'Lee');
INSERT INTO users (username, name, surname) VALUES ('oracle_user', 'Oracle', 'Test');

COMMIT;