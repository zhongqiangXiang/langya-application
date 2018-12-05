package com.ideacome.services.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // 开启事务管理机制
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.ideacome.services"})
public class BootServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootServicesApplication.class, args);
	}
	
}
