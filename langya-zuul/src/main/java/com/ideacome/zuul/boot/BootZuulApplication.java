package com.ideacome.zuul.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages={"com.ideacome.zuul"})
@EnableConfigurationProperties
public class BootZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootZuulApplication.class, args);
	}
}
