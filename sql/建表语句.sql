DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  login_name varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系人显示的名字',
  password varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系人的名字',
  username varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '联系人的昵称',
  PRIMARY KEY ('id')
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



-- 更新
CREATE DATABASE jdbctemplate;


CREATE TABLE tb_user (
    id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login_name varchar(20) COLLATE utf8_unicode_ci NOT NULL ,
    password varchar(20) COLLATE utf8_unicode_ci NOT NULL ,
    username varchar(20) COLLATE utf8_unicode_ci NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



-- 最新建表sql

CREATE DATABASE userweb;
DROP TABLE IF EXISTS use_info;

CREATE TABLE `use_info` (
                            `id` int(32) NOT NULL AUTO_INCREMENT,
                            `userName` varchar(255) DEFAULT NULL,
                            `sex` varchar(10) DEFAULT NULL,
                            `idNumber` varchar(25) DEFAULT NULL,
                            `phone` varchar(25) DEFAULT NULL,
                            `birth` date DEFAULT NULL,
                            `address` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8
