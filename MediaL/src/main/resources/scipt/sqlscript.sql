create table user (id integer not null, country varchar(255), enable bit not null, pass varchar(255), roles varchar(255), username varchar(255), primary key (id))
create table lead (lead_id integer not null, add1 varchar(255), add2 varchar(255), city varchar(255), company_name varchar(255), department varchar(255), email varchar(255), full_name varchar(255), job_title varchar(255), phone varchar(255), pincode varchar(255), primary key (lead_id))