package com.ideacome.common.dto.base;

import lombok.Data;

/**
 * 返回的基本参数
 * @author zhuting
 *
 * @param <T>
 */
@Data
public class BaseRes<T> {
    private Integer code;
    private String msg;
    private T data;
    private Boolean success;
    
}
