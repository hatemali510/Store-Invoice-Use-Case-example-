CREATE TABLE `customer` (
	`id` int(20) NOT NULL,
	`name` varchar(1000) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `invoice` (
	`id` int(11) NOT NULL,
	`customer_id` int(11) NOT NULL,
	`total_price` FLOAT NOT NULL,
	`date` DATE NOT NULL,
	`status` int(2) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `inv_items` (
	`inv_id` int(20) NOT NULL,
	`item_id` int(20) NOT NULL,
	`quantity` int(20) NOT NULL
);

CREATE TABLE `item` (
	`id` int(20) NOT NULL,
	`name` varchar(500) NOT NULL,
	`price` FLOAT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
	`id` int(11) NOT NULL,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
	`id` int(20) NOT NULL,
	`name` varchar(1000) NOT NULL,
	`role_id` int(11) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `invoice` ADD CONSTRAINT `invoice_fk0` FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`);

ALTER TABLE `inv_items` ADD CONSTRAINT `inv_items_fk0` FOREIGN KEY (`inv_id`) REFERENCES `invoice`(`id`);

ALTER TABLE `inv_items` ADD CONSTRAINT `inv_items_fk1` FOREIGN KEY (`item_id`) REFERENCES `item`(`id`);

ALTER TABLE `user` ADD CONSTRAINT `user_fk0` FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`);

