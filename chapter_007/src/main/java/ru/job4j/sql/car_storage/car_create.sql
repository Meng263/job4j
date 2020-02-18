create table body(
id serial primary key,
	color varchar(50),
body_type varchar(50)
);

create table engine (
id serial primary key,
	engine_type varchar(50),
	horse_power int
);

create table gearbox (
id serial primary key,
	gearbox_type varchar(50),
	gear_count int
);

create table car (
id serial primary key,
	model varchar(50),
	body_id int REFERENCES body(id),
	engine_id int references engine(id),
	gearbox_id int references gearbox(id)
);

insert into body (color, body_type) values ('red', 'jeep');
insert into body (color, body_type) values ('green', 'hatch');
insert into body (color, body_type) values ('red', 'sedan');
insert into body (color, body_type) values ('red', 'pickup');
insert into body (color, body_type) values ('black', 'coupe');
insert into body (color, body_type) values ('gray', 'hatch');
insert into body (color, body_type) values ('red', 'sedan');

insert into engine (engine_type, horse_power) values ('V8', '256');
insert into engine (engine_type, horse_power) values ('VR6', '180');
insert into engine (engine_type, horse_power) values ('R4', '150');
insert into engine (engine_type, horse_power) values ('V6', '200');
insert into engine (engine_type, horse_power) values ('R3', '70');
insert into engine (engine_type, horse_power) values ('R4', '75');
insert into engine (engine_type, horse_power) values ('V12', '500');

insert into gearbox(gearbox_type, gear_count) values ('automatic', 4);
insert into gearbox(gearbox_type, gear_count) values ('automatic', 5);
insert into gearbox(gearbox_type, gear_count) values ('automatic', 6);
insert into gearbox(gearbox_type, gear_count) values ('automatic', 3);
insert into gearbox(gearbox_type, gear_count) values ('manual', 5);
insert into gearbox(gearbox_type, gear_count) values ('manual', 6);
insert into gearbox(gearbox_type, gear_count) values ('variator', 6);
insert into gearbox(gearbox_type, gear_count) values ('robot', 6);
insert into gearbox(gearbox_type, gear_count) values ('robot', 5);
insert into gearbox(gearbox_type, gear_count) values ('dsg', '6');

insert into car (model, body_id, engine_id, gearbox_id) values ('gaz-24', 7, 1, 5);
insert into car (model, body_id, engine_id, gearbox_id) values ('porshe 911', 5, 1, 6);
insert into car (model, body_id, engine_id, gearbox_id) values ('mersedes s600', 3, 7, 2);