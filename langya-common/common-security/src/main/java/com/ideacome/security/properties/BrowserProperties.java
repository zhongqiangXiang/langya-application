package com.ideacome.security.properties;


import com.ideacome.security.properties.enums.LoginResponseType;
import lombok.Data;

/**
 * @Description:    浏览器登录认证相关配置
 */
@Data
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    /**
     * 默认登录页面
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /**
     * 默认登录方式
     */
    private LoginResponseType loginType = LoginResponseType.JSON;

    /**
     * 默认记住我的时长
     */
    private int rememberMeSeconds = 3600;

}
