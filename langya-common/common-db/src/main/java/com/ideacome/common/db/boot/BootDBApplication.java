package com.ideacome.common.db.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.ideacome.common.db.config"})
@PropertySource("classpath:application.properties")
public class BootDBApplication {
}
