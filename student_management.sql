
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
INSERT INTO `clazz` VALUES (1, 10, '3', '计算机1班');
INSERT INTO `clazz` VALUES (2, 1, '3', '计算机2班');
INSERT INTO `clazz` VALUES (4, 0, '3', '计算机3班');
INSERT INTO `clazz` VALUES (5, 0, '3', '计算机4班');
INSERT INTO `clazz` VALUES (6, 1, '3', '计算机5班');
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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES (1, '数据库系统概论', '48');
INSERT INTO `lesson` VALUES (3, '操作系统', '36');
INSERT INTO `lesson` VALUES (5, '数据结构', '32');
INSERT INTO `lesson` VALUES (6, '计算机组成原理', '48');
INSERT INTO `lesson` VALUES (7, '人工智能', '32');
INSERT INTO `lesson` VALUES (8, 'C++程序设计', '48');
INSERT INTO `lesson` VALUES (9, 'java程序设计', '32');
INSERT INTO `lesson` VALUES (10, '大学物理', '48');
INSERT INTO `lesson` VALUES (11, '高等数学', '52');
INSERT INTO `lesson` VALUES (12, '线性代数', '48');
INSERT INTO `lesson` VALUES (14, '软件工程（web）', '64');

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sk
-- ----------------------------
INSERT INTO `sk` VALUES (1, 1, 1);
INSERT INTO `sk` VALUES (3, 2, 3);
INSERT INTO `sk` VALUES (8, 9, 5);
INSERT INTO `sk` VALUES (10, 1, 7);
INSERT INTO `sk` VALUES (11, 3, 6);
INSERT INTO `sk` VALUES (12, 4, 8);
INSERT INTO `sk` VALUES (13, 13, 11);
INSERT INTO `sk` VALUES (14, 13, 7);
INSERT INTO `sk` VALUES (15, 3, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 100603 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (100101, '小红', 1, 2, '2023-12-08');
INSERT INTO `student` VALUES (100103, '小吴', 1, 1, '2023-11-28');
INSERT INTO `student` VALUES (100104, '小王', 1, 1, '2023-11-30');
INSERT INTO `student` VALUES (100105, '小全', 1, 1, '2023-12-19');
INSERT INTO `student` VALUES (100106, '小紫', 1, 2, '2023-12-06');
INSERT INTO `student` VALUES (100107, '小田', 1, 1, '2023-11-30');
INSERT INTO `student` VALUES (100108, '123', 1, 1, '2023-11-29');
INSERT INTO `student` VALUES (100109, '魏大', 1, 1, '2022-01-01');
INSERT INTO `student` VALUES (100110, '小泉', 1, 1, '2000-01-01');
INSERT INTO `student` VALUES (100111, '高太', 1, 1, '1999-12-30');
INSERT INTO `student` VALUES (100201, '小芳', 2, 2, '2023-11-28');
INSERT INTO `student` VALUES (100601, '魏小羊', 6, 1, '2000-08-01');

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `system_msg` VALUES (15, ' 吃雪', '全员持续', '2023-12-22 14:45:47', 1, '3');
INSERT INTO `system_msg` VALUES (16, '12y3891y2', 'dubwudbuiwbeibfwiebfiuw', '2023-12-22 15:12:33', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (17, '魏太君', '抓住魏太君', '2023-12-22 15:58:20', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (18, '你好', '你好', '2023-12-22 17:29:03', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (19, '你好', '你好！世界！', '2023-12-22 17:32:04', 1, '1,2,3');
INSERT INTO `system_msg` VALUES (20, '原石大礼包', '送你10000000000000000000000000000000000000000000000000000000000000000原石', '2023-12-22 17:38:18', 1, '1');
INSERT INTO `system_msg` VALUES (21, '1', '111', '2023-12-22 17:38:34', 1, '1,2');
INSERT INTO `system_msg` VALUES (22, '121', '1212', '2023-12-22 17:38:51', 1, '1,2');
INSERT INTO `system_msg` VALUES (23, '213123', '213123123', '2023-12-22 17:39:23', 1, '1');
INSERT INTO `system_msg` VALUES (24, '12321', '2122', '2023-12-22 17:39:40', 1, '1');
INSERT INTO `system_msg` VALUES (25, '测试', 'xxxx', '2023-12-22 17:39:58', 1, '1');

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
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_msg_read
-- ----------------------------
INSERT INTO `system_msg_read` VALUES (1, 1, 1, 2);
INSERT INTO `system_msg_read` VALUES (2, 1, 2, 2);
INSERT INTO `system_msg_read` VALUES (3, 1, 3, 2);
INSERT INTO `system_msg_read` VALUES (4, 1, 4, 2);
INSERT INTO `system_msg_read` VALUES (5, 1, 5, 2);
INSERT INTO `system_msg_read` VALUES (6, 1, 6, 2);
INSERT INTO `system_msg_read` VALUES (7, 1, 7, 2);
INSERT INTO `system_msg_read` VALUES (8, 1, 8, 2);
INSERT INTO `system_msg_read` VALUES (9, 1, 11, 2);
INSERT INTO `system_msg_read` VALUES (10, 1, 14, 2);
INSERT INTO `system_msg_read` VALUES (11, 2, 14, 0);
INSERT INTO `system_msg_read` VALUES (12, 5, 14, 0);
INSERT INTO `system_msg_read` VALUES (13, 15, 14, 1);
INSERT INTO `system_msg_read` VALUES (14, 18, 14, 0);
INSERT INTO `system_msg_read` VALUES (15, 19, 14, 0);
INSERT INTO `system_msg_read` VALUES (16, 20, 14, 0);
INSERT INTO `system_msg_read` VALUES (17, 21, 14, 0);
INSERT INTO `system_msg_read` VALUES (18, 22, 14, 0);
INSERT INTO `system_msg_read` VALUES (19, 23, 14, 0);
INSERT INTO `system_msg_read` VALUES (20, 24, 14, 0);
INSERT INTO `system_msg_read` VALUES (21, 25, 14, 0);
INSERT INTO `system_msg_read` VALUES (22, 2, 15, 0);
INSERT INTO `system_msg_read` VALUES (23, 15, 15, 1);
INSERT INTO `system_msg_read` VALUES (24, 18, 15, 0);
INSERT INTO `system_msg_read` VALUES (25, 19, 15, 0);
INSERT INTO `system_msg_read` VALUES (26, 20, 15, 0);
INSERT INTO `system_msg_read` VALUES (27, 21, 15, 0);
INSERT INTO `system_msg_read` VALUES (28, 22, 15, 0);
INSERT INTO `system_msg_read` VALUES (29, 24, 15, 0);
INSERT INTO `system_msg_read` VALUES (30, 28, 15, 0);
INSERT INTO `system_msg_read` VALUES (31, 29, 15, 0);
INSERT INTO `system_msg_read` VALUES (32, 30, 15, 0);
INSERT INTO `system_msg_read` VALUES (33, 31, 15, 2);
INSERT INTO `system_msg_read` VALUES (34, 1, 16, 2);
INSERT INTO `system_msg_read` VALUES (35, 2, 16, 0);
INSERT INTO `system_msg_read` VALUES (36, 5, 16, 0);
INSERT INTO `system_msg_read` VALUES (37, 15, 16, 1);
INSERT INTO `system_msg_read` VALUES (38, 18, 16, 0);
INSERT INTO `system_msg_read` VALUES (39, 19, 16, 0);
INSERT INTO `system_msg_read` VALUES (40, 20, 16, 0);
INSERT INTO `system_msg_read` VALUES (41, 21, 16, 0);
INSERT INTO `system_msg_read` VALUES (42, 22, 16, 0);
INSERT INTO `system_msg_read` VALUES (43, 24, 16, 0);
INSERT INTO `system_msg_read` VALUES (44, 28, 16, 0);
INSERT INTO `system_msg_read` VALUES (45, 29, 16, 0);
INSERT INTO `system_msg_read` VALUES (46, 30, 16, 0);
INSERT INTO `system_msg_read` VALUES (47, 31, 16, 1);
INSERT INTO `system_msg_read` VALUES (48, 1, 17, 2);
INSERT INTO `system_msg_read` VALUES (49, 2, 17, 0);
INSERT INTO `system_msg_read` VALUES (50, 5, 17, 0);
INSERT INTO `system_msg_read` VALUES (51, 15, 17, 1);
INSERT INTO `system_msg_read` VALUES (52, 18, 17, 0);
INSERT INTO `system_msg_read` VALUES (53, 19, 17, 0);
INSERT INTO `system_msg_read` VALUES (54, 20, 17, 0);
INSERT INTO `system_msg_read` VALUES (55, 21, 17, 0);
INSERT INTO `system_msg_read` VALUES (56, 22, 17, 0);
INSERT INTO `system_msg_read` VALUES (57, 24, 17, 0);
INSERT INTO `system_msg_read` VALUES (58, 28, 17, 0);
INSERT INTO `system_msg_read` VALUES (59, 30, 17, 0);
INSERT INTO `system_msg_read` VALUES (60, 31, 17, 0);
INSERT INTO `system_msg_read` VALUES (61, 33, 17, 0);
INSERT INTO `system_msg_read` VALUES (62, 1, 18, 1);
INSERT INTO `system_msg_read` VALUES (63, 2, 18, 0);
INSERT INTO `system_msg_read` VALUES (64, 5, 18, 0);
INSERT INTO `system_msg_read` VALUES (65, 15, 18, 0);
INSERT INTO `system_msg_read` VALUES (66, 18, 18, 0);
INSERT INTO `system_msg_read` VALUES (67, 19, 18, 0);
INSERT INTO `system_msg_read` VALUES (68, 20, 18, 0);
INSERT INTO `system_msg_read` VALUES (69, 21, 18, 0);
INSERT INTO `system_msg_read` VALUES (70, 22, 18, 0);
INSERT INTO `system_msg_read` VALUES (71, 28, 18, 0);
INSERT INTO `system_msg_read` VALUES (72, 30, 18, 0);
INSERT INTO `system_msg_read` VALUES (73, 31, 18, 0);
INSERT INTO `system_msg_read` VALUES (74, 33, 18, 0);
INSERT INTO `system_msg_read` VALUES (75, 34, 18, 0);
INSERT INTO `system_msg_read` VALUES (76, 1, 19, 1);
INSERT INTO `system_msg_read` VALUES (77, 2, 19, 0);
INSERT INTO `system_msg_read` VALUES (78, 5, 19, 0);
INSERT INTO `system_msg_read` VALUES (79, 15, 19, 0);
INSERT INTO `system_msg_read` VALUES (80, 18, 19, 0);
INSERT INTO `system_msg_read` VALUES (81, 19, 19, 0);
INSERT INTO `system_msg_read` VALUES (82, 20, 19, 0);
INSERT INTO `system_msg_read` VALUES (83, 21, 19, 0);
INSERT INTO `system_msg_read` VALUES (84, 22, 19, 0);
INSERT INTO `system_msg_read` VALUES (85, 28, 19, 0);
INSERT INTO `system_msg_read` VALUES (86, 30, 19, 0);
INSERT INTO `system_msg_read` VALUES (87, 31, 19, 0);
INSERT INTO `system_msg_read` VALUES (88, 33, 19, 0);
INSERT INTO `system_msg_read` VALUES (89, 34, 19, 0);
INSERT INTO `system_msg_read` VALUES (90, 1, 20, 1);
INSERT INTO `system_msg_read` VALUES (91, 1, 21, 2);
INSERT INTO `system_msg_read` VALUES (92, 5, 21, 0);
INSERT INTO `system_msg_read` VALUES (93, 1, 22, 2);
INSERT INTO `system_msg_read` VALUES (94, 5, 22, 0);
INSERT INTO `system_msg_read` VALUES (95, 1, 23, 2);
INSERT INTO `system_msg_read` VALUES (96, 1, 24, 1);
INSERT INTO `system_msg_read` VALUES (97, 1, 25, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '王老师', 1, '2023-11-09');
INSERT INTO `teacher` VALUES (2, '魏老师', 2, '2023-12-06');
INSERT INTO `teacher` VALUES (3, '钱老师', 1, '2023-12-06');
INSERT INTO `teacher` VALUES (4, '李老师', 1, '2023-12-27');
INSERT INTO `teacher` VALUES (9, '鲲老师', 1, '2023-12-14');
INSERT INTO `teacher` VALUES (12, '何老师', 2, '2024-01-04');
INSERT INTO `teacher` VALUES (13, '庄老师', 2, '2023-12-10');
INSERT INTO `teacher` VALUES (14, '马老师', 1, '2000-03-03');
INSERT INTO `teacher` VALUES (15, '魏小军', 1, '2000-01-01');

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
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `rid` int(11) NULL DEFAULT NULL COMMENT '用户所属角色号',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, 'http://47.99.143.160/image/1738105141678182400.jpeg');
INSERT INTO `user` VALUES (2, 'user', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (5, 'teacher', 'e10adc3949ba59abbe56e057f20f883e', 2, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (15, '100101', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (18, '100103', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (19, '100201', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (20, '100104', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (21, '100105', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (22, '100106', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (28, '100601', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (30, '100109', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (31, 'user_test', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (33, '100111', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');
INSERT INTO `user` VALUES (34, 'wxy', 'e10adc3949ba59abbe56e057f20f883e', 3, 'https://img.zcool.cn/community/01cfd95d145660a8012051cdb52093.png@1280w_1l_2o_100sh.png');

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xk
-- ----------------------------
INSERT INTO `xk` VALUES (6, 100101, 1, '2023.09-2024.01', '88');
INSERT INTO `xk` VALUES (7, 100101, 8, '2023.09-2024.01', '78');
INSERT INTO `xk` VALUES (8, 100103, 1, '2023.09-2024.01', '77');
INSERT INTO `xk` VALUES (9, 100105, 1, '2023.09-2024.01', '56');
INSERT INTO `xk` VALUES (11, 100103, 8, '2023.03-2023.06', '99');
INSERT INTO `xk` VALUES (12, 100106, 14, '2023.09-2024.01', '98');
INSERT INTO `xk` VALUES (13, 100105, 10, '2023.09-2024.01', '100.90');
INSERT INTO `xk` VALUES (14, 100111, 10, '2024.03-2024.06', '56');
INSERT INTO `xk` VALUES (15, 100101, 3, '2024.03-2024.06', '暂无成绩');

SET FOREIGN_KEY_CHECKS = 1;
