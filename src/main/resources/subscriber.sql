create table subscriber
(
	imsi bigint not null
		constraint subscriber_pk
			primary key,
	terminal_id bigint
		constraint subscriber_terminal_id_fk
			references terminal,
	seconds_called integer,
	data_used numeric,
	subscription_id bigint
		constraint subscriber_subscription_id_fk
			references subscription,
	first_name text,
	last_name text
);

create unique index subscriber_imsi_uindex
	on subscriber (imsi);

