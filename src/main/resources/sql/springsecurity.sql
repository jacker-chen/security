/*
 Navicat Premium Data Transfer

 Source Server         : 3306-root
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : springsecurity

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/06/2021 16:46:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除(0.未删除;1.已删除)',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '2021-06-25 15:29:48', '2021-06-25 15:30:35', 0, NULL, 'all', '所有资源权限');
INSERT INTO `sys_menu` VALUES (2, '2021-06-25 15:30:27', '2021-06-25 15:31:19', 0, NULL, '/test/**', '测试接口所有资源');
INSERT INTO `sys_menu` VALUES (3, '2021-06-25 15:32:09', '2021-06-25 15:32:09', 0, NULL, '/hello/**', 'hello接口所有资源');
INSERT INTO `sys_menu` VALUES (4, '2021-06-29 16:10:26', '2021-06-29 16:20:40', 0, NULL, '/login*', '登录接口');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除(0.未删除;1.已删除)',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2021-06-25 15:25:35', '2021-06-25 15:25:35', 0, 'admin', 'admin', '超级管理员');
INSERT INTO `sys_role` VALUES (2, '2021-06-25 15:25:47', '2021-06-25 15:26:41', 0, 'admin', 'other', '其他');
INSERT INTO `sys_role` VALUES (3, '2021-06-25 15:26:55', '2021-06-25 15:26:55', 0, 'admin', 'user', '普通用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除(0.未删除;1.已删除)',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `role_id` int(32) NULL DEFAULT NULL COMMENT 'role id',
  `menu_id` int(32) NULL DEFAULT NULL COMMENT '菜单id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, '2021-06-25 15:33:47', '2021-06-25 15:33:47', 0, NULL, 1, 1, NULL);
INSERT INTO `sys_role_menu` VALUES (2, '2021-06-25 15:34:07', '2021-06-25 15:34:07', 0, NULL, 2, 2, NULL);
INSERT INTO `sys_role_menu` VALUES (3, '2021-06-25 15:34:13', '2021-06-25 15:34:13', 0, NULL, 3, 3, NULL);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除(0.未删除;1.已删除)',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `role_id` int(32) NULL DEFAULT NULL COMMENT 'roleid',
  `user_id` int(32) NULL DEFAULT NULL COMMENT 'userid',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, '2021-06-25 15:28:07', '2021-06-25 15:28:07', 0, NULL, 1, 3, NULL);
INSERT INTO `sys_role_user` VALUES (2, '2021-06-25 15:28:27', '2021-06-25 15:28:27', 0, NULL, 3, 1, NULL);
INSERT INTO `sys_role_user` VALUES (3, '2021-06-25 15:28:36', '2021-06-25 15:28:36', 0, NULL, 2, 2, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除(0.未删除;1.已删除)',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2021-06-21 11:17:53', '2021-06-23 16:37:09', 0, NULL, 'bjfn', '$2a$10$JbifvM6n5ojaIQv0aG1r6.ZtZWESiOMwY64c8nUi3wWPGcne13Vhu', NULL);
INSERT INTO `sys_user` VALUES (2, '2021-06-25 14:08:36', '2021-06-25 14:08:36', 0, '', 'test', '$2a$10$JbifvM6n5ojaIQv0aG1r6.ZtZWESiOMwY64c8nUi3wWPGcne13Vhu', '');
INSERT INTO `sys_user` VALUES (3, '2021-06-25 15:27:29', '2021-06-25 15:27:34', 0, NULL, 'admin', '$2a$10$JbifvM6n5ojaIQv0aG1r6.ZtZWESiOMwY64c8nUi3wWPGcne13Vhu', NULL);

SET FOREIGN_KEY_CHECKS = 1;
