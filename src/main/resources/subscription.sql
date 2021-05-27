create table subscription
(
	id integer not null
		constraint subscription_pk
			primary key,
	name text,
	basic_fee integer,
	seconds_included integer,
	price_per_minute numeric,
	data_volume numeric
);

create unique index subscription_id_uindex
	on subscription (id);

