package com.leoyon.vote.api;

public abstract class AbstractResponse<T> {
	
	private int code;
    private String error;
    
	protected AbstractResponse(int code, String error) {
		super();
		this.code = code;
		this.error = error;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getError() {
		return error;
	}   
	
	public void setCode(int code) {
		this.code = code;
	}

	public void setError(String error) {
		this.error = error;
	}

	public abstract T getData();
}
