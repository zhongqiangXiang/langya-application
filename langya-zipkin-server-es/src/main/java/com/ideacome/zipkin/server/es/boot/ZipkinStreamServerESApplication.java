package com.ideacome.zipkin.server.es.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableZipkinStreamServer// //使用Stream方式启动ZipkinServer
@SpringBootApplication
public class ZipkinStreamServerESApplication {
	public static void main(String[] args) {
        SpringApplication.run(ZipkinStreamServerESApplication.class,args);
    }
}
