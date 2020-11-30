/*
MySQL Backup
Database: shop
Backup Time: 2020-11-30 09:36:05
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `shop`.`goods`;
DROP TABLE IF EXISTS `shop`.`people`;
DROP TABLE IF EXISTS `shop`.`people_data`;
DROP TABLE IF EXISTS `shop`.`sales`;
DROP TABLE IF EXISTS `shop`.`sales_people`;
DROP TABLE IF EXISTS `shop`.`shopcar`;
DROP TABLE IF EXISTS `shop`.`stock`;
DROP TABLE IF EXISTS `shop`.`sum_stock`;
DROP TABLE IF EXISTS `shop`.`supplier`;
CREATE TABLE `goods` (
  `g_id` int(11) NOT NULL COMMENT '编号',
  `g_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `g_model` varchar(255) DEFAULT NULL COMMENT '型号',
  `g_price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `people` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `p_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `p_gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `p_wage` int(11) DEFAULT NULL COMMENT '工资',
  `p_position` varchar(255) DEFAULT NULL COMMENT '职务',
  `p_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`p_id`),
  KEY `p_name` (`p_name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
CREATE TABLE `people_data` (
  `p_id` int(11) NOT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_dang` varchar(255) DEFAULT NULL,
  `p_resume` varchar(255) DEFAULT NULL,
  `p_photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `p_name` (`p_name`),
  CONSTRAINT `p_id` FOREIGN KEY (`p_id`) REFERENCES `people` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `p_name` FOREIGN KEY (`p_name`) REFERENCES `people` (`p_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sales` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `danhao` int(11) NOT NULL,
  `gs_id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `selldate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gs_id` (`gs_id`),
  KEY `danhao` (`danhao`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
CREATE TABLE `sales_people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dp_id` int(11) DEFAULT NULL,
  `pd_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pd_id` (`pd_id`),
  KEY `dp_id` (`dp_id`),
  CONSTRAINT `dp_id` FOREIGN KEY (`dp_id`) REFERENCES `sales` (`danhao`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pd_id` FOREIGN KEY (`pd_id`) REFERENCES `people` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
CREATE TABLE `shopcar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gc_id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `pc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `stock` (
  `g_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `buydate` varchar(255) DEFAULT NULL,
  `buyprice` double(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `s_id` (`s_id`) USING BTREE,
  KEY `g_id` (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
CREATE TABLE `sum_stock` (
  `gg_id` int(11) DEFAULT NULL,
  `ss_id` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  KEY `gg_id` (`gg_id`),
  KEY `ss_id` (`ss_id`),
  CONSTRAINT `gg_id` FOREIGN KEY (`gg_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ss_id` FOREIGN KEY (`ss_id`) REFERENCES `supplier` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `supplier` (
  `s_id` int(11) NOT NULL,
  `s_name` varchar(255) DEFAULT NULL,
  `s_address` varchar(255) DEFAULT NULL,
  `s_phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
BEGIN;
LOCK TABLES `shop`.`goods` WRITE;
DELETE FROM `shop`.`goods`;
INSERT INTO `shop`.`goods` (`g_id`,`g_name`,`g_model`,`g_price`) VALUES (111111, 'qwe', '1', 1.00),(111201, '矿泉水', '01', 2.00),(123401, '矿泉水', '01', 2.00),(123456, 'asd', '2', 2.00),(211206, '猪肉脯', '06', 36.90),(221002, '豆角干', '02', 25.80),(222222, 'asd', '2', 2.00),(321125, '实木取暖器', '25', 28.00),(333333, 'test1', '05', 29.80),(411201, '坚果大礼包', '01', 138.00),(444444, '789', '05', 19.80),(999999, '测试', '05', 9.50);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`people` WRITE;
DELETE FROM `shop`.`people`;
INSERT INTO `shop`.`people` (`p_id`,`p_name`,`p_gender`,`p_wage`,`p_position`,`p_pwd`) VALUES (1, '张三', '男', 3500, '仓库管理', '123456'),(2, '李四', '男', 2000, '销售', '456789'),(3, '小赵', '女', 2000, '销售', '456789'),(4, '小何', '女', 3500, '仓库管理', '123456'),(7, '111', '111', 111, 'fghfgh', 'drgyes'),(8, 'dsfs', 'sdfsdf', 789, 'fghfgh', '789'),(10, 'cvb', '男', 111, '仓库管理', '456'),(12, '测试', '男', 123, '仓库管理', '123456'),(99, 'admin', 'null', 8000, '经理', 'root');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`people_data` WRITE;
DELETE FROM `shop`.`people_data`;
INSERT INTO `shop`.`people_data` (`p_id`,`p_name`,`p_dang`,`p_resume`,`p_photo`) VALUES (1, '张三', '是', 'asjkdbkgbagb', '1.jpg'),(2, '李四', '是', 'qweasdgnlag', '2.jpg'),(3, '小赵', '否', 'adbgkusabglnfj', '3.jpg'),(4, '小何', '是', 'asngkjnsgk', '6.jpg'),(7, '111', NULL, NULL, NULL),(8, 'dsfs', '786', 'ergsserg', '8.jpg'),(10, 'cvb', '是', 'agbkjreh', '10.jpg'),(12, '测试', '?', 'gverveheb', '12.jpg'),(99, 'admin', '是', NULL, NULL);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`sales` WRITE;
DELETE FROM `shop`.`sales`;
INSERT INTO `shop`.`sales` (`id`,`danhao`,`gs_id`,`num`,`price`,`selldate`) VALUES (1, 159147, 321125, 2, 56.00, '2020-10-29'),(2, 231797, 111111, 2, 2.00, '2020-11-16'),(3, 354152, 321125, 2, 56.00, '2020-10-18'),(4, 375110, 111111, 1, 1.00, '2020-10-21'),(5, 574491, 111111, 1, 1.00, '2020-10-21'),(6, 586482, 123456, 1, 2.00, '2020-10-23'),(7, 754364, 111201, 1, 2.00, '2020-10-19'),(8, 854428, 111201, 2, 4.00, '2020-10-29'),(9, 118941, 111111, 10, 10.00, '2020-11-16'),(10, 118941, 222222, 1, 2.00, '2020-10-29'),(11, 268647, 111111, 2, 2.00, '2020-10-30'),(12, 268647, 222222, 1, 2.00, '2020-10-30'),(13, 214305, 333333, 1, 29.80, '2020-11-19'),(14, 981310, 111111, 2, 2.00, '2020-11-23'),(21, 679041, 123401, 1, 2.00, '2020-11-19'),(24, 723563, 111201, 1, 2.00, '2020-11-19'),(25, 723563, 123401, 1, 2.00, '2020-11-19'),(26, 369725, 111201, 2, 4.00, '2020-11-23');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`sales_people` WRITE;
DELETE FROM `shop`.`sales_people`;
INSERT INTO `shop`.`sales_people` (`id`,`dp_id`,`pd_id`) VALUES (1, 231797, 3),(2, 354152, 3),(3, 375110, 99),(4, 574491, 99),(5, 586482, 3),(6, 754364, 2),(7, 854428, 2),(9, 159147, 2),(12, 118941, 3),(14, 268647, 2),(15, 214305, 1),(16, 981310, 2),(26, 679041, 2),(29, 723563, 2),(30, 369725, 99);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`shopcar` WRITE;
DELETE FROM `shop`.`shopcar`;
INSERT INTO `shop`.`shopcar` (`id`,`gc_id`,`num`,`price`,`pc_id`) VALUES (2, 111201, 1, 2.00, 2),(3, 123401, 1, 2.00, 2);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`stock` WRITE;
DELETE FROM `shop`.`stock`;
INSERT INTO `shop`.`stock` (`g_id`,`s_id`,`buydate`,`buyprice`,`stock`,`id`) VALUES (111201, 101, '2020-10-19', 1.00, 50, 1),(123401, 102, '2020-10-19', 1.00, 50, 2),(211206, 212, '2020-10-19', 15.00, 20, 3),(321125, 311, '2020-10-19', 10.00, 10, 4),(411201, 212, '2020-10-19', 80.00, 15, 5),(221002, 210, '2020-10-20', 10.00, 25, 6),(987654, 111, '2020-10-23', 50.00, 50, 7),(111111, 111, '2020-11-18', 19.80, 15, 8),(444444, 222, '2020-11-20', 29.80, 10, 13);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`sum_stock` WRITE;
DELETE FROM `shop`.`sum_stock`;
INSERT INTO `shop`.`sum_stock` (`gg_id`,`ss_id`,`stock`) VALUES (111201, 101, 40),(123401, 102, 44),(211206, 212, 20),(321125, 311, 7),(411201, 212, 15),(221002, 210, 20),(111111, 111, 30),(999999, 222, 0),(333333, 222, 28),(222222, 111, 7),(444444, 111, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `shop`.`supplier` WRITE;
DELETE FROM `shop`.`supplier`;
INSERT INTO `shop`.`supplier` (`s_id`,`s_name`,`s_address`,`s_phone`) VALUES (101, '娃哈哈', '杭州', 1234567),(102, '农夫山泉', '杭州', 6549871),(111, 'asd', 'qwe', 1234567),(210, '鸽鸽', '鹰潭', 3654821),(212, '三只松鼠', '芜湖', 4567892),(222, 'qwe', 'asd', 123456),(311, '骆驼', '长沙', 8521476),(888, 'test', '北京', 1234564),(999, 'test2', '北海', 654987);
UNLOCK TABLES;
COMMIT;
