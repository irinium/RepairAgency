CREATE TABLE `User` (
    `user_id`        INT          NOT NULL AUTO_INCREMENT,
    `email`     varchar(64)  NOT NULL UNIQUE,
    `password`  varchar(64)  NOT NULL,
    `role_id`   INT          NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `Appliance` (
    `appliance_id`                 INT          NOT NULL AUTO_INCREMENT,
    `name`               varchar(64)  NOT NULL,
    `model`              varchar(64)  NOT NULL,
    `price`              FLOAT        NOT NULL,
    `power_consumption`  INT          NOT NULL,
    `manufacturer_id`    INT          NOT NULL,
    `type_id`            INT          NOT NULL,
    PRIMARY KEY (`appliance_id`)
);

CREATE TABLE `Role_set` (
`role_id`  INT  NOT NULL,
`user_id`  INT  NOT NULL);

CREATE TABLE `Manufacturer` (
    `manufacturer_id`                 INT          NOT NULL AUTO_INCREMENT,
    `name`               varchar(64)  NOT NULL,
    PRIMARY KEY (`manufacturer_id`)
);

CREATE TABLE `Manufacturer_set` (
    `manufacturer_id`   INT          NOT NULL,
    `appliance_id`      INT          NOT NULL);

CREATE TABLE `Type` (
    `type_id`                 INT          NOT NULL AUTO_INCREMENT,
    `name`               varchar(64)  NOT NULL,
    PRIMARY KEY (`type_id`)
);

CREATE TABLE `Type_set` (
    `type_id`            INT          NOT NULL,
    `appliance_id`       INT          NOT NULL);

CREATE TABLE `Role` (
    `role_id`                 INT          NOT NULL AUTO_INCREMENT,
    `name`               varchar(64)  NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `Order` (
    `order_id`   INT          NOT NULL AUTO_INCREMENT,
    `title`      varchar(128) NOT NULL,
    `price`      FLOAT        NOT NULL,
    PRIMARY KEY (`order_id`)
);
