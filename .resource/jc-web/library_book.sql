/*
Navicat MySQL Data Transfer

Source Server         : 10.35.104.126
Source Server Version : 50722
Source Host           : 10.35.104.126:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-01-07 10:07:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `library_book`
-- ----------------------------
DROP TABLE IF EXISTS `library_book`;
CREATE TABLE `library_book` (
  `book_id` bigint(20) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of library_book
-- ----------------------------
INSERT INTO `library_book` VALUES ('1', 'demoData', '1', '1', '2020-01-06 15:11:33', null);
