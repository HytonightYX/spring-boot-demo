/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 10/12/2018 23:01:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 0 未读 1 在读 2 已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '追风筝的人', '卡勒德·胡赛尼 ', '2岁的阿富汗富家少爷阿米尔与仆人哈桑情同手足', '1');
INSERT INTO `book` VALUES (2, '解忧杂货店', '东野圭吾 ', '现代人内心流失的东西，这家杂货店能帮你找回', '2');
INSERT INTO `book` VALUES (3, '小王子', '安东尼·德·圣-埃克苏佩里 ', '小王子是一个超凡脱俗的仙童，他住在一颗只比他大一丁点儿的小行星上。陪伴他的是一朵他非常喜爱的小玫瑰花。但玫瑰花的虚荣心伤害了小王子对她的感情。', '2');
INSERT INTO `book` VALUES (4, '围城', '钱钟书', '很多人认为这是一部幽默作品。除了各具特色的人物语言之外', '1');
INSERT INTO `book` VALUES (6, '三体', '刘慈欣', '这是一本书', '0');
INSERT INTO `book` VALUES (9, 'Coaster', 'James', 'Coaster', '2');
INSERT INTO `book` VALUES (10, '书目10', '作者10', '书目10的描述', '0');
INSERT INTO `book` VALUES (11, '书目11', '作者11', '书目11的描述', '0');
INSERT INTO `book` VALUES (12, '书目12', '作者12', '书目12的描述', '1');
INSERT INTO `book` VALUES (13, '书目13', '作者13', '书目13的描述', '0');
INSERT INTO `book` VALUES (14, '书目14', '作者14', '书目14的描述', '2');
INSERT INTO `book` VALUES (15, '书目15', '作者15', '书目15的描述', '0');
INSERT INTO `book` VALUES (16, '书目16', '作者16', '书目16的描述', '0');
INSERT INTO `book` VALUES (17, '书目17', '作者17', '书目17的描述', '2');
INSERT INTO `book` VALUES (18, '书目18', '作者18', '书目18的描述', '1');
INSERT INTO `book` VALUES (19, '书目19', '作者19', '书目19的描述', '0');
INSERT INTO `book` VALUES (20, '书目20', '作者20', '书目20的描述', '1');
INSERT INTO `book` VALUES (21, '书目21', '作者21', '书目21的描述', '2');
INSERT INTO `book` VALUES (22, '书目22', '作者22', '书目22的描述', '2');
INSERT INTO `book` VALUES (23, '书目23', '作者23', '书目23的描述', '1');
INSERT INTO `book` VALUES (24, '书目24', '作者24', '书目24的描述', '1');
INSERT INTO `book` VALUES (25, '书目25', '作者25', '书目25的描述', '1');
INSERT INTO `book` VALUES (26, '书目26', '作者26', '书目26的描述', '0');
INSERT INTO `book` VALUES (27, '书目27', '作者27', '书目27的描述', '2');
INSERT INTO `book` VALUES (28, '书目28', '作者28', '书目28的描述', '0');
INSERT INTO `book` VALUES (29, '书目29', '作者29', '书目29的描述', '1');
INSERT INTO `book` VALUES (30, '书目30', '作者30', '书目30的描述', '1');
INSERT INTO `book` VALUES (31, '书目31', '作者31', '书目31的描述', '0');
INSERT INTO `book` VALUES (32, '书目32', '作者32', '书目32的描述', '2');
INSERT INTO `book` VALUES (33, '书目33', '作者33', '书目33的描述', '1');
INSERT INTO `book` VALUES (34, '书目34', '作者34', '书目34的描述', '2');
INSERT INTO `book` VALUES (35, '书目35', '作者35', '书目35的描述', '2');
INSERT INTO `book` VALUES (36, '书目36', '作者36', '书目36的描述', '2');
INSERT INTO `book` VALUES (37, '书目37', '作者37', '书目37的描述', '2');
INSERT INTO `book` VALUES (38, '书目38', '作者38', '书目38的描述', '1');
INSERT INTO `book` VALUES (39, '书目39', '作者39', '书目39的描述', '1');
INSERT INTO `book` VALUES (40, '书目40', '作者40', '书目40的描述', '0');
INSERT INTO `book` VALUES (41, '书目41', '作者41', '书目41的描述', '1');
INSERT INTO `book` VALUES (42, '书目42', '作者42', '书目42的描述', '2');
INSERT INTO `book` VALUES (43, '书目43', '作者43', '书目43的描述', '0');
INSERT INTO `book` VALUES (44, '书目44', '作者44', '书目44的描述', '1');
INSERT INTO `book` VALUES (45, '书目45', '作者45', '书目45的描述', '1');
INSERT INTO `book` VALUES (46, '书目46', '作者46', '书目46的描述', '1');
INSERT INTO `book` VALUES (47, '书目47', '作者47', '书目47的描述', '1');
INSERT INTO `book` VALUES (48, '书目48', '作者48', '书目48的描述', '2');
INSERT INTO `book` VALUES (49, '书目49', '作者49', '书目49的描述', '2');
INSERT INTO `book` VALUES (50, '书目50', '作者50', '书目50的描述', '2');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (51);

SET FOREIGN_KEY_CHECKS = 1;
