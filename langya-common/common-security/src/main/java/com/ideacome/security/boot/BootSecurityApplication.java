package com.ideacome.security.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.ideacome.security.config","com.ideacome.security.service","com.ideacome.security.controller"})
public class BootSecurityApplication {
}
