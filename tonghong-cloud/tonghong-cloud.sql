/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.7.28 : Database - tonghongcloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tonghongcloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `tonghongcloud`;

/*Table structure for table `chapter` */

DROP TABLE IF EXISTS `chapter`;

CREATE TABLE `chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `info` varchar(512) DEFAULT NULL,
  `video` varchar(128) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0未学习，1学习中，2已学完',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `chapter` */

insert  into `chapter`(`id`,`name`,`course_id`,`info`,`video`,`status`) values (1,'第一节',9,'简介','/course/1.mp4',0),(2,'第二节',9,'环境搭建','/course/2.mp4',0),(3,'第三节',9,'ui组件','/course/1.mp4',0),(4,'第四节',9,'api','/course/2.mp4',0);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师id',
  `attribute` int(11) DEFAULT NULL COMMENT '属性：公开课..',
  `face_img` varchar(255) DEFAULT NULL COMMENT '封面',
  `ks` int(11) DEFAULT NULL COMMENT '课时',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`teacher_id`,`attribute`,`face_img`,`ks`,`add_time`) values (1,'vue',1,1,'/files/wximages/courses/1.jpg',120,'2022-10-13 10:45:54'),(8,'bigdata',1,2,'/files/wximages/courses/2.jpg',160,'2022-10-13 15:48:17'),(9,'wechat',NULL,1,'/files/wximages/courses/3.jpg',200,'2022-10-13 14:42:46'),(13,'1231',NULL,1,'/files/壁纸精选 (8).jpg',2222,'2022-10-18 09:07:57'),(14,'999',NULL,1,'/files/壁纸精选 (10).jpg',120,'2022-10-18 09:10:11');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(128) DEFAULT NULL,
  `session_key` varchar(128) DEFAULT NULL,
  `nickname` varchar(128) DEFAULT NULL COMMENT '微信昵称',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `account` varchar(256) DEFAULT NULL COMMENT '账号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `openidunique` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='微信端客户表';

/*Data for the table `customer` */

insert  into `customer`(`id`,`openid`,`session_key`,`nickname`,`avatar_url`,`add_time`,`account`,`phone`) values (2,'osM3s0NU2qWPHh5-mDH9E0ruVzXw','qxlAUKd5RKh1T6Wha4EVZQ==','undefined','https://thirdwx.qlogo.cn/mmopen/vi_32/ZbXEXIRQYqzRTZ68xfFnyiaOPSERjRsEiaUmqjmmXAyLKK9u7t0CicGVJbkv4Lf9vALaSKQOWIaH2aJ8EPnibFeqmA/132','2022-10-14 11:47:54',NULL,NULL);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mail` varchar(128) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `photo` varchar(128) DEFAULT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`account`,`name`,`phone`,`mail`,`level`,`photo`,`add_time`) values (1,'teacher_li','小李','15999998888','teacher_li@sina.com',1,NULL,'2022-10-17 09:07:31');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
