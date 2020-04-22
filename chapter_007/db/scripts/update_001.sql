create table items
(
    id          serial primary key,
    name        VARCHAR(256),
    description VARCHAR(256),
    time        TIMESTAMP
);