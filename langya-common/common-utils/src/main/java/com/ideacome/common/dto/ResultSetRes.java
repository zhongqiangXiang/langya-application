package com.ideacome.common.dto;

import com.ideacome.common.dto.base.BaseRes;

import lombok.Data;

@Data
public class ResultSetRes<F> {
    private boolean isSuccess;
    private String errorMsg;
    private BaseRes<F> data = new BaseRes<>();
    
    
}
