/*
Navicat MySQL Data Transfer

Source Server         : Training
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : trading

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-08-08 10:23:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `fill_t`
-- ----------------------------
DROP TABLE IF EXISTS `fill_t`;
CREATE TABLE `fill_t` (
  `price` double(20,0) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `executions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`executions_id`),
  CONSTRAINT `executions_id` FOREIGN KEY (`executions_id`) REFERENCES `orderexecution_t` (`executions_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fill_t
-- ----------------------------
