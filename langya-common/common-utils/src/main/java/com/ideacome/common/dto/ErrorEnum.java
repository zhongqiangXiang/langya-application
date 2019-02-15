package com.ideacome.common.dto;
/**
 * 错误信息枚举类
 * @author zhuting
 *
 */
public enum ErrorEnum {
    parameter_error(1998,"参数错误"),
    success(10000,"成功。"),
    failure(9999,"失败")
    ;
    private int code;
    private String msg;
    private ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    
}
