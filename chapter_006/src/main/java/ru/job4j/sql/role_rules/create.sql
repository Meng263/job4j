
create table category (
id serial primary key,
description VARCHAR(256)
);

create table state (
id serial primary key,
description VARCHAR(256)
);


create table item (
id serial primary key,
description VARCHAR(256),
state_id int references state(id),
category_id int references category(id)
);

create table comment (
id serial primary key,
comment VARCHAR(256),
item_id int references item(id)
);

create table attached (
id serial primary key,
path VARCHAR(256),
item_id int references item(id)
);

create table rule (
id serial primary key,
description VARCHAR(256)
);

create table role (
id serial primary key,
description VARCHAR(256)
);

create table role_rule (
id serial primary key,
role_id int references role(id),
rule_id int references rule(id)
);

create table users (
id serial primary key,
name VARCHAR(256),
role_id int references role(id)
);


