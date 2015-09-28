create table t_Province (
id integer primary key auto increment,
province_name text,
province_code text)


create table t_City(
id integer primary key auto increment,
city_name text,
city_code text,
province_id integer)

create table t_County(
id integer primary key auto increment,
county_name text,
county_code text,
city_id integer)


