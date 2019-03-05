#1、基础微服务
CREATE DATABASE IF NOT EXISTS rewardPoint_base DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_base`.`tb_label`(
   `id` VARCHAR(20) NOT NULL,
   `labelname` VARCHAR(100),
   `state` VARCHAR(1),
   `count` BIGINT(20),
   `recommend` VARCHAR(1),
   `fans` BIGINT(20),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#2、招聘微服务
CREATE DATABASE IF NOT EXISTS rewardPoint_recruit DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#2.1 企业表
CREATE TABLE IF NOT EXISTS `rewardPoint_recruit`.`tb_enterprise`(
   `id` VARCHAR(20) NOT NULL,
   `name` VARCHAR(100) COMMENT '企业名称',
   `summary` VARCHAR(100) COMMENT '企业简介' ,
   `address` VARCHAR(100) COMMENT '企业地址',
   `labels` VARCHAR(200) COMMENT '标签列表， 用逗号隔开',
   `coordinate` VARCHAR(100) COMMENT '坐标',
   `ishot` VARCHAR(1) COMMENT '是否热门 0(非热门)，1(热门)',
   `logo` VARCHAR(100) COMMENT 'LOGO',
   `jobcount` int(4) COMMENT '职位数',
   `url` VARCHAR(200) COMMENT '企业网址',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#2.2 招聘表
CREATE TABLE IF NOT EXISTS `rewardPoint_recruit`.`tb_recruit`(
   `id` VARCHAR(20) NOT NULL,
   `jobname` VARCHAR(100) COMMENT '职位名称',
   `salary` VARCHAR(100) COMMENT '薪资范围' ,
   `condition` VARCHAR(100) COMMENT '经验要求',
   `education` VARCHAR(100) COMMENT '学历要求',
   `type` VARCHAR(1) COMMENT '任职方式',
   `address` VARCHAR(100) COMMENT '办公地址',
   `eid` VARCHAR(20) COMMENT '企业id',
   `createtime` timestamp COMMENT '创建日期',
   `state` VARCHAR(1) COMMENT '状态：0（关闭） 1（开启） 2（推荐）',
   `url` VARCHAR(200) COMMENT '原网址',
   `label` VARCHAR(100) COMMENT '标签',
   `content1` VARCHAR(1000) COMMENT '职位描述',
   `content2` VARCHAR(1000) COMMENT '职位要求',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3、问答微服务
CREATE DATABASE IF NOT EXISTS rewardPoint_qa DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#3.1 中间表tb_pl，关联problem & label
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_pl`(
   `problemid` VARCHAR(20) NOT NULL COMMENT '问题ID',
   `labelid` VARCHAR(20) NOT NULL COMMENT '标签ID',
   PRIMARY KEY (`problemid`,`labelid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3.2 problem 表
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_problem`(
   `id` VARCHAR(20) NOT NULL COMMENT '问题ID',
   `title` VARCHAR(100) COMMENT '标题',
   `content` TEXT COMMENT '内容',
   `createtime` DATETIME COMMENT '创建日期',
   `updatetime` DATETIME COMMENT '修改日期',
   `userid` VARCHAR(20) COMMENT '用户ID',
   `nickname` VARCHAR(100) COMMENT '昵称',
   `visits` BIGINT(20) COMMENT '浏览量',
   `thumbup` BIGINT(20) COMMENT '点赞数',
   `reply` BIGINT(20) COMMENT '回复数',
   `solve` VARCHAR(1) COMMENT '是否解决',
   `replyname` VARCHAR(100) COMMENT '回复人昵称',
   `replytime` DATETIME COMMENT '回复日期',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3.3 回复表 tb_reply
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_reply`(
   `id` VARCHAR(20) NOT NULL COMMENT '回复ID',
   `problemid` VARCHAR(20) COMMENT '问题ID',
   `content` TEXT COMMENT '回复内容',
   `createtime` DATETIME COMMENT '创建日期',
   `updatetime` DATETIME COMMENT '修改日期',
   `userid` VARCHAR(20) COMMENT '回复人ID',
   `nickname` VARCHAR(100) COMMENT '昵称',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#4、文章微服务
CREATE DATABASE IF NOT EXISTS rewardPoint_article DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_article`.`tb_article`(
   `id` VARCHAR(20) NOT NULL COMMENT '文章ID',
   `columnid` VARCHAR(100) COMMENT '专栏ID',
   `userid` VARCHAR(100) COMMENT '用户ID',
   `title` VARCHAR(100) COMMENT '文章标题',
   `content` TEXT COMMENT '文章内容',
   `image` VARCHAR(100) COMMENT '文章封面',
   `createtime` DATETIME COMMENT '发表日期',
   `updatetime` DATETIME COMMENT '修改日期',
   `ispublic` VARCHAR(1) COMMENT '是否公开，0(不公开), 1(公开)',
   `istop` VARCHAR(1) COMMENT '是否置顶，0(不置顶), 1(置顶)',
   `visits` INT(20) COMMENT '浏览量',
   `thumbup` INT(20) COMMENT '点赞数',
   `comment` INT(20) COMMENT '评论数',
   `state` VARCHAR(1) COMMENT '审核状态，0(未审核), 1(已审核)',
   `channelid` VARCHAR(20) COMMENT '所属频道，频道表ID',
   `url` VARCHAR(200) COMMENT 'url',
   `type` VARCHAR(1) COMMENT '文章类型，0(分享),1(专栏)',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#5、缓存处理

#6、用户
CREATE DATABASE IF NOT EXISTS rewardPoint_user DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_user`.`tb_user`(
   `id` VARCHAR(20) NOT NULL COMMENT '用户ID',
   `mobile` VARCHAR(100) COMMENT '手机号码',
   `password` VARCHAR(100) COMMENT '密码',
   `nickname` VARCHAR(100) COMMENT '用户昵称',
   `sex` VARCHAR(2) COMMENT '性别',
   `birthday` DATETIME COMMENT '出生年月日',
   `avator` VARCHAR(100) COMMENT '头像',
   `email` VARCHAR(100) COMMENT 'E-mail',
   `regdate` DATETIME COMMENT '注册日期',
   `updatedate` DATETIME COMMENT '修改日期',
   `lastdate` DATETIME COMMENT '最后登录日期',
   `online` BIGINT(20) COMMENT '在线时长（分钟）',
   `interest` VARCHAR(100) COMMENT '兴趣',
   `personality` VARCHAR(100) COMMENT '个性',
   `fanscount` INT(20) COMMENT '粉丝数',
   `followcount` INT(20) COMMENT '关注数',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rewardPoint_user`.`tb_admin`(
   `id` VARCHAR(20) NOT NULL COMMENT '管理员ID',
   `loginname` VARCHAR(100) COMMENT '管理员名称',
   `password` VARCHAR(100) COMMENT '密码',
   `state` VARCHAR(2) COMMENT '状态',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#交友服务
CREATE DATABASE IF NOT EXISTS rewardPoint_friend DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_friend`.`tb_friend`(
   `userid` VARCHAR(20) NOT NULL COMMENT '用户ID',
   `friendid` VARCHAR(20) COMMENT '好友id',
   `islike` VARCHAR(2) COMMENT '是否相互喜欢',
   PRIMARY KEY (`userid`, `friendid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rewardPoint_friend`.`tb_nofriend`(
   `userid` VARCHAR(20) NOT NULL COMMENT '用户ID',
   `friendid` VARCHAR(20) COMMENT '好友id',
   PRIMARY KEY (`userid`, `friendid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

