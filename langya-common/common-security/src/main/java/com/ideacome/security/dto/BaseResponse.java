package com.ideacome.security.dto;

import lombok.Data;

@Data
public class BaseResponse {
    public BaseResponse(Object content) {
        this.content = content;
    }

    private Object content;
    private Integer code = 401;

}
