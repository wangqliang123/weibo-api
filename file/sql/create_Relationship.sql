CREATE TABLE `Relationship` (
  `followerId` bigint(20) NOT NULL,
  `followeeId` bigint(20) NOT NULL,
  PRIMARY KEY (`followerId`,`followeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8