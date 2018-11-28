package com.ideacome.conf.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class BootConfApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootConfApplication.class, args);
	}
}
