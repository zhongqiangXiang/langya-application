package com.ideacome.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.ideacome.common.db.props.LangyaDBConf;

//@Configuration
public class ServicesCoreConfiguration {
	@Autowired
	private LangyaDBConf dbConf;
	
	@Bean
	public String getString() {
		System.out.println(JSON.toJSON(dbConf));
		
		return "hello,world";
	}
}
