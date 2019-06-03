package com.ideacome.common.db.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "jdbc")
@Data
public class LangyaDBConf {
	private JdbcConf master;
	private JdbcConf slave;
}
