CREATE OR REPLACE VIEW v_locations
AS 
with recursive rec_locations as (
select
	id,
	name,
	name as location,
	1 as level,
	observation,
	status,
	location_id
from
	locations
where
	location_id is null
union all
select
	l.id ,
	l.name,
	rec_locations.location || ' - ' || l.name,
	rec_locations.level + 1 AS level,
	l.observation,
	l.status,
	l.location_id
from
	locations l
join rec_locations on
	l.location_id = rec_locations.id 
)
select
	id, name, location, level, observation, status, location_id
from
	rec_locations
order by location;