create table `packaging`
(
   `id` long auto_increment primary key,
   `billno` VARCHAR(255) NOT NULL,
   `receiver` VARCHAR(255) NOT NULL,
   `phonenum` VARCHAR(255) NOT NULL,
   `status` VARCHAR(255) default `未取件`,
   `apptime` TIMESTAMP,
   `weight` int,
);