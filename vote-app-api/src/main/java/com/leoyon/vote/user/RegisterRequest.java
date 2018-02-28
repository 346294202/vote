package com.leoyon.vote.user;

import javax.validation.constraints.NotNull;

public class RegisterRequest extends LoginRequest {

	@NotNull(message="验证码不能为空")
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
