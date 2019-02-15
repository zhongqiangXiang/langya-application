package com.ideacome.common.db.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ideacome.common.db.service.DataSourceReaderService;
import com.ideacome.common.dto.HttpClientRes;
import com.ideacome.common.util.HttpClientUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataSourceReaderServiceImpl implements DataSourceReaderService{
	
	Properties properties;
	/**
	 * 访问本地的配置文件  
	 */
	/**
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
	**/
	/**
	 * 访问配置中心的配置文件
	 */
	{
		InputStream in = null;
		try{
			properties = new Properties();
			// 使用ClassLoader加载properties配置文件生成对应的输入流
	    	in = DataSourceReaderServiceImpl.class.getClassLoader().getResourceAsStream("dataSource.properties");
	    	// 使用properties对象加载输入流
			properties.load(in);
			String baseUrl = properties.getProperty("spring.config.url").endsWith("/") ? 
					properties.getProperty("spring.config.url") : (properties.getProperty("spring.config.url")+"/");
			String langyaConfUrl = baseUrl
					+ properties.getProperty("spring.application.name")+"-"
					+ properties.getProperty("dataSource.profile")
					+ properties.getProperty("dataSource.suffix");
			HttpClientRes configRes = HttpClientUtil.sendGet(langyaConfUrl, null);
			if(!configRes.isSuccess()) {
				throw new IOException(configRes.getMsg());
			}
			String contentStr = configRes.getDataJson();
			if(StringUtils.isNotBlank(contentStr)) {
				String[] confProps = contentStr.split("\n");
				if(confProps != null && confProps.length > 0) {
					for(String prop : confProps) {
						String[] keyVal = prop.split(": ");
						if(keyVal != null && keyVal.length == 2) {
							properties.put(keyVal[0].trim(), keyVal[1].trim());
						}else {
							throw new IOException("配置中心的参数格式有误！！！！");
						}
					}
				}else {
					throw new IOException("配置中心的参数为空！！！！");
				}
			}else {
				throw new IOException("配置中心的参数为空！！！！");
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error("失败消息：{}",e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally{
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("失败消息：{}",e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String getPropertiesValue(String key){
		log.info("DataSourceReaderServiceImpl.getPropertiesValue:参数-key-{},val-{}",key,properties.getProperty(key));
		return properties.getProperty(key);
	}
}
