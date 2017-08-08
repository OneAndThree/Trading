/*
Navicat MySQL Data Transfer

Source Server         : Training
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : trading

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-08-08 10:24:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orderdetail_t`
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail_t`;
CREATE TABLE `orderdetail_t` (
  `order_id` bigint(20) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `side` varchar(20) NOT NULL,
  `symbol` varchar(20) NOT NULL,
  `trader_id` bigint(20) NOT NULL,
  `price` double(20,0) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `trader_id` (`trader_id`),
  CONSTRAINT `trader_id` FOREIGN KEY (`trader_id`) REFERENCES `trader_t` (`trader_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail_t
-- ----------------------------
