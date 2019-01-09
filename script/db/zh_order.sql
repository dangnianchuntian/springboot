CREATE database if NOT EXISTS `zh_order` default character set utf8mb4 collate utf8mb4_general_ci;
use `zh_order`;

CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(32) DEFAULT NULL COMMENT '订单编码',
  `user_no` varchar(32) DEFAULT NULL COMMENT '用户编号',  
  `remark` varchar(128) DEFAULT NULL COMMENT '订单备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `param1` int(2) DEFAULT NULL COMMENT '预留字段1',
  `param2` int(4) DEFAULT NULL COMMENT '预留字段2',
  `param3` int(11) DEFAULT NULL COMMENT '预留字段3',
  `param4` varchar(32) DEFAULT NULL COMMENT '预留字段4',
  `param5` varchar(64) DEFAULT NULL COMMENT '预留字段5',
  `param6` varchar(128) DEFAULT NULL COMMENT '预留字段6',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_info_no` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

INSERT INTO `zh_order`.`order_info` (`no`, `user_no`, `remark`, `create_time`, `update_time`, `param1`, `param2`, `param3`, `param4`, `param5`, `param6`) VALUES ('o2378923', '167890345', '张晗的第一个订单', '2019-01-09 23:49:28', NULL, NULL, NULL, NULL, NULL, NULL, NULL);