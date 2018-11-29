package com.ideacome.services.vo;

import java.io.Serializable;

public class GreetingVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String content;
	
	public GreetingVO() {
	}

	public GreetingVO(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
}
