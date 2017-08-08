/*
Navicat MySQL Data Transfer

Source Server         : Training
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : trading

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-08-08 10:25:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `trader_t`
-- ----------------------------
DROP TABLE IF EXISTS `trader_t`;
CREATE TABLE `trader_t` (
  `trader_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`trader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trader_t
-- ----------------------------
