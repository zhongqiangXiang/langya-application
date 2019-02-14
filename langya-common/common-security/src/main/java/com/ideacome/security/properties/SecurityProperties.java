package com.ideacome.security.properties;

import lombok.Data;

/**
 * @Author: zhuting
 * @Description:    可配置的属性
 */
@Data
//@ConfigurationProperties(prefix = "my.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
}
