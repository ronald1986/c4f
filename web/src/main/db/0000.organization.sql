create table organization (
	id					char(32),
	version				integer,
	code				varchar(20),
	name				varchar(200),
	postal_code			varchar(50),
	postal_address1		varchar(200),
	postal_address2		varchar(200),
	postal_address3		varchar(200),
	country				char(32),
	create_date			timestamp,
	create_user_id		char(32),
	create_user			varchar(60),
	update_date			timestamp,
	update_user_id		char(32),
	update_user			varchar(60)
);

alter table organization add primary key (id);

alter table organization add constraint uk_org_code unique (code);

alter table organization add constraint uk_org_name unique (name);