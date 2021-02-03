DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT '0',
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT '0',
  `comment_count` int(255) DEFAULT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_type_id` (`type_id`),
  KEY `FK_user_id` (`user_id`),
  CONSTRAINT `FK_type_id` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `name` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `t_blog_tag`;
CREATE TABLE `t_blog_tag` (
  `tag_id` bigint(20) NOT NULL,
  `blog_id` varchar(255) NOT NULL,
  PRIMARY KEY (`tag_id`,`blog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `nickname` varchar(255) DEFAULT NULL,
 `email` varchar(255) DEFAULT NULL,
 `content` varchar(255) DEFAULT NULL,
 `avatar` varchar(255) DEFAULT NULL,
 `create_time` datetime DEFAULT NULL,
 `blog_id` bigint(20) DEFAULT NULL,
 `parent_comment_id` bigint(20) DEFAULT NULL,
 `admin_comment` bit(1) DEFAULT NULL,
 PRIMARY KEY (`id`) USING BTREE,
 KEY `FK_blog_id` (`blog_id`),
 CONSTRAINT `FK_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `nickname` varchar(255) DEFAULT NULL,
 `email` varchar(255) DEFAULT NULL,
 `content` varchar(255) DEFAULT NULL,
 `avatar` varchar(255) DEFAULT NULL,
 `create_time` datetime DEFAULT NULL,
 `parent_message_id` bigint(20) DEFAULT NULL,
 `admin_message` bit(1) NOT NULL,
 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `content` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_count`;
CREATE TABLE `t_count` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `count` int(255) DEFAULT NULL,
   `create_time` datetime(6) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;