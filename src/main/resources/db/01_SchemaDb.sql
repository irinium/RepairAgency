CREATE TABLE `Roles`
(
    `role_id`   INT         NOT NULL AUTO_INCREMENT,
    `role_name` varchar(16) NOT NULL,
    PRIMARY KEY (`role_id`)
);

CREATE TABLE `Users`
(
    `user_id`   INT         NOT NULL AUTO_INCREMENT,
    `email`     varchar(64) NOT NULL UNIQUE,
    `password`  varchar(32) NOT NULL,
    `phone`     varchar(32) NOT NULL,
    `role_id`   INT         NOT NULL,
    `user_name` varchar(64) NOT NULL,
    `surname`   varchar(64) NOT NULL,
    PRIMARY KEY (`user_id`),
    FOREIGN KEY (`role_id`) REFERENCES repairagency.Roles (`role_id`) ON UPDATE NO ACTION
);

CREATE TABLE `Manufacturers`
(
    `manufacturer_id`   INT         NOT NULL AUTO_INCREMENT,
    `manufacturer_name` varchar(32) NOT NULL,
    PRIMARY KEY (`manufacturer_id`)
);

CREATE TABLE `Types`
(
    `type_id`   INT         NOT NULL AUTO_INCREMENT,
    `type_name` varchar(32) NOT NULL,
    PRIMARY KEY (`type_id`)
);

CREATE TABLE `Appliances`
(
    `appliance_id`    INT          NOT NULL AUTO_INCREMENT,
    `appliance_name`  varchar(64)  NOT NULL,
    `model`           varchar(64)  NOT NULL,
    `disrepair`       varchar(128) NOT NULL,
    `manufacturer_id` INT          NOT NULL,
    `type_id`         INT          NOT NULL,
    `user_id`         INT          NOT NULL,
    PRIMARY KEY (`appliance_id`),
    FOREIGN KEY (`manufacturer_id`) REFERENCES repairagency.Manufacturers (`manufacturer_id`),
    FOREIGN KEY (`type_id`) REFERENCES repairagency.Types (`type_id`),
    FOREIGN KEY (`user_id`) REFERENCES repairagency.Users (`user_id`) ON UPDATE CASCADE
);

CREATE TABLE `Orders`
(
    `order_id`     INT NOT NULL AUTO_INCREMENT,
    `title`        varchar(128),
    `price`        DECIMAL,
    `user_id`      INT NOT NULL,
    `appliance_id` INT NOT NULL,
    `master_id`    INT,
    `state`        BOOL,
    `status`       BOOL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES repairagency.Users (`user_id`) ON UPDATE CASCADE ,
    FOREIGN KEY (`master_id`) REFERENCES repairagency.Users (`user_id`) ON UPDATE CASCADE ,
    FOREIGN KEY (`appliance_id`) REFERENCES repairagency.Appliances (`appliance_id`) ON UPDATE CASCADE

);
CREATE TABLE `Responses`
(
    `response_id` INT      NOT NULL AUTO_INCREMENT,
    `text`        TINYTEXT NOT NULL,
    `user_id`     INT      NOT NULL,
    PRIMARY KEY (`response_id`),
    FOREIGN KEY (`user_id`) REFERENCES repairagency.Users (`user_id`) ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;

