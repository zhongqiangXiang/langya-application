package com.ideacome.services.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO{
	private int code;
	private String msg;
	private Object data;
	
	public static ResultVO newSuccess(int code,String msg,Object data){
		return new ResultVO(code,msg,data);
	}
}
