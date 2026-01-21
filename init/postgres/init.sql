CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                       username VARCHAR(100) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL,
                       surname VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, name, surname) VALUES
                                                ('john_doe', 'John', 'Doe'),
                                                ('jane_smith', 'Jane', 'Smith'),
                                                ('postgres_user', 'Postgres', 'Test');