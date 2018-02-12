package com.leoyon.vote.user;

public class VerifyCode {
	public static final String KEY_NAME = "vote-verify-code-key";
	private String key;
	private String code;
	public String getKey() {
		return key;
	}
	public String getCode() {
		return code;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setCode(String code) {
		this.code = code;
	}	
}