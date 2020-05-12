CREATE TABLE items
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(256),
    description VARCHAR(256),
    time        TIMESTAMP
);