#1������΢����
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

#2����Ƹ΢����
CREATE DATABASE IF NOT EXISTS rewardPoint_recruit DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#2.1 ��ҵ��
CREATE TABLE IF NOT EXISTS `rewardPoint_recruit`.`tb_enterprise`(
   `id` VARCHAR(20) NOT NULL,
   `name` VARCHAR(100) COMMENT '��ҵ����',
   `summary` VARCHAR(100) COMMENT '��ҵ���' ,
   `address` VARCHAR(100) COMMENT '��ҵ��ַ',
   `labels` VARCHAR(200) COMMENT '��ǩ�б� �ö��Ÿ���',
   `coordinate` VARCHAR(100) COMMENT '����',
   `ishot` VARCHAR(1) COMMENT '�Ƿ����� 0(������)��1(����)',
   `logo` VARCHAR(100) COMMENT 'LOGO',
   `jobcount` int(4) COMMENT 'ְλ��',
   `url` VARCHAR(200) COMMENT '��ҵ��ַ',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#2.2 ��Ƹ��
CREATE TABLE IF NOT EXISTS `rewardPoint_recruit`.`tb_recruit`(
   `id` VARCHAR(20) NOT NULL,
   `jobname` VARCHAR(100) COMMENT 'ְλ����',
   `salary` VARCHAR(100) COMMENT 'н�ʷ�Χ' ,
   `condition` VARCHAR(100) COMMENT '����Ҫ��',
   `education` VARCHAR(100) COMMENT 'ѧ��Ҫ��',
   `type` VARCHAR(1) COMMENT '��ְ��ʽ',
   `address` VARCHAR(100) COMMENT '�칫��ַ',
   `eid` VARCHAR(20) COMMENT '��ҵid',
   `createtime` timestamp COMMENT '��������',
   `state` VARCHAR(1) COMMENT '״̬��0���رգ� 1�������� 2���Ƽ���',
   `url` VARCHAR(200) COMMENT 'ԭ��ַ',
   `label` VARCHAR(100) COMMENT '��ǩ',
   `content1` VARCHAR(1000) COMMENT 'ְλ����',
   `content2` VARCHAR(1000) COMMENT 'ְλҪ��',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3���ʴ�΢����
CREATE DATABASE IF NOT EXISTS rewardPoint_qa DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
#3.1 �м��tb_pl������problem & label
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_pl`(
   `problemid` VARCHAR(20) NOT NULL COMMENT '����ID',
   `labelid` VARCHAR(20) NOT NULL COMMENT '��ǩID',
   PRIMARY KEY (`problemid`,`labelid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3.2 problem ��
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_problem`(
   `id` VARCHAR(20) NOT NULL COMMENT '����ID',
   `title` VARCHAR(100) COMMENT '����',
   `content` TEXT COMMENT '����',
   `createtime` DATETIME COMMENT '��������',
   `updatetime` DATETIME COMMENT '�޸�����',
   `userid` VARCHAR(20) COMMENT '�û�ID',
   `nickname` VARCHAR(100) COMMENT '�ǳ�',
   `visits` BIGINT(20) COMMENT '�����',
   `thumbup` BIGINT(20) COMMENT '������',
   `reply` BIGINT(20) COMMENT '�ظ���',
   `solve` VARCHAR(1) COMMENT '�Ƿ���',
   `replyname` VARCHAR(100) COMMENT '�ظ����ǳ�',
   `replytime` DATETIME COMMENT '�ظ�����',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#3.3 �ظ��� tb_reply
CREATE TABLE IF NOT EXISTS `rewardPoint_qa`.`tb_reply`(
   `id` VARCHAR(20) NOT NULL COMMENT '�ظ�ID',
   `problemid` VARCHAR(20) COMMENT '����ID',
   `content` TEXT COMMENT '�ظ�����',
   `createtime` DATETIME COMMENT '��������',
   `updatetime` DATETIME COMMENT '�޸�����',
   `userid` VARCHAR(20) COMMENT '�ظ���ID',
   `nickname` VARCHAR(100) COMMENT '�ǳ�',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#4������΢����
CREATE DATABASE IF NOT EXISTS rewardPoint_article DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_article`.`tb_article`(
   `id` VARCHAR(20) NOT NULL COMMENT '����ID',
   `columnid` VARCHAR(100) COMMENT 'ר��ID',
   `userid` VARCHAR(100) COMMENT '�û�ID',
   `title` VARCHAR(100) COMMENT '���±���',
   `content` TEXT COMMENT '��������',
   `image` VARCHAR(100) COMMENT '���·���',
   `createtime` DATETIME COMMENT '��������',
   `updatetime` DATETIME COMMENT '�޸�����',
   `ispublic` VARCHAR(1) COMMENT '�Ƿ񹫿���0(������), 1(����)',
   `istop` VARCHAR(1) COMMENT '�Ƿ��ö���0(���ö�), 1(�ö�)',
   `visits` INT(20) COMMENT '�����',
   `thumbup` INT(20) COMMENT '������',
   `comment` INT(20) COMMENT '������',
   `state` VARCHAR(1) COMMENT '���״̬��0(δ���), 1(�����)',
   `channelid` VARCHAR(20) COMMENT '����Ƶ����Ƶ����ID',
   `url` VARCHAR(200) COMMENT 'url',
   `type` VARCHAR(1) COMMENT '�������ͣ�0(����),1(ר��)',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#5�����洦��

#6���û�
CREATE DATABASE IF NOT EXISTS rewardPoint_user DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_user`.`tb_user`(
   `id` VARCHAR(20) NOT NULL COMMENT '�û�ID',
   `mobile` VARCHAR(100) COMMENT '�ֻ�����',
   `password` VARCHAR(100) COMMENT '����',
   `nickname` VARCHAR(100) COMMENT '�û��ǳ�',
   `sex` VARCHAR(2) COMMENT '�Ա�',
   `birthday` DATETIME COMMENT '����������',
   `avator` VARCHAR(100) COMMENT 'ͷ��',
   `email` VARCHAR(100) COMMENT 'E-mail',
   `regdate` DATETIME COMMENT 'ע������',
   `updatedate` DATETIME COMMENT '�޸�����',
   `lastdate` DATETIME COMMENT '����¼����',
   `online` BIGINT(20) COMMENT '����ʱ�������ӣ�',
   `interest` VARCHAR(100) COMMENT '��Ȥ',
   `personality` VARCHAR(100) COMMENT '����',
   `fanscount` INT(20) COMMENT '��˿��',
   `followcount` INT(20) COMMENT '��ע��',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rewardPoint_user`.`tb_admin`(
   `id` VARCHAR(20) NOT NULL COMMENT '����ԱID',
   `loginname` VARCHAR(100) COMMENT '����Ա����',
   `password` VARCHAR(100) COMMENT '����',
   `state` VARCHAR(2) COMMENT '״̬',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

#���ѷ���
CREATE DATABASE IF NOT EXISTS rewardPoint_friend DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `rewardPoint_friend`.`tb_friend`(
   `userid` VARCHAR(20) NOT NULL COMMENT '�û�ID',
   `friendid` VARCHAR(20) COMMENT '����id',
   `islike` VARCHAR(2) COMMENT '�Ƿ��໥ϲ��',
   PRIMARY KEY (`userid`, `friendid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rewardPoint_friend`.`tb_nofriend`(
   `userid` VARCHAR(20) NOT NULL COMMENT '�û�ID',
   `friendid` VARCHAR(20) COMMENT '����id',
   PRIMARY KEY (`userid`, `friendid`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

