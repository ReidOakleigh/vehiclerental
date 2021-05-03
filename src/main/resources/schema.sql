drop table if exists customer;
create table customer (
	id bigint not null auto_increment,
	cust_name varchar(50),
	birthdate date,
	drivers_license varchar(10),
	insurance varchar(50),
	phone_number varchar(10),
	primary key(id)
);
