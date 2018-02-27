package com.leoyon.vote.api;

public final class CodeMessage {

	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	private CodeMessage(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message + "(" + code + ")";
	}

	public static CodeMessage wrap(int code, String message) {
		return new CodeMessage(code, message);
	}
	
	public static CodeMessage wrap(Error e) {
		return new CodeMessage(e.getValue(), e.getLabel());
	}
}
