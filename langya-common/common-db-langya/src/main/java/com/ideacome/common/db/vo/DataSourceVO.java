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
	private String poolTimeBetweenEvictionRunsMillis;
	private String poolMinEvictableIdleTimeMillis;
	private String poolValidationQuery;
	private String poolTestWhileIdle;
	private String poolTestOnBorrow;
	private String poolTestOnReturn;
	private String poolPreparedStatements;
}
