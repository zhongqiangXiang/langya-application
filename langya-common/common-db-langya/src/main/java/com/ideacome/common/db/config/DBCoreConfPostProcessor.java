package com.ideacome.common.db.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ideacome.common.db.props.LangyaDBConf;

@Component
public class DBCoreConfPostProcessor implements BeanPostProcessor{
	@Autowired
	private ApplicationContext context;

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		if("langyaDBConf".equals(arg1)) {
			LangyaDBConf langyaDBConf = (LangyaDBConf)arg0;
			BasicDataSource masterDBSource = (BasicDataSource)context.getBean("masterDataSource");
			BeanUtils.copyProperties(langyaDBConf.getMaster(), masterDBSource);
			
			BasicDataSource slaveDBSource = (BasicDataSource)context.getBean("slaveDataSource");
			BeanUtils.copyProperties(langyaDBConf.getSlave(), slaveDBSource);
		}
		return arg0;
	}
	
}
