package com.leoyon.vote.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.leoyon.vote.AdminPojo;
import com.leoyon.vote.Passwords;

public class SysUser extends AdminPojo{
	
	private Long id;
	private String username;
	private String password;
	private String salt;
	private Boolean active;
	private String realName;
	private String depart;
	private String phone;
	private Date lastLoginTime;
	private Date lastLoginIp;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(Date lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = StringUtils.isBlank(salt) ? password : Passwords.encode(password, salt);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean matchPassword(String password) throws Exception {
		return Passwords.match(this.password, password, salt);
	}
}
