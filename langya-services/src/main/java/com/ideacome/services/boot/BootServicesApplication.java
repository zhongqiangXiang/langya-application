package com.ideacome.services.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.ideacome.services"})
public class BootServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootServicesApplication.class, args);
	}
	
}
