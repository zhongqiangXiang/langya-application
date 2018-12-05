package com.ideacome.common.db.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.ideacome.common.db.service.DataSourceReaderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataSourceReaderServiceImpl implements DataSourceReaderService{
	Properties properties;
	{
		properties = new Properties();
		// 使用ClassLoader加载properties配置文件生成对应的输入流
	    InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
	    // 使用properties对象加载输入流
	    try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("失败消息：{}",e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public String getPropertiesValue(String key){
		log.info("DataSourceReaderServiceImpl.getPropertiesValue:参数-key-{}",key);
		return properties.getProperty(key);
	}
}
