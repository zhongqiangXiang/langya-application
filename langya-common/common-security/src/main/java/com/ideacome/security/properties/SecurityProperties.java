package com.ideacome.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 15:20
 * @Description:    可配置的属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "my.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
