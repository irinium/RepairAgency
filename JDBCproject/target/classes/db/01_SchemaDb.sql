CREATE TABLE `Users` (
    `user_id`        INT          NOT NULL AUTO_INCREMENT,
    `email`     varchar(16)  NOT NULL UNIQUE,
    `password`  varchar(8)  NOT NULL,
    `role_id`   TINYINT          NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `Masters` (
    `master_id`        INT   NOT NULL AUTO_INCREMENT,
    `email`     varchar(16)  NOT NULL UNIQUE,
    `password`  varchar(8)  NOT NULL,
    `role_id`   TINYINT          NOT NULL,
    `order_id`  INT
    PRIMARY KEY (`master_id`)
);

CREATE TABLE `Appliances` (
    `appliance_id`       INT          NOT NULL AUTO_INCREMENT,
    `name`               varchar(64)  NOT NULL,
    `model`              varchar(64)  NOT NULL,
    `disrepair`          varchar(128)        NOT NULL,
    `power_consumption`  INT          NOT NULL,
    `manufacturer_id`    INT          NOT NULL,
    `type_id`            INT          NOT NULL,
    PRIMARY KEY (`appliance_id`)
);

CREATE TABLE `Manufacturers` (
    `manufacturer_id`                 INT          NOT NULL AUTO_INCREMENT,
    `name`                            varchar(32)  NOT NULL,
    PRIMARY KEY (`manufacturer_id`)
);

CREATE TABLE `Types` (
    `type_id`                 INT     NOT NULL AUTO_INCREMENT,
    `name`                    varchar(32)  NOT NULL,
    PRIMARY KEY (`type_id`)
);

CREATE TABLE `Roles` (
    `role_id`                 INT     NOT NULL AUTO_INCREMENT,
    `name`                    varchar(16)  NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `Orders` (
    `order_id`   INT                 NOT NULL AUTO_INCREMENT,
    `title`      varchar(128)        NOT NULL,
    `price`      BIGINT unsigned     NOT NULL,
    PRIMARY KEY (`order_id`)
);
