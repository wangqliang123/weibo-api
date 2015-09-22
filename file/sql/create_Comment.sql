CREATE TABLE `Comment` (
  `id` bigint(20) NOT NULL,
  `createdAt` datetime NOT NULL,
  `mid` varchar(1024) DEFAULT NULL,
  `idstr` varchar(1024) DEFAULT NULL,
  `text` varchar(1024) DEFAULT NULL,
  `source` varchar(10240) DEFAULT NULL,
  `replycomment` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `statusId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB