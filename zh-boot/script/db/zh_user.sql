CREATE database if NOT EXISTS `zh_user` default character set utf8mb4 collate utf8mb4_general_ci;
use `zh_user`;

CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(32) DEFAULT NULL COMMENT '用户编码',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `name` varchar(128) DEFAULT NULL COMMENT '用户姓名',
  `sex` int(2) DEFAULT '0' COMMENT '0男  1女',  
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `param1` int(2) DEFAULT NULL COMMENT '预留字段1',
  `param2` int(4) DEFAULT NULL COMMENT '预留字段2',
  `param3` int(11) DEFAULT NULL COMMENT '预留字段3',
  `param4` varchar(32) DEFAULT NULL COMMENT '预留字段4',
  `param5` varchar(64) DEFAULT NULL COMMENT '预留字段5',
  `param6` varchar(128) DEFAULT NULL COMMENT '预留字段6',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_info_no` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户详情表';

INSERT INTO `zh_user`.`user_info` (`no`, `mobile`, `name`, `sex`, `create_time`, `update_time`, `param1`, `param2`, `param3`, `param4`, `param5`, `param6`) VALUES ('167890345', '17633201809', '张晗', '0', '2019-01-09 23:46:31', NULL, NULL, NULL, NULL, NULL, NULL, NULL);