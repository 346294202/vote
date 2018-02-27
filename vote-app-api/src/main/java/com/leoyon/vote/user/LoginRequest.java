package com.leoyon.vote.user;

import javax.validation.constraints.NotNull;

import com.leoyon.vote.validation.MobileNumber;

public class LoginRequest {

	
	@MobileNumber(message="错误的手机号")	
	private String mobile;
	
	@NotNull(message="密码不能为空")
	private String password;

	public LoginRequest() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}