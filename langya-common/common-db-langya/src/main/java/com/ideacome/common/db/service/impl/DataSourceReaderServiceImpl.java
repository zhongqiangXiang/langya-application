package com.ideacome.common.db.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ideacome.common.db.service.DataSourceReaderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataSourceReaderServiceImpl implements DataSourceReaderService{
	Properties properties;
	{
		InputStream in = null;
		InputStream in2 = null;
	    try {
	    	properties = new Properties();
	    	// 使用ClassLoader加载properties配置文件生成对应的输入流
	    	in = this.getClass().getClassLoader().getResourceAsStream("dataSource.properties");
	    	// 使用properties对象加载输入流
			properties.load(in);
			String profile = properties.getProperty("dataSource.profile");
			if(StringUtils.isEmpty(profile)){
			}else{
				properties.clear();
				in2 = this.getClass().getClassLoader().getResourceAsStream("dataSource-"+profile+".properties");
				properties.load(in2);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("失败消息：{}",e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally{
			if(in != null ){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					log.error("失败消息：{}",e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			}
			if(in2 != null){
				try {
					in2.close();
				} catch (IOException e) {
					e.printStackTrace();
					log.error("失败消息：{}",e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}
	
	@Override
	public String getPropertiesValue(String key){
		log.info("DataSourceReaderServiceImpl.getPropertiesValue:参数-key-{}",key);
		return properties.getProperty(key);
	}
}
