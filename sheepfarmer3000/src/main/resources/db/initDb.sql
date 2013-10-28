--Add farmer 1
INSERT INTO users (id,firstname,lastname, hashpass, email, phone) VALUES ('uuid_farmer1', 'Ole', 'Olsen', 'pass1', 'ole.olsen@bondelaget.no', '12345678');
INSERT INTO farmer VALUES(1, 'uuid_farmer1');
--Add farmer 2
INSERT INTO users (id,firstname,lastname, hashpass, email, phone) VALUES ('uuid_farmer2', 'Hans', 'Hansen', 'pass2', 'hans.hansen@bondelaget.no', '52345678');
INSERT INTO farmer VALUES(2, 'uuid_farmer2');

--Add sheepscoordinates
INSERT INTO coordinate (id, dateevent, latitude, longitude) VALUES 
    ('sheep1coordinate1',TIMESTAMP '2011-05-16 15:00:00' ,62.00000,9.00000),
    ('sheep1coordinate2',TIMESTAMP '2011-05-16 12:00:00' ,61.00000,8.00000),
    ('sheep1coordinate3',TIMESTAMP '2011-05-16 09:00:00' ,63.00000,9.00000),
    ('sheep2coordinate1',TIMESTAMP '2011-05-16 15:00:00' ,62.00000,8.00000),
    ('sheep2coordinate2',TIMESTAMP '2011-05-16 12:00:00' ,61.00000,10.00000);

INSERT INTO sheepcoordinate (id, coordinate_id, sheep_id) VALUES 
    ('sheep1coordinate1', 'sheep1coordinate1', 'sheep1'),
    ('sheep1coordinate2', 'sheep1coordinate2', 'sheep1'),
    ('sheep1coordinate3', 'sheep1coordinate3', 'sheep1'),
    ('sheep2coordinate1', 'sheep2coordinate1', 'sheep2'),
    ('sheep2coordinate2', 'sheep2coordinate2', 'sheep2');

INSERT INTO sheep (id, farmerid, birth, alive, lastcoordinateid) VALUES 
    ('sheep1', 1,'2013-01-01' , true, 'sheep1coordinate1'),
    ('sheep2', 1,'2013-01-02' , true, 'sheep2coordinate1');



