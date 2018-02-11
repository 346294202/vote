package com.leoyon.vote.user;

import com.leoyon.vote.api.Passwords;

public class User {
	
	private Long id;
	private String mobile;
	private String password;	
	private String salt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public void setPassword(String password) throws Exception {
		this.password = Passwords.encode(password, salt);
	}

	public boolean matchPassword(String password) throws Exception {
		return Passwords.match(this.password, password, salt);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
