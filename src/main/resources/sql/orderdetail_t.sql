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

 Date: 08/08/2017 10:46:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderdetail_t
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail_t`;
CREATE TABLE `orderdetail_t` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `side` varchar(20) NOT NULL,
  `symbol` varchar(20) NOT NULL,
  `trader_id` bigint(20) NOT NULL,
  `price` double(20,0) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `trader_id` (`trader_id`),
  CONSTRAINT `trader_id` FOREIGN KEY (`trader_id`) REFERENCES `trader_t` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
