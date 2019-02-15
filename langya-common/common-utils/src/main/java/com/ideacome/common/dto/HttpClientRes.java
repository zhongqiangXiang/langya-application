package com.ideacome.common.dto;

import lombok.Data;

/**
 * http请求结果信息类
 * @author zhuting
 *
 */
@Data
public class HttpClientRes {
    private Integer code;
    private String msg;
    private String dataJson;
    public boolean isSuccess() {
        if(this.code == ErrorEnum.success.getCode()) {
            return true;
        }
        return false;
    }
    public HttpClientRes(Integer code, String msg,String dataJson) {
        this.code = code;
        this.msg = msg;
        this.dataJson = dataJson;
    }
    public static HttpClientRes newSuccess(String dataJson) {
        return new HttpClientRes(ErrorEnum.success.getCode(), ErrorEnum.success.getMsg(),dataJson);
    }
    public static HttpClientRes newFailure(ErrorEnum errorEnum,String errorMsg) {
        return new HttpClientRes(ErrorEnum.success.getCode(), errorMsg,null);
    }
}
