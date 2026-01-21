CREATE TABLE users (
                       user_id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
                       username VARCHAR(100) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL,
                       surname VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (user_id, username, name, surname) VALUES
                                                    (UUID(), 'alex_brown', 'Alex', 'Brown'),
                                                    (UUID(), 'maria_garcia', 'Maria', 'Garcia'),
                                                    (UUID(), 'mariadb_user', 'MariaDB', 'Test');