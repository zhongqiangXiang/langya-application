package com.ideacome.common.db.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DBCorePluginConfiguration {
	
	@Value(value = "${pool.maxActive}") String maxActive;
	
	@Bean("masterDataSource")
	public BasicDataSource getMasterDataSource(
			@Value(value = "${master.jdbc.driverClassName}") String driverClassName,
			@Value(value = "${master.jdbc.url}") String url,
			@Value(value = "${master.jdbc.username}") String userName,
			@Value(value = "${master.jdbc.password}") String password,
			@Value(value = "${pool.initialSize}") String initialSize,
			@Value(value = "${pool.maxWait}") String maxWait,
			@Value(value = "${pool.minIdle}") String minIdle
			){
		log.error("driverClassName-{},maxActive-{},maxWait-{}",driverClassName,maxActive,maxWait);
		BasicDataSource masterDataSource = new BasicDataSource();
		masterDataSource.setDriverClassName(driverClassName);
		masterDataSource.setUrl(url);
		masterDataSource.setUsername(userName);
		masterDataSource.setPassword(password);
		masterDataSource.setMaxActive(Integer.valueOf(maxActive));
		masterDataSource.setInitialSize(Integer.valueOf(initialSize));
		masterDataSource.setMaxWait(Long.valueOf(maxWait));
		masterDataSource.setMinIdle(Integer.valueOf(minIdle));
		initDataSource(masterDataSource);
		
		return masterDataSource;
	}
	
	@Bean("slaveDataSource")
	public BasicDataSource getSlaveDataSource(
			@Value("${slave.jdbc.driverClassName}") String driverClassName,
			@Value("${slave.jdbc.url}") String url,
			@Value("${slave.jdbc.username}") String userName,
			@Value("${slave.jdbc.password}") String password,
			@Value("${pool.maxActive}") String maxActive,
			@Value("${pool.initialSize}") String initialSize,
			@Value("${pool.maxWait}") String maxWait,
			@Value("${pool.minIdle}") String minIdle
			){
		BasicDataSource slaveDataSource = new BasicDataSource();
		slaveDataSource.setDriverClassName(driverClassName);
		slaveDataSource.setUrl(url);
		slaveDataSource.setUsername(userName);
		slaveDataSource.setPassword(password);
		slaveDataSource.setMaxActive(Integer.valueOf(maxActive));
		slaveDataSource.setInitialSize(Integer.valueOf(initialSize));
		slaveDataSource.setMaxWait(Long.valueOf(maxWait));
		slaveDataSource.setMinIdle(Integer.valueOf(minIdle));
		initDataSource(slaveDataSource);
		
		return slaveDataSource;
	}
	
	private void initDataSource(BasicDataSource basicDataSource){
		basicDataSource.setTimeBetweenEvictionRunsMillis(3000);
		basicDataSource.setMinEvictableIdleTimeMillis(300000);
		basicDataSource.setValidationQuery("SELECT 1");
		basicDataSource.setTestWhileIdle(true);
		basicDataSource.setTestOnBorrow(false);
		basicDataSource.setTestOnReturn(false);
		basicDataSource.setPoolPreparedStatements(true);
	}
	
	@Bean("dataSource")
	public DynamicDataSource getDynamicDataSource(BasicDataSource masterDataSource,BasicDataSource slaveDataSource){
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put("slave", slaveDataSource);
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
		
		return dynamicDataSource;
	}
	
	@Bean("transactionManager")
	public DataSourceTransactionManager getDataSourceTransactionManager(DynamicDataSource dataSource){
		DataSourceTransactionManager dataSourceTransationManager = new DataSourceTransactionManager();
		dataSourceTransationManager.setDataSource(dataSource);
		
		return dataSourceTransationManager;
	}
	
	@Bean("sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactoryBean(DynamicDataSource dataSource){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.ideacome.common.db.domain");
        bean.setConfigLocation(new ClassPathResource("mybatis/myBatisContext.xml"));
        
        try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Bean("mapperScaner")
	public MapperScannerConfigurer getMapperScannerConfigurer(SqlSessionFactory sqlSessionFactory){
		MapperScannerConfigurer mapperScaner = new MapperScannerConfigurer();
		mapperScaner.setBasePackage("com.ideacome.common.db.mapper");
		mapperScaner.setSqlSessionFactory(sqlSessionFactory);
		
		return mapperScaner;
	}
	
}
