CREATE TABLE `Users` (
    `user_id`        INT          NOT NULL AUTO_INCREMENT,
    `email`          varchar(64)  NOT NULL UNIQUE,
    `password`       varchar(32)   NOT NULL,
    `phone`          varchar(32)  NOT NULL,
    `role_id`        INT          NOT NULL,
    `user_name`      varchar(64)  NOT NULL,
    `surname`        varchar(64)  NOT NULL,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `Appliances` (
    `appliance_id`       INT          NOT NULL AUTO_INCREMENT,
    `appliance_name`     varchar(64)  NOT NULL,
    `model`              varchar(64)  NOT NULL,
    `disrepair`          varchar(128) NOT NULL,
    `manufacturer_id`    INT          NOT NULL,
    `type_id`            INT          NOT NULL,
    `user_id`            INT          NOT NULL,
    PRIMARY KEY (`appliance_id`)
);

CREATE TABLE `Manufacturers` (
    `manufacturer_id`                 INT          NOT NULL AUTO_INCREMENT,
    `manufacturer_name`                            varchar(32)  NOT NULL,
    PRIMARY KEY (`manufacturer_id`)
);

CREATE TABLE `Types` (
    `type_id`                 INT          NOT NULL AUTO_INCREMENT,
    `type_name`                    varchar(32)  NOT NULL,
    PRIMARY KEY (`type_id`)
);

CREATE TABLE `Roles` (
    `role_id`                 INT          NOT NULL AUTO_INCREMENT,
    `role_name`                    varchar(16)  NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `Orders` (
    `order_id`      INT                 NOT NULL AUTO_INCREMENT,
    `title`         varchar(128),
    `price`         DECIMAL,
    `user_id`       INT                 NOT NULL,
    `appliance_id`  INT                 NOT NULL,
    `master_id`     INT,
    `state`         BOOL,
    PRIMARY KEY (`order_id`)
);
CREATE TABLE `Responses` (
    `response_id`   INT              NOT NULL AUTO_INCREMENT,
    `text`          TINYTEXT             NOT NULL,
    `user_id`       INT              NOT NULL,
    PRIMARY KEY (`response_id`)
);

