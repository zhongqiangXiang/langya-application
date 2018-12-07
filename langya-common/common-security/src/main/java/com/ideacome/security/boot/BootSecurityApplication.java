package com.ideacome.security.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.ideacome.security.config","com.ideacome.security.service"})
public class BootSecurityApplication {
}
