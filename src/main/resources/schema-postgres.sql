DROP TABLE IF EXISTS application_user;
CREATE TABLE application_user(id serial PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), username VARCHAR(255), email VARCHAR(255), password VARCHAR(255));