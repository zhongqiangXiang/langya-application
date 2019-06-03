package com.ideacome.common.db.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.ideacome.common.db.config","com.ideacome.common.db.service.impl"
		,"com.ideacome.common.db.props"})
public class BootDBApplication {
}
