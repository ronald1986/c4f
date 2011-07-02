create table usr (
	id					char(32),
	version				integer,
	org_id				char(32),
	org_code			varchar(20),
	loginid				varchar(20),
	name				varchar(200),
	email				varchar(200),
	password			varchar(100),
	create_date			timestamp,
	create_user_id		char(32),
	create_user			varchar(60),
	update_date			timestamp,
	update_user_id		char(32),
	update_user			varchar(60)
);

alter table usr add primary key (id);

alter table usr add constraint uk_usr_email unique (email);

alter table usr add constraint uk_usr_org_login unique (org_code, loginid);

alter table usr add foreign key (org_id) references organization;