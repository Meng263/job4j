select * from car as c 
left outer  join body as b on c.body_id = b.id 
left outer join engine as e on c.engine_id = e.id
left outer join gearbox as g on c.gearbox_id = g.id;

select b.id, b.color, b.body_type from car as c 
right outer  join body as b on c.body_id = b.id 
where c.id is null;

select e.id, e.engine_type, e.horse_power from car as c 
right outer join engine as e on c.engine_id = e.id
where c.id is null;

select g.id, g.gearbox_type, g.gear_count from car as c 
right outer join gearbox as g on c.gearbox_id = g.id
where c.id is null;

