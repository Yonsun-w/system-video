# --------------------------------------------------------
# Host:                         39.100.139.202
# Server version:               8.0.22
# Server OS:                    Linux
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2021-07-02 17:22:04
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


# Dumping structure for table hars.t_appointment

drop database if exists hars;
create database hars;
use hars;


DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE IF NOT EXISTS `t_appointment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `appt_num` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预约号',
  `visit_id` bigint DEFAULT NULL COMMENT '预约id',
  `doc_id` bigint DEFAULT NULL COMMENT '医生id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `status` int DEFAULT NULL COMMENT '状态：已预约、已就诊、已取消、未就诊',
  PRIMARY KEY (`id`),
  KEY `visit_num` (`visit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_appointment: ~5 rows (approximately)
/*!40000 ALTER TABLE `t_appointment` DISABLE KEYS */;
INSERT INTO `t_appointment` (`id`, `appt_num`, `visit_id`, `doc_id`, `user_id`, `fee`, `status`) VALUES
	(1, '000001', 1, 1, 1, 14.40, 1),
	(2, '000002', 2, 6, 1, 19.20, 1),
	(3, '015731', 4, 1, 1, 14.40, 1),
	(4, '190642', 4, 1, 2, 14.40, 1),
	(5, '851690', 3, 1, 2, 14.40, 1);
/*!40000 ALTER TABLE `t_appointment` ENABLE KEYS */;


# Dumping structure for table hars.t_department
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE IF NOT EXISTS `t_department` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码',
  `hospital_code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医院代码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科室名称',
  `brief` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`),
  KEY `hospital_code` (`hospital_code`),
  KEY `code` (`code`),
  CONSTRAINT `hospital_code` FOREIGN KEY (`hospital_code`) REFERENCES `t_hospital` (`code`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_department: ~5 rows (approximately)
/*!40000 ALTER TABLE `t_department` DISABLE KEYS */;
INSERT INTO `t_department` (`id`, `code`, `hospital_code`, `name`, `brief`) VALUES
	(1, '0001', '21020303\r\n', '呼吸科', '备注'),
	(2, '0002', '21020303\r\n', '消化科', '备注'),
	(3, '0003', '21020303\r\n', '内分泌科', NULL),
	(4, '0004', '21020303\r\n', '心血管科', NULL),
	(5, '0005', '21020303\r\n', '骨外科', NULL);
/*!40000 ALTER TABLE `t_department` ENABLE KEYS */;


# Dumping structure for table hars.t_doctor
DROP TABLE IF EXISTS `t_doctor`;
CREATE TABLE IF NOT EXISTS `t_doctor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dept_code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '科室代码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '职称',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '挂号费',
  `brief` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`),
  KEY `dept_code` (`dept_code`),
  CONSTRAINT `dept_code` FOREIGN KEY (`dept_code`) REFERENCES `t_department` (`code`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_doctor: ~7 rows (approximately)
/*!40000 ALTER TABLE `t_doctor` DISABLE KEYS */;
INSERT INTO `t_doctor` (`id`, `dept_code`, `name`, `title`, `fee`, `brief`) VALUES
	(1, '0001', '马医生', '副主任', 14.40, '备注'),
	(2, '0001', '何医生', '副主任', 14.40, '备注'),
	(3, '0001', '李医生', '主治医师', 9.60, NULL),
	(4, '0002', '梁医生', '副主任', 14.40, NULL),
	(5, '0002', '唐医生', '主任', 19.20, NULL),
	(6, '0003', '王浩', '主任', 19.20, NULL),
	(7, '0003', '张医生', '主治医师', 9.60, NULL);
/*!40000 ALTER TABLE `t_doctor` ENABLE KEYS */;


# Dumping structure for table hars.t_hospital
DROP TABLE IF EXISTS `t_hospital`;
CREATE TABLE IF NOT EXISTS `t_hospital` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '代码',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '医院名称',
  `level` int DEFAULT NULL COMMENT '医院等级',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `brief` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`),
  KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_hospital: ~4 rows (approximately)
/*!40000 ALTER TABLE `t_hospital` DISABLE KEYS */;
INSERT INTO `t_hospital` (`id`, `code`, `name`, `level`, `address`, `brief`) VALUES
	(1, '21020201', '大连大学附属中山医院', 3, '辽宁省大连市中山区解放街6号  116001', '创建于1907年，2005年正式归属大连大学，更名为大连大学附属中山医院。医院是一所集预防、医疗、教学、科研、康复为一体的综合性大学附属医院。'),
	(2, '21020303\r\n', '大连医科大学附属第一医院\r\n21020303	大连医科大学附属第一医院\r\n', 3, '辽宁省大连市西岗区中山路222号  116011', '始建于1930年，是一所集医疗、教学、科研为一体的综合性三级医院，是大连市医保定点医院。'),
	(3, '21020405', '大连医科大学附属第二医院', 3, '大连市沙河口区中山路467号 116023', '始建于1958年，是一所集医疗、教学、科研于一体的综合性三级医院，是大连市医保定点医院。'),
	(4, '1008', '测试医院', 0, NULL, NULL);
/*!40000 ALTER TABLE `t_hospital` ENABLE KEYS */;


# Dumping structure for table hars.t_user
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '显示名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `registration_date` date DEFAULT NULL COMMENT '注册日期',
  `user_level` int DEFAULT NULL COMMENT '用户等级',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `user_name`, `name`, `password`, `registration_date`, `user_level`, `create_time`) VALUES
	(1, '张三', '张三', '123456', '2021-06-17', 1, '2021-06-17 10:44:08'),
	(2, 'admin', 'admin', '123', '2021-06-17', 0, '2021-06-17 06:17:31');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


# Dumping structure for table hars.t_visit_info
DROP TABLE IF EXISTS `t_visit_info`;
CREATE TABLE IF NOT EXISTS `t_visit_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `visit_num` int DEFAULT NULL COMMENT '出诊序号',
  `doc_id` bigint DEFAULT NULL COMMENT '医生id',
  `visit_datetime` datetime DEFAULT NULL COMMENT '出诊日期',
  `visit_period` int DEFAULT NULL COMMENT '出诊时段，单位分钟，如15表示每15分钟看一名患者',
  `max_num` int DEFAULT NULL COMMENT '最大人数',
  `status` int DEFAULT NULL COMMENT '出诊状态：有号源、无号源',
  PRIMARY KEY (`id`),
  KEY `visit_num` (`visit_num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# Dumping data for table hars.t_visit_info: ~4 rows (approximately)
/*!40000 ALTER TABLE `t_visit_info` DISABLE KEYS */;
INSERT INTO `t_visit_info` (`id`, `visit_num`, `doc_id`, `visit_datetime`, `visit_period`, `max_num`, `status`) VALUES
	(1, 1, 1, '2021-06-17 08:00:00', 15, 1, 2),
	(2, 2, 6, '2021-06-17 08:00:00', 15, 1, 1),
	(3, 3, 1, '2021-06-17 08:15:00', 15, 1, 2),
	(4, 4, 1, '2021-06-17 08:30:00', 15, 1, 2);
/*!40000 ALTER TABLE `t_visit_info` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
