/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : student_system

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 01/03/2024 10:40:16
*/
CREATE DATABASE IF NOT EXISTS student_system;
USE student_system;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级号',
  `num` int(255) NULL DEFAULT NULL COMMENT '班级人数',
  `grade` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '班级所属年级',
  `clazz_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1, 6, '3', '计算机1班');
INSERT INTO `clazz` VALUES (2, 3, '3', '计算机2班');
INSERT INTO `clazz` VALUES (4, 0, '3', '计算机3班');
INSERT INTO `clazz` VALUES (5, 0, '3', '计算机4班');
INSERT INTO `clazz` VALUES (7, 0, '2', '信安1班');

-- ----------------------------
-- Table structure for lesson
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson`  (
  `lid` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程号',
  `lname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `hours` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '课程学时',
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, '数据库系统概论', '48');
INSERT INTO `lesson` VALUES (3, '操作系统', '36');
INSERT INTO `lesson` VALUES (5, '数据结构', '48');
INSERT INTO `lesson` VALUES (6, '课程A', '42');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `mid` int(11) NOT NULL COMMENT '菜单号',
  `mname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单对应的页面url',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '学生管理', '/html/list/studentList.html');
INSERT INTO `menu` VALUES (2, '教师管理', '/html/list/teacherList.html');
INSERT INTO `menu` VALUES (3, '课程管理', '/html/list/lessonList.html');
INSERT INTO `menu` VALUES (4, '选课管理', '/html/list/xkList.html');
INSERT INTO `menu` VALUES (5, '授课管理', '/html/list/skList.html');
INSERT INTO `menu` VALUES (6, '班级管理', '/html/list/clazzList.html');
INSERT INTO `menu` VALUES (7, '用户管理', '/html/list/userList.html');
INSERT INTO `menu` VALUES (8, '成绩管理', '/html/score.html');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL COMMENT '角色号',
  `rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员');
INSERT INTO `role` VALUES (2, '教师');
INSERT INTO `role` VALUES (3, '学生');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色号',
  `mid` int(11) NULL DEFAULT NULL COMMENT '角色拥有菜单号'
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1);
INSERT INTO `role_menu` VALUES (1, 2);
INSERT INTO `role_menu` VALUES (1, 3);
INSERT INTO `role_menu` VALUES (1, 4);
INSERT INTO `role_menu` VALUES (1, 5);
INSERT INTO `role_menu` VALUES (1, 6);
INSERT INTO `role_menu` VALUES (1, 7);
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 2);
INSERT INTO `role_menu` VALUES (2, 3);
INSERT INTO `role_menu` VALUES (2, 4);
INSERT INTO `role_menu` VALUES (2, 5);
INSERT INTO `role_menu` VALUES (2, 6);
INSERT INTO `role_menu` VALUES (3, 1);
INSERT INTO `role_menu` VALUES (3, 4);
INSERT INTO `role_menu` VALUES (1, 8);
INSERT INTO `role_menu` VALUES (2, 8);
INSERT INTO `role_menu` VALUES (3, 8);

-- ----------------------------
-- Table structure for sk
-- ----------------------------
DROP TABLE IF EXISTS `sk`;
CREATE TABLE `sk`  (
  `sk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '授课记录号',
  `tid` int(11) NULL DEFAULT NULL COMMENT '授课教师号',
  `lid` int(11) NULL DEFAULT NULL COMMENT '授课课程号',
  PRIMARY KEY (`sk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk
-- ----------------------------
INSERT INTO `sk` VALUES (1, 1, 1);
INSERT INTO `sk` VALUES (3, 2, 3);
INSERT INTO `sk` VALUES (8, 9, 5);
INSERT INTO `sk` VALUES (9, 1, 6);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生号',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `cid` int(255) NULL DEFAULT NULL COMMENT '学生所在班级号',
  `sex` int(255) NULL DEFAULT NULL COMMENT '学生性别(1男,2女)',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100204 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (100101, '小红', 1, 2, '2023-12-08');
INSERT INTO `student` VALUES (100103, '小吴', 1, 1, '2023-11-28');
INSERT INTO `student` VALUES (100104, '小王', 1, 1, '2023-11-30');
INSERT INTO `student` VALUES (100105, '小全', 1, 1, '2023-12-19');
INSERT INTO `student` VALUES (100106, '小紫', 1, 2, '2023-12-06');
INSERT INTO `student` VALUES (100107, '小田', 1, 1, '2023-11-30');
INSERT INTO `student` VALUES (100201, '小芳', 2, 2, '2023-11-28');
INSERT INTO `student` VALUES (100202, '魏太君', 2, 1, '2023-12-14');
INSERT INTO `student` VALUES (100203, '小陆', 2, 1, '2023-12-07');

-- ----------------------------
-- Table structure for system_msg
-- ----------------------------
DROP TABLE IF EXISTS `system_msg`;
CREATE TABLE `system_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息标题',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息内容',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `send_uid` int(11) NULL DEFAULT NULL COMMENT '发布用户id',
  `receive_rid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收用户rids',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_msg
-- ----------------------------
INSERT INTO `system_msg` VALUES (1, '系统通知', '测试通知', '2023-12-21 15:32:24', 1, '1');
INSERT INTO `system_msg` VALUES (2, '系统通知2', '测试通知2', '2023-12-21 15:33:09', 1, '1');
INSERT INTO `system_msg` VALUES (3, '原石奖励', '奖励你1000000000原石', '2023-12-13 01:30:01', 1, '1');
INSERT INTO `system_msg` VALUES (4, '原周率', '原周率π = 3.14159 26535 89793 23846 26433 83279 50288 41971 69399 37510 58209 74944 59230 78164 06286 20899 86280 34825 34211 70679 82148 08651 32823 06647 09384 46095 50582 23172 53594 08128 48111 74502 84102 70193 85211 05559 64462 29489 54930 38196 44288 10975 66593 34461 28475 64823 37867 83165 27120 19091 45648 56692 34603 48610 45432 66482 13393 60726 02491 41273 72458 70066 06315 58817 48815 20920 96282 92540 91715 36436 78925 90360 01133 05305 48820 46652 13841 46951 94151 16094 33057 27036 57595 91953 09218 61173 81932 61179 31051 18548 07446 23799 62749 56735 18857 52724 89122 79381 83011 94912 98336 73362 44065 66430 86021 39494 63952 24737 19070 21798 60943 70277 05392 17176 29317 67523 84674 81846 76694 05132 00056 81271 45263 56082 77857 71342 75778 96091 73637 17872 14684 40901 22495 34301 46549 58537 10507 92279 68925 89235 42019 95611 21290 21960 86403 44181 59813 62977 47713 09960 51870 72113 49999 99837 29780 49951 05973 17328 16096 31859 50244 59455 34690 83026 42522 30825 33446 85035 26193 11881 71010 00313 78387 52886 58753 32083 81420 61717 76691 47303 59825 34904 28755 46873 11595 62863 88235 37875 93751 95778 18577 80532 17122 68066 13001 92787 66111 95909 21642 01989 38095 25720 10654 85863 27886 59361 53381 82796 82303 01952 03530 18529 68995 77362 25994 13891 24972 17752 83479 13151 55748 57242 45415 0695', '2023-12-05 01:31:50', 1, '1');
INSERT INTO `system_msg` VALUES (5, 'test', 'test', '2023-12-13 02:17:59', 1, '1');
INSERT INTO `system_msg` VALUES (6, 'test', 'test', '2023-12-06 02:18:11', 1, '1');
INSERT INTO `system_msg` VALUES (7, 'test', 'test', '2024-01-05 02:18:41', 1, '1');
INSERT INTO `system_msg` VALUES (8, 'test', 'test', '2023-11-28 02:18:51', 1, '1');
INSERT INTO `system_msg` VALUES (11, '1', '1', '2023-12-22 13:37:24', 1, '1');
INSERT INTO `system_msg` VALUES (14, '全局消息', '这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试这是一个测试', '2023-12-22 14:01:49', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (15, '测试', '123', '2023-12-22 14:49:52', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (16, '1111', '12233', '2024-01-20 15:47:36', 1, '1,2');

-- ----------------------------
-- Table structure for system_msg_read
-- ----------------------------
DROP TABLE IF EXISTS `system_msg_read`;
CREATE TABLE `system_msg_read`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `msg_id` int(11) NULL DEFAULT NULL COMMENT '消息id',
  `status` int(11) NULL DEFAULT NULL COMMENT '未读0,已读1,删除2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_msg_read
-- ----------------------------
INSERT INTO `system_msg_read` VALUES (1, 1, 1, 1);
INSERT INTO `system_msg_read` VALUES (2, 1, 2, 1);
INSERT INTO `system_msg_read` VALUES (3, 1, 3, 1);
INSERT INTO `system_msg_read` VALUES (4, 1, 4, 0);
INSERT INTO `system_msg_read` VALUES (5, 1, 5, 2);
INSERT INTO `system_msg_read` VALUES (6, 1, 6, 1);
INSERT INTO `system_msg_read` VALUES (7, 1, 7, 0);
INSERT INTO `system_msg_read` VALUES (8, 1, 8, 0);
INSERT INTO `system_msg_read` VALUES (9, 1, 11, 2);
INSERT INTO `system_msg_read` VALUES (10, 1, 14, 0);
INSERT INTO `system_msg_read` VALUES (11, 2, 14, 0);
INSERT INTO `system_msg_read` VALUES (12, 5, 14, 0);
INSERT INTO `system_msg_read` VALUES (13, 15, 14, 0);
INSERT INTO `system_msg_read` VALUES (14, 18, 14, 0);
INSERT INTO `system_msg_read` VALUES (15, 19, 14, 0);
INSERT INTO `system_msg_read` VALUES (16, 20, 14, 0);
INSERT INTO `system_msg_read` VALUES (17, 21, 14, 0);
INSERT INTO `system_msg_read` VALUES (18, 22, 14, 0);
INSERT INTO `system_msg_read` VALUES (19, 23, 14, 0);
INSERT INTO `system_msg_read` VALUES (20, 24, 14, 0);
INSERT INTO `system_msg_read` VALUES (21, 25, 14, 0);
INSERT INTO `system_msg_read` VALUES (22, 1, 15, 0);
INSERT INTO `system_msg_read` VALUES (23, 2, 15, 0);
INSERT INTO `system_msg_read` VALUES (24, 5, 15, 0);
INSERT INTO `system_msg_read` VALUES (25, 15, 15, 0);
INSERT INTO `system_msg_read` VALUES (26, 18, 15, 0);
INSERT INTO `system_msg_read` VALUES (27, 19, 15, 0);
INSERT INTO `system_msg_read` VALUES (28, 20, 15, 0);
INSERT INTO `system_msg_read` VALUES (29, 21, 15, 0);
INSERT INTO `system_msg_read` VALUES (30, 22, 15, 0);
INSERT INTO `system_msg_read` VALUES (31, 23, 15, 0);
INSERT INTO `system_msg_read` VALUES (32, 24, 15, 0);
INSERT INTO `system_msg_read` VALUES (33, 25, 15, 0);
INSERT INTO `system_msg_read` VALUES (34, 1, 16, 1);
INSERT INTO `system_msg_read` VALUES (35, 5, 16, 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师号',
  `tname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `sex` int(255) NULL DEFAULT NULL COMMENT '性别(1男2女)',
  `birthday` date NULL DEFAULT NULL COMMENT '教师出生日',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '王老师', 1, '2023-11-09');
INSERT INTO `teacher` VALUES (2, '魏老师', 1, '2023-12-06');
INSERT INTO `teacher` VALUES (3, '钱老师', 1, '2023-12-06');
INSERT INTO `teacher` VALUES (4, '李老师', 2, '2023-12-27');
INSERT INTO `teacher` VALUES (9, '鲲老师', 1, '2023-12-14');

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term`  (
  `tid` int(11) NOT NULL COMMENT '学期号',
  `term_start` date NULL DEFAULT NULL COMMENT '学期起始日期',
  `term_end` date NULL DEFAULT NULL COMMENT '学期结束日期',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of term
-- ----------------------------
INSERT INTO `term` VALUES (1, '2023-03-01', '2023-06-26');
INSERT INTO `term` VALUES (2, '2023-09-01', '2024-01-20');
INSERT INTO `term` VALUES (3, '2024-03-01', '2024-06-26');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户号',
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '密码',
  `rid` int(11) NULL DEFAULT NULL COMMENT '用户所属角色号',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 'http://47.99.143.160/image\\1739554597111992320.png');
INSERT INTO `user` VALUES (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (5, 'teacher', 'e10adc3949ba59abbe56e057f20f883e', 2, NULL);
INSERT INTO `user` VALUES (15, '100101', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (18, '100103', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (19, '100201', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (20, '100104', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (21, '100105', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (22, '100106', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (23, '100107', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (24, '100202', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);
INSERT INTO `user` VALUES (25, '100203', 'e10adc3949ba59abbe56e057f20f883e', 3, NULL);

-- ----------------------------
-- Table structure for xk
-- ----------------------------
DROP TABLE IF EXISTS `xk`;
CREATE TABLE `xk`  (
  `xk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选课号',
  `sid` int(11) NULL DEFAULT NULL COMMENT '选课所属学生号',
  `sk_id` int(11) NULL DEFAULT NULL COMMENT '选课所属授课号',
  `term` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期',
  `score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`xk_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xk
-- ----------------------------
INSERT INTO `xk` VALUES (6, 100101, 1, '2023.09-2024.01', '88');
INSERT INTO `xk` VALUES (7, 100101, 8, '2023.03-2023.06', '78');
INSERT INTO `xk` VALUES (8, 100103, 1, '2023.09-2024.01', '77');
INSERT INTO `xk` VALUES (9, 100105, 1, '2023.09-2024.01', '56');
INSERT INTO `xk` VALUES (10, 100203, 9, '2024.03-2024.06', '暂无成绩');

SET FOREIGN_KEY_CHECKS = 1;
