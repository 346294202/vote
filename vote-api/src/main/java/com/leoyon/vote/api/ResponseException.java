package com.leoyon.vote.api;

/**
 * 提供错误代码的异常，用于controller级别
 * @author WangJiang
 *
 */
public class ResponseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3124337240884405370L;

	private Error code;

	public Error getCode() {
		return code;
	}
	
	public ResponseException(Error code) {
		super(code.getLabel());
		this.code = code;
	}
	
	public ResponseException(Error code, Throwable cause) {
		super(code.getLabel(), cause);
		this.code = code;
	}
	
}
