package com.ideacome.security.properties;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 15:20
 * @Description:    可配置的属性
 */
@Data
@Configuration
//@ConfigurationProperties(prefix = "my.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
