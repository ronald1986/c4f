create table codelist (
	id					char(32),
	version				integer,
	org_id				char(32),
	name				varchar(20),
	description			varchar(200),
	create_date			timestamp,
	create_user_id		char(32),
	create_user			varchar(60),
	update_date			timestamp,
	update_user_id		char(32),
	update_user			varchar(60)
);

alter table codelist add primary key (id);

alter table codelist add constraint uk_codelist_name unique (org_id, name);

alter table codelist add foreign key (org_id) references organization;


create table codelist_item (
	id					char(32),
	version				integer,
	codelist_id			char(32),
	code				varchar(20),
	name				varchar(200),
	create_date			timestamp,
	create_user_id		char(32),
	create_user			varchar(60),
	update_date			timestamp,
	update_user_id		char(32),
	update_user			varchar(60)
);

alter table codelist_item add primary key (id);

alter table codelist_item add constraint uk_codelist_item_code unique (codelist_id, code);

alter table codelist_item add foreign key (codelist_id) references codelist;