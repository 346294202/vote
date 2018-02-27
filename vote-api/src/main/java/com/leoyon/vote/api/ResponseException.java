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

	private int code;
	private String error;

	public String getError() {
		return error;
	}

	public int getCode() {
		return code;
	}
	
	public ResponseException(int code, String error, Throwable cause) {
		super(error, cause);
		this.code = code;
		this.error = error;
	}
	
	public ResponseException(int code, String error) {
		this(code, error, null);
	}
	
	public ResponseException(String error) {
		this(Error.UNKNOWN_EXCEPT.getValue(), error, null);
	}

	public ResponseException(Error code) {
		this(code, null);
	}
	
	public ResponseException(Error code, Throwable cause) {
		this(code.getValue(), code.getLabel(), cause);
	}

	public ResponseException(CodeMessage smsSendFail, Throwable cause) {
		this(smsSendFail.getCode(), smsSendFail.getMessage(), cause);
	}

	public ResponseException(CodeMessage smsSendFail) {
		this(smsSendFail.getCode(), smsSendFail.getMessage(), null);
	}
	
}
