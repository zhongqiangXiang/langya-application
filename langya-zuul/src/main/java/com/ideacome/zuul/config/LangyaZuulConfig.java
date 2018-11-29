package com.ideacome.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ideacome.zuul.filter.AccessFilter;

@Configuration
public class LangyaZuulConfig {
	
	@Bean
	public AccessFilter getAccessFilter(){
		return new AccessFilter();
	}
}
