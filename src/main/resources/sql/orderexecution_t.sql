/*
Navicat MySQL Data Transfer

Source Server         : Training
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : trading

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-08-08 10:24:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orderexecution_t`
-- ----------------------------
DROP TABLE IF EXISTS `orderexecution_t`;
CREATE TABLE `orderexecution_t` (
  `executions_id` bigint(20) NOT NULL,
  `fills` int(11) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  `rejections` int(11) DEFAULT NULL,
  `actives` int(11) DEFAULT NULL,
  PRIMARY KEY (`executions_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orderdetail_t` (`order_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderexecution_t
-- ----------------------------
