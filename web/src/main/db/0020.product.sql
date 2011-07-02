create table product (
	id					char(32),
	version				integer,
	org_id				char(32),
	code				varchar(20),
	name				varchar(200),
	uom_id				char(32),
	description			varchar(500),
	create_date			timestamp,
	create_user_id		char(32),
	create_user			varchar(60),
	update_date			timestamp,
	update_user_id		char(32),
	update_user			varchar(60)
);

alter table product add primary key (id);

alter table product add constraint uk_product_code unique (org_id, code);

alter table product add constraint uk_product_name unique (org_id, name);

alter table product add foreign key (org_id) references organization;