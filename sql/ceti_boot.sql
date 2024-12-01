
-- ----------------------------
-- 1. 创建数据库
-- ----------------------------
CREATE DATABASE IF NOT EXISTS ceti_boot DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

-- ----------------------------
-- 2. 创建表 && 数据初始化
-- ----------------------------
use youlai_boot;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

      -- 开启事务
START TRANSACTION;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
                             `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
                             `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父节点id',
                             `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '父节点id路径',
                             `sort` smallint NULL DEFAULT 0 COMMENT '显示顺序',
                             `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE COMMENT '部门编号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '研发部门', 'RD001', 1, '0,1', 1, 1, 2, NULL, 2, '2022-04-19 12:46:37', 0);

-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
                             `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
                             `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常 0-停用)',
                             `data_scope` tinyint NULL DEFAULT NULL COMMENT '数据权限(0-所有数据 1-部门及子部门数据 2-本部门数据3-本人数据)',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人 ID',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE COMMENT '角色名称唯一索引',
                             UNIQUE INDEX `uk_code`(`code` ASC) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, NULL, '2021-05-21 14:56:51', NULL, '2018-12-23 16:00:00', 0);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, NULL, '2021-03-25 12:39:54', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
                             `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别((1-男 2-女 0-保密)',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `dept_id` int NULL DEFAULT NULL COMMENT '部门ID',
                             `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户头像',
                             `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
                             `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态((1-正常 0-禁用)',
                             `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `update_by` bigint NULL DEFAULT NULL COMMENT '修改人ID',
                             `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
                             `open_id` char(28) DEFAULT NULL COMMENT '微信 openid',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `login_name`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `role_id` bigint NOT NULL COMMENT '角色ID',
                                  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
                                  UNIQUE INDEX `uk_userid_roleid`(`user_id` ASC, `role_id` ASC) USING BTREE COMMENT '用户角色唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `parent_id` bigint NOT NULL COMMENT '父菜单ID',
                             `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
                             `type` tinyint NOT NULL COMMENT '菜单类型（1-菜单 2-目录 3-外链 4-按钮）',
                             `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称（Vue Router 中用于命名路由）',
                             `route_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径（Vue Router 中定义的 URL 路径）',
                             `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径（组件页面完整路径，相对于 src/views/，缺省后缀 .vue）',
                             `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '【按钮】权限标识',
                             `always_show` tinyint NULL DEFAULT 0 COMMENT '【目录】只有一个子路由是否始终显示（1-是 0-否）',
                             `keep_alive` tinyint NULL DEFAULT 0 COMMENT '【菜单】是否开启页面缓存（1-是 0-否）',
                             `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '显示状态（1-显示 0-隐藏）',
                             `sort` int NULL DEFAULT 0 COMMENT '排序',
                             `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
                             `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                             `params` json NULL COMMENT '路由参数',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ',
                            `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '类型编码',
                            `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '类型名称',
                            `status` tinyint(1) DEFAULT '0' COMMENT '状态(0:正常;1:禁用)',
                            `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                            `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                            `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
                            `is_deleted` tinyint DEFAULT '0' COMMENT '是否删除(1-删除，0-未删除)',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `idx_dict_code` (`dict_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典表';


-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联字典编码，与sys_dict表中的dict_code对应',
                                 `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典项值',
                                 `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典项标签',
                                 `tag_type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签类型，用于前端样式展示（如success、warning等）',
                                 `status` tinyint DEFAULT '0' COMMENT '状态（1-正常，0-禁用）',
                                 `sort` int DEFAULT '0' COMMENT '排序',
                                 `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
                                 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
                                 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                 `update_by` bigint DEFAULT NULL COMMENT '修改人ID',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典数据表';


