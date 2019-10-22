INSERT INTO `Role`
    (`role_id`,`name`)
VALUES
    ('1','CUSTOMER'),
    ('2','ADMIN');

INSERT INTO `Role_set`
(`role_id`, `user_id`)
VALUES
    ('1', 1),
    ('2', 2),
    ('1', 3),
    ('1', 4),
    ('1', 5),
    ('1', 6);
