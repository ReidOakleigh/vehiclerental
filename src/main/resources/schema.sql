create table if exists customer(
	id bigint not null auto_increment,
	varchar cust_name,
	birthdate data,
	license_number varchar(10),
	insurance varchar(50),
	phone_number varchar(10),
	primary key(id)
);
