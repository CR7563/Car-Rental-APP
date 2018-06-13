/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : carmessagedb

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-04-20 17:45:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carmsg
-- ----------------------------
DROP TABLE IF EXISTS `carmsg`;
CREATE TABLE `carmsg` (
  `carId` int(50) NOT NULL AUTO_INCREMENT,
  `carNo` varchar(255) DEFAULT NULL,
  `carBrand` varchar(255) DEFAULT NULL,
  `carMoney` int(255) DEFAULT NULL,
  `carMessage` varchar(255) DEFAULT NULL,
  `carIsHave` varchar(255) DEFAULT NULL,
  `carIsSet` varchar(255) DEFAULT NULL,
  `carIsType` varchar(255) DEFAULT NULL,
  `carIsInsurance` varchar(255) DEFAULT NULL,
  `carTypeId` varchar(255) DEFAULT NULL,
  `carTypeName` varchar(255) DEFAULT NULL,
  `carTime` varchar(255) DEFAULT NULL,
  `carImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`carId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carmsg
-- ----------------------------
INSERT INTO `carmsg` VALUES ('12', '陕AU12365', '奔驰', '200', '高配', '否', '有', '手动', '有', '22', '经济型', '2018-02-07 16:09:54', 'car4.jpg');
INSERT INTO `carmsg` VALUES ('13', '陕AW66998', '宝马', '600', '中配', '是', '没有', '自动', '有', '23', '豪华型', '2018-02-07 16:10:32', 'car5.png');
INSERT INTO `carmsg` VALUES ('14', '陕AU56569', '起亚', '900', '高配', '是', '没有', '自动', '有', '23', '豪华型', '2018-02-07 16:11:17', 'car2.jpg');
INSERT INTO `carmsg` VALUES ('15', '陕AU88888', '保时捷', '1300', '中配', '是', '有', '自动', '有', '25', '豪华轿车', '2018-02-07 16:12:28', 'car10.jpg');
INSERT INTO `carmsg` VALUES ('16', '陕AU16666', '多多', '1600', '高配', '是', '有', '手动', '有', '23', '豪华型', '2018-02-07 16:13:01', 'car6.jpg');
INSERT INTO `carmsg` VALUES ('17', '陕AU89786', '多多', '800', '高配', '是', '没有', '手动', '有', '23', '豪华型', '2018-02-08 13:50:09', 'car1.jpg');
INSERT INTO `carmsg` VALUES ('18', '陕AU897861', '奔驰', '1600', '高配', '是', '没有', '手动', '有', '25', '豪华轿车', '2018-02-08 13:57:35', 'car1.jpg');
INSERT INTO `carmsg` VALUES ('20', '陕AU12365', null, '950', '高配', null, null, null, null, '28', '呆呆', '2018-02-11 14:57:31', 'car8.jpg');
INSERT INTO `carmsg` VALUES ('21', '陕AU12366', null, '650', '高配', null, null, null, null, '29', '呆呆1028', '2018-02-11 15:02:30', 'car10.jpg');
INSERT INTO `carmsg` VALUES ('22', '陕AU66666', null, '666', '高配', null, null, null, null, '30', '多多1116', '2018-02-11 15:16:23', 'car6.jpg');
INSERT INTO `carmsg` VALUES ('23', '陕AU12365', null, '1500', '高配', null, null, null, null, '21', 'suv', '2018-04-19 09:41:13', '1497354412956.jpg');

-- ----------------------------
-- Table structure for collectmsg
-- ----------------------------
DROP TABLE IF EXISTS `collectmsg`;
CREATE TABLE `collectmsg` (
  `collectId` int(50) NOT NULL AUTO_INCREMENT,
  `collectUserId` varchar(100) DEFAULT NULL,
  `collectUserName` varchar(255) DEFAULT NULL,
  `collectMessageId` varchar(100) DEFAULT NULL,
  `collectTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`collectId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectmsg
-- ----------------------------
INSERT INTO `collectmsg` VALUES ('11', '91', null, '12', '2018-02-11 15:17');

-- ----------------------------
-- Table structure for ordermsg
-- ----------------------------
DROP TABLE IF EXISTS `ordermsg`;
CREATE TABLE `ordermsg` (
  `orderId` int(50) NOT NULL AUTO_INCREMENT,
  `orderMessageId` varchar(50) DEFAULT NULL,
  `orderUserId` varchar(255) DEFAULT NULL,
  `orderUserName` varchar(255) DEFAULT NULL,
  `orderState` varchar(255) DEFAULT NULL,
  `orderCreateTime` varchar(100) DEFAULT NULL,
  `orderMoney` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordermsg
-- ----------------------------
INSERT INTO `ordermsg` VALUES ('121', '12', '91', 'pony', '1', '2018-04-20 17:44', '600');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `rid` int(50) NOT NULL AUTO_INCREMENT,
  `rUserId` varchar(50) NOT NULL,
  `rUserName` varchar(100) NOT NULL,
  `rMessageId` varchar(100) NOT NULL,
  `rReviewContent` varchar(300) NOT NULL,
  `rCreatime` varchar(100) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES ('79', '91', 'pony', '12', '我摸', '2018-02-12 15:25');
INSERT INTO `review` VALUES ('83', '91', 'pony', '12', '很不错的使用', '2018-02-12 15:47');
INSERT INTO `review` VALUES ('84', '91', 'pony', '12', '我会再来的', '2018-02-12 15:47');

-- ----------------------------
-- Table structure for scoremsg
-- ----------------------------
DROP TABLE IF EXISTS `scoremsg`;
CREATE TABLE `scoremsg` (
  `scoreId` int(50) NOT NULL AUTO_INCREMENT,
  `scoreMessageId` varchar(50) DEFAULT NULL,
  `scoreMessage` int(255) DEFAULT NULL,
  PRIMARY KEY (`scoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scoremsg
-- ----------------------------

-- ----------------------------
-- Table structure for typemsg
-- ----------------------------
DROP TABLE IF EXISTS `typemsg`;
CREATE TABLE `typemsg` (
  `typeId` int(50) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(255) DEFAULT NULL,
  `typeTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`typeId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of typemsg
-- ----------------------------
INSERT INTO `typemsg` VALUES ('21', 'suv', '2018-02-07 15:33');
INSERT INTO `typemsg` VALUES ('22', '经济型', '2018-02-07 15:34');
INSERT INTO `typemsg` VALUES ('23', '豪华型', '2018-02-07 15:34');
INSERT INTO `typemsg` VALUES ('24', '6-15座商户', '2018-02-07 15:34');
INSERT INTO `typemsg` VALUES ('25', '豪华轿车', '2018-02-07 15:34');
INSERT INTO `typemsg` VALUES ('26', '多多', '2018-02-08 13:49');
INSERT INTO `typemsg` VALUES ('27', '适用性', '2018-02-08 13:57');
INSERT INTO `typemsg` VALUES ('28', '呆呆', '2018-02-11 14:57');
INSERT INTO `typemsg` VALUES ('29', '呆呆1028', '2018-02-11 15:02');
INSERT INTO `typemsg` VALUES ('30', '多多1116', '2018-02-11 15:16');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(255) NOT NULL AUTO_INCREMENT,
  `uname` varchar(200) CHARACTER SET utf8 NOT NULL,
  `uphone` varchar(100) NOT NULL,
  `upswd` varchar(200) NOT NULL,
  `utime` varchar(300) NOT NULL,
  `uCode` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `uMoney` int(255) DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('91', 'pony', '15249243002', '123456', '2018-02-11 15:16', '19901116', '5480');
