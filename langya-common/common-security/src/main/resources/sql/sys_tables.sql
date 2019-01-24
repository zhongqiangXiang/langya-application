CREATE TABLE `sys_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fun_code` varchar(200) NOT NULL COMMENT '功能code',
  `fun_name` varchar(64) NOT NULL COMMENT '功能名称',
  `fun_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '功能类型:0-目录;1-页面;2-按钮',
  `action_url` varchar(256) NOT NULL DEFAULT '' COMMENT '请求地址',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级功能id',
  `icon` varchar(40) DEFAULT NULL COMMENT '页面图标',
  `order_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序，按照order优先，id其次升序排序',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效1-有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `fun_code` (`fun_code`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8 COMMENT='功能表';

CREATE TABLE `sys_function_user_permisssion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `function_id` int(11) NOT NULL COMMENT '功能id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `permission_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '是否允许：0-否，1-是',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效1-有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `function_user` (`function_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8 COMMENT='功能用户权限表';

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL COMMENT '角色名',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='角色表';


CREATE TABLE `sys_role_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `function_id` int(11) NOT NULL COMMENT '功能id',
  `valid_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效,1-有效',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-删除,1-有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_user` (`role_id`,`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8 COMMENT='角色功能关系表';

CREATE TABLE `sys_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `valid_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效,1-有效',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-删除,1-有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_user` (`role_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8 COMMENT='角色用户关系表';

CREATE TABLE `sys_user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(64) NOT NULL COMMENT '昵称',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `real_name` varchar(64) NOT NULL COMMENT '真名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(64) NOT NULL DEFAULT '' COMMENT '手机号码',
  `creator` varchar(64) NOT NULL COMMENT '创建人用户名',
  `modifier` varchar(64) NOT NULL COMMENT '最后修改人用户名',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `sys_flag` tinyint(3) NOT NULL DEFAULT '1' COMMENT '0-失效1-有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10145 DEFAULT CHARSET=utf8 COMMENT='用户表';



-- 数据样例
insert into `sys_function` (`id`, `fun_code`, `fun_name`, `fun_type`, `action_url`, `parent_id`, `icon`, `order_num`, `description`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('387','loginTest','登陆测试','1','/login/test/','0',NULL,'0','登陆测试描述','admin1','admin2','2018-12-11 13:22:55','2018-12-11 13:22:55','1');
insert into `sys_function_user_permisssion` (`id`, `function_id`, `user_id`, `permission_flag`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('387','387','10144','1','admin1','admin2','2018-12-11 13:24:35','2018-12-11 13:24:35','1');
insert into `sys_role` (`id`, `role_name`, `description`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('122','管理员','管理员角色','adminor','adminor','2018-12-11 11:33:51','2018-12-11 11:33:51','1');
insert into `sys_role_function` (`id`, `role_id`, `function_id`, `valid_flag`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('387','122','387','1','admin1','admin2','2018-12-11 13:25:56','2018-12-11 13:25:56','1');
insert into `sys_role_user` (`id`, `role_id`, `user_id`, `valid_flag`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('387','122','10144','1','adminad','admin1','2018-12-11 11:35:19','2018-12-11 11:35:19','1');
insert into `sys_user` (`id`, `nick_name`, `user_name`, `real_name`, `password`, `email`, `mobile`, `creator`, `modifier`, `add_time`, `modify_time`, `sys_flag`) values('10144','admin_nick','admin','admin_real','e10adc3949ba59abbe56e057f20f883e','11111111@qq.com','111111111111','adminor_1','adminor','2018-12-11 11:32:18','2018-12-11 11:32:18','1');

