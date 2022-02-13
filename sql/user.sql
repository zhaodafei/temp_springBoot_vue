/*
 Navicat Premium Data Transfer

 Source Server         : 127_fei
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ry

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/02/2022 01:50:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `name_foo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户备用名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'dafei_01_aa', '123456', 'dafei@qq.com', '11111111', 'fooName_01');
INSERT INTO `user` VALUES (2, 'dafei_02', '123456', 'dafei_02@qq.com', '1111111122', 'fooName_02');
INSERT INTO `user` VALUES (3, 'dafei_03', '123456', 'dafei_03@qq.com', '1111111133', 'fooName_03');
INSERT INTO `user` VALUES (4, 'dafei_04', '123456', '123456@qq.com', '111111111111', 'fooName_04');
INSERT INTO `user` VALUES (5, 'dafei_05', '123456', '123456@qq.com', '111111111111', 'fooName_05');
INSERT INTO `user` VALUES (6, 'dafei_06', '123456', '123456@qq.com', '111111111111', 'fooName_06');

SET FOREIGN_KEY_CHECKS = 1;
