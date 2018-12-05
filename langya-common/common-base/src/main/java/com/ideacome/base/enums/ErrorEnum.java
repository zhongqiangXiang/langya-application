package com.ideacome.base.enums;

/**
 * 这里的错误码会最终传递给前端，目前前端只判断 success 10000，其他都为错误，会直接使用 errorMsg 作为显示
 * 
 * @author laosao
 *
 */
public enum ErrorEnum implements BaseErrorEnum {
    success(10000, "成功"), failure(99999, "失败"), param_error(19998, "参数错误"), exception(19997, "异常"), session_timeout(
                    19995, "会话超时"), auth_error(30003, "验证码错误"), 

    user_not_login(50001, "用户未登录"), user_not_match(50002, "权限验证信息不匹配"), pwd_not_match(50003, "密码错误"),

    // 缺少参数异常
    PARAMETER_MISSING(40001, "缺少参数异常"),
    // 超时异常
    TIME_OUT(40002, "超时异常"),

    // POST QUOTE 报价后规则检测错误
    PREMIUM_FEEDBACK_SCORE_FAIL(20001, "不符合平台的业务规则！"),

    // 权限
    NO_PERMISSION(60001, "没有访问权限"), TOKEN_EXPIRED(19999, "缓存过期")
    ;

    private Integer errorCode;
    private String errorMSG;

    private ErrorEnum(Integer errorCode, String errorMSG) {
        this.errorCode = errorCode;
        this.errorMSG = errorMSG;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMSG() {
        return errorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        this.errorMSG = errorMSG;
    }

    @Override
    public Integer getKey() {
        return errorCode;
    }

    @Override
    public String getValue() {
        return errorMSG;
    }
}
