INSERT INTO `Type`
    (`type_id`,`name`)
VALUES
    ('1','MAJOR_HOUSEHOLD_APPLIANCE'),
    ('2','MINOR_HOUSEHOLD_APPLIANCE'),
    ('3','CLIMATIC_APPLIANCE');

INSERT INTO `Type_set`
    (`type_id`,`appliance_id`)
VALUES
    ('1', 1),
    ('1', 2),
    ('1', 3),
    ('2', 4),
    ('2', 5),
    ('2', 6),
    ('3', 7),
    ('3', 8),
    ('3', 9);