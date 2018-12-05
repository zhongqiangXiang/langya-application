package com.ideacome.services.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 演示用的类
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class ResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private Object data;
	
	public static ResultVO newSuccess(int code,String msg,Object data){
		return new ResultVO(code,msg,data);
	}
	
	public static ResultVO newFailure(int code,String msg){
		return new ResultVO(code,msg,null);
	}
}
