package com.ideacome.common.db.vo;

import lombok.Data;

@Data
public class DataSourceVO {
	private String masterDriverClassName;
	private String masterUrl;
	private String masterUsername;
	private String masterPassword;
	
	private String slaveDriverClassName;
	private String slaveUrl;
	private String slaveUsername;
	private String slavePassword;
	
	private String poolMaxActive;
	private String poolInitialSize;
	private String poolMaxWait;
	private String poolMinIdle;
}
