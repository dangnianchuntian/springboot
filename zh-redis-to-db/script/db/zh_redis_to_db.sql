CREATE database if NOT EXISTS `zh_redis_to_db` default character set utf8mb4 collate utf8mb4_general_ci;
use `zh_redis_to_db`;

CREATE TABLE `zh_article_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bu_no` varchar(32) DEFAULT NULL COMMENT '业务编码',
  `customer_id` varchar(32) DEFAULT NULL COMMENT '用户编码',
  `type` int(2) DEFAULT '0' COMMENT '统计类型:0APP内文章阅读',
  `article_no` varchar(32) DEFAULT NULL COMMENT '文章编码',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `param1` int(2) DEFAULT NULL COMMENT '预留字段1',
  `param2` int(4) DEFAULT NULL COMMENT '预留字段2',
  `param3` int(11) DEFAULT NULL COMMENT '预留字段3',
  `param4` varchar(20) DEFAULT NULL COMMENT '预留字段4',
  `param5` varchar(32) DEFAULT NULL COMMENT '预留字段5',
  `param6` varchar(64) DEFAULT NULL COMMENT '预留字段6',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_zh_article_count_buno` (`bu_no`),
  KEY `key_zh_article_count_csign` (`customer_id`),
  KEY `key_zh_article_count_ano` (`article_no`),
  KEY `key_zh_article_count_rtime` (`read_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章阅读统计表';