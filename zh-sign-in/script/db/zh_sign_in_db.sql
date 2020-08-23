CREATE database if NOT EXISTS `zh_sign_in` default character set utf8mb4 collate utf8mb4_general_ci;
use `zh_sign_in`;

CREATE TABLE `zh_sign_in` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bu_no` varchar(32) DEFAULT NULL COMMENT '业务编码',
  `customer_id` varchar(32) DEFAULT NULL COMMENT '签到用户编码',
  `sign_in_date` datetime DEFAULT NULL COMMENT '签到日期(单位精确到日)',
  `reward_money` int(11) DEFAULT NULL COMMENT '本次签到奖励金币个数',
  `continuite_day` int(2) DEFAULT '1' COMMENT '连续签到天数（A:7天内如果有断签从0开始 B:7天签满从0开始）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `param1` int(2) DEFAULT NULL COMMENT '预留字段1',
  `param2` int(4) DEFAULT NULL COMMENT '预留字段2',
  `param3` int(11) DEFAULT NULL COMMENT '预留字段3',
  `param4` varchar(20) DEFAULT NULL COMMENT '预留字段4',
  `param5` varchar(32) DEFAULT NULL COMMENT '预留字段5',
  `param6` varchar(64) DEFAULT NULL COMMENT '预留字段6',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_zh_sign_in_buno` (`bu_no`),
  UNIQUE KEY `uk_zh_sign_in_cid_signindate` (`customer_id`,`sign_in_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户签到表';

CREATE TABLE `zh_sign_in_hist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bu_no` varchar(32) DEFAULT NULL COMMENT '业务编码',
  `customer_id` varchar(32) DEFAULT NULL COMMENT '签到用户编码',
  `sign_in_date` datetime NULL DEFAULT NULL COMMENT '签到日期(单位精确到日)',
  `reward_money` int(11) DEFAULT NULL COMMENT '本次签到奖励金币个数',
  `continuite_day` int(2) DEFAULT '1' COMMENT '连续签到天数（A:7天内如果有断签从0开始 B:7天签满从0开始）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `param1` int(2) DEFAULT NULL COMMENT '预留字段1',
  `param2` int(4) DEFAULT NULL COMMENT '预留字段2',
  `param3` int(11) DEFAULT NULL COMMENT '预留字段3',
  `param4` varchar(20) DEFAULT NULL COMMENT '预留字段4',
  `param5` varchar(32) DEFAULT NULL COMMENT '预留字段5',
  `param6` varchar(64) DEFAULT NULL COMMENT '预留字段6',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_zh_sign_in_hist_cid_signindate` (`customer_id`,`sign_in_date`) USING BTREE,
  KEY `key_zh_sign_in_hist_buno` (`bu_no`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='用户签到历史表';
