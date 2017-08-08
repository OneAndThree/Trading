/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : db_trading

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 08/08/2017 10:46:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderexecution_t
-- ----------------------------
DROP TABLE IF EXISTS `orderexecution_t`;
CREATE TABLE `orderexecution_t` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fills` int(11) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  `rejections` int(11) DEFAULT NULL,
  `actives` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orderdetail_t` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
