alter table users drop column hashpass;
alter table farmer add column hashpass character varying(255);
