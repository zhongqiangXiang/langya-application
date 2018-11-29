package com.ideacome.zuul.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableZuulProxy
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients(basePackages={"com.ideacome.zuul.consumer"})
@SpringBootApplication(scanBasePackages={"com.ideacome.zuul"})
@SpringCloudApplication
public class BootZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootZuulApplication.class, args);
	}
}
