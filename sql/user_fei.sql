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

 Date: 07/02/2022 01:51:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_fei
-- ----------------------------
DROP TABLE IF EXISTS `user_fei`;
CREATE TABLE `user_fei`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT 'user表关联id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_fei
-- ----------------------------
INSERT INTO `user_fei` VALUES (1, '管理员1', 1);
INSERT INTO `user_fei` VALUES (2, '管理员_02', 2);
INSERT INTO `user_fei` VALUES (3, '管理员_03', 3);
INSERT INTO `user_fei` VALUES (4, '管理员_04', 4);
INSERT INTO `user_fei` VALUES (5, '管理员_05', 5);
INSERT INTO `user_fei` VALUES (6, '管理员_06', 6);

SET FOREIGN_KEY_CHECKS = 1;
