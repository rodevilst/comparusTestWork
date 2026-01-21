CREATE TABLE users (
                       ldap_login VARCHAR(100) NOT NULL UNIQUE,
                       name VARCHAR(100) NOT NULL,
                       surname VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (ldap_login, name, surname) VALUES
                                                  ('mike_wilson', 'Mike', 'Wilson'),
                                                  ('sarah_connor', 'Sarah', 'Connor'),
                                                  ('mysql_user', 'MySQL', 'Test');