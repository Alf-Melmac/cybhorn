create table terminal
(
	id integer not null
		constraint terminal_pk
			primary key,
	name text,
	supported_ran text
);

create unique index terminal_id_uindex
	on terminal (id);

