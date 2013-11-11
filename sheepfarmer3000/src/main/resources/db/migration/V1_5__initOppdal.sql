
insert into users (id) values ('farmer1001');
insert into farmer (id, users_id, hashpass) values (1001,'farmer1001','1001');

insert into coordinate (id, latitude, longitude, dateevent) values ('farmer1c',62.585715,9.708897,'2013-11-11 12:00:00 CET');
insert into farmercoordinate (id, coordinate_id, farmerid) values ('farmer1c','farmer1c',1001);

insert into coordinate (id, latitude, longitude, dateevent) values ('farmer2c',62.578047,9.718252,'2013-11-11 12:00:01 CET');
insert into farmercoordinate (id, coordinate_id, farmerid) values ('farmer2c','farmer2c',1001);

insert into coordinate (id, latitude, longitude, dateevent) values ('farmer3c',62.580498,9.749666,'2013-11-11 12:00:02 CET');
insert into farmercoordinate (id, coordinate_id, farmerid) values ('farmer3c','farmer3c',1001);

insert into coordinate (id, latitude, longitude, dateevent) values ('farmer4c',62.590891,9.733702,'2013-11-11 12:00:03 CET');
insert into farmercoordinate (id, coordinate_id, farmerid) values ('farmer4c','farmer4c',1001);
