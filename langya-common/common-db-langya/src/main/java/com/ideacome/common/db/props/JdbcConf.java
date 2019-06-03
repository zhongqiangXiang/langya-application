package com.ideacome.common.db.props;

import lombok.Data;

@Data
public class JdbcConf {
	private String driverClassName;
	private String url;
	private String username;
	private String password;
}
