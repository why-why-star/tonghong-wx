/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : tonghongcloud

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 28/10/2022 23:57:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_id` int(0) NULL DEFAULT NULL,
  `info` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `video` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT 0 COMMENT '0未学习，1学习中，2已学完',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES (18, '第一节', 32, 'PHP介绍', '/files/覃德将的个人介绍.mp4', 0);
INSERT INTO `chapter` VALUES (29, '第一节', 37, '课程介绍', '/files/覃德将的个人介绍.mp4', 0);
INSERT INTO `chapter` VALUES (32, '第二节', 37, '电到为止', '/files/WIN_20220608_20_13_22_Pro.mp4', 0);
INSERT INTO `chapter` VALUES (36, '第三节', 37, '入土为安', '/files/WIN_20220608_20_13_00_Pro.mp4', 0);
INSERT INTO `chapter` VALUES (37, '最后一节', 37, '起死回生', '/files/WIN_20220608_20_13_22_Pro.mp4', 0);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_id` int(0) NULL DEFAULT NULL COMMENT '老师id',
  `attribute` int(0) NULL DEFAULT NULL COMMENT '属性：公开课..',
  `face_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面',
  `ks` int(0) NULL DEFAULT NULL COMMENT '课时',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (32, 'PHP实训', 23, 1, '/files/Snipaste_2022-10-26_15-48-02.png', 50, '2022-10-27 15:38:20');
INSERT INTO `course` VALUES (33, 'Java实训plus', 24, 1, '/files/Snipaste_2022-08-21_21-20-32.png', 20, '2022-10-27 16:49:35');
INSERT INTO `course` VALUES (34, 'Python实训', 25, 2, '/files/Snipaste_2022-05-08_23-36-03.png', 50, '2022-10-27 18:45:09');
INSERT INTO `course` VALUES (37, '电击疗法助你戒除网瘾', 36, 2, '/files/Snipaste_2021-12-13_16-21-38.png', 100, '2022-10-28 21:23:12');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `session_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信昵称',
  `avatar_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `account` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `openidunique`(`openid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '微信端客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (57, NULL, NULL, '覃德将', 'https://thirdwx.qlogo.cn/mmopen/vi_32/yyOIkbICG7XytZADTXtnXxEpj1iaRx7zMmibUgMxCItvZ0rNo5JyY9B8icDH3GibY1piaTh6kfvPV1CWvScE347iaChg/132', '2022-10-26 11:52:10', NULL, NULL, '2', '1');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `userid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (43, '1', '2022-10-28 11:19:32', 57);
INSERT INTO `message` VALUES (44, '2', '2022-10-28 11:19:33', 57);
INSERT INTO `message` VALUES (45, '2', '2022-10-28 11:19:35', 57);
INSERT INTO `message` VALUES (46, '3', '2022-10-28 11:19:37', 57);
INSERT INTO `message` VALUES (47, '记得交作业啦', '2022-10-28 18:54:36', 57);
INSERT INTO `message` VALUES (48, '记得交学费了', '2022-10-28 18:58:19', 57);
INSERT INTO `message` VALUES (49, '1', '2022-10-28 21:46:50', 57);
INSERT INTO `message` VALUES (50, '1', '2022-10-28 21:46:54', 57);
INSERT INTO `message` VALUES (51, '1', '2022-10-28 21:46:58', 57);
INSERT INTO `message` VALUES (52, '2', '2022-10-28 21:48:07', 57);
INSERT INTO `message` VALUES (53, '1', '2022-10-28 21:52:06', 57);
INSERT INTO `message` VALUES (54, '我不服啊', '2022-10-28 21:52:12', 57);
INSERT INTO `message` VALUES (55, '记得交学费了', '2022-10-28 21:59:40', 57);
INSERT INTO `message` VALUES (56, '还好', '2022-10-28 23:02:42', 57);
INSERT INTO `message` VALUES (57, '差不多得了', '2022-10-28 23:52:43', 57);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `mail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `level` int(0) NULL DEFAULT NULL,
  `photo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (23, NULL, '覃德将', '123', 'qdj@163.com', 1, '/files/58.jpg', '2022-10-27 11:26:51');
INSERT INTO `teacher` VALUES (24, NULL, '梁家铭', '1234', 'ljm@163.com', 1, '/files/Snipaste_2022-08-27_00-30-19.png', '2022-10-27 11:27:13');
INSERT INTO `teacher` VALUES (25, NULL, '郑德文', '123', 'ljm@163.com', 1, '/files/Snipaste_2022-08-14_11-56-29.png', '2022-10-27 11:27:40');
INSERT INTO `teacher` VALUES (36, NULL, '雷电法王', '151515', 'yrx@qq.com', 1, '/files/Snipaste_2022-05-08_23-46-53.png', '2022-10-28 21:22:50');

SET FOREIGN_KEY_CHECKS = 1;
