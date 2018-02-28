CREATE DATABASE IF NOT EXISTS eagle DEFAULT CHARACTER SET utf8 ;

USE eagle;

CREATE TABLE eagle_user (
	id BIGINT(18) NOT NULL AUTO_INCREMENT COMMENT '代理ID',
	user_id VARCHAR(18) NOT NULL DEFAULT '' COMMENT '用户ID',
	user_name VARCHAR(128) NOT NULL DEFAULT '' COMMENT '用户名',
	user_sex TINYINT NOT NULL COMMENT '用户性别',
	user_birth_year VARCHAR(4) NOT NULL DEFAULT '' COMMENT '出生年份yyyy',
	user_birth_month VARCHAR(2) NOT NULL DEFAULT '' COMMENT '出生月份MM',
	user_birth_day VARCHAR(2) NOT NULL DEFAULT '' COMMENT '出生日期dd',
	create_time TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
	update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (id),
	UNIQUE KEY uniq_eagle_user_user_id (user_id) USING BTREE,
	UNIQUE KEY uniq_eagle_user_id_name_sex (user_id,user_name,user_sex) USING BTREE,
	KEY index_eagle_user_year_month_day (user_birth_year,user_birth_month,user_birth_day) USING BTREE
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='用户信息';

INSERT INTO `eagle_user` (`user_id`, `user_name`, `user_sex`, `user_birth_year`, `user_birth_month`, `user_birth_day`, `create_time`) VALUES('20170509U0001','胡一','0','1990','01','04',CURRENT_TIMESTAMP);
INSERT INTO `eagle_user` (`user_id`, `user_name`, `user_sex`, `user_birth_year`, `user_birth_month`, `user_birth_day`, `create_time`) VALUES('20170509U0002','胡二','0','1991','02','05',CURRENT_TIMESTAMP);
INSERT INTO `eagle_user` (`user_id`, `user_name`, `user_sex`, `user_birth_year`, `user_birth_month`, `user_birth_day`, `create_time`) VALUES('20170509U0003','胡三','1','1988','03','06',CURRENT_TIMESTAMP);
INSERT INTO `eagle_user` (`user_id`, `user_name`, `user_sex`, `user_birth_year`, `user_birth_month`, `user_birth_day`, `create_time`) VALUES('20170509U0004','胡四','0','1987','04','07',CURRENT_TIMESTAMP);
INSERT INTO `eagle_user` (`user_id`, `user_name`, `user_sex`, `user_birth_year`, `user_birth_month`, `user_birth_day`, `create_time`) VALUES('20170509U0005','胡五','1','1991','05','08',CURRENT_TIMESTAMP);
