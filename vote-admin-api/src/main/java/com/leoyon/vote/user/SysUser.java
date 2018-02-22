package com.leoyon.vote.user;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.leoyon.doc.ApiParam;
import com.leoyon.doc.ApiParamIgnore;
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

	@ApiParam(desc="姓名")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@ApiParam(desc="部门")
	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@ApiParam(desc="手机")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ApiParam(desc="是否有效")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	@ApiParamIgnore
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@ApiParamIgnore
	public Date getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(Date lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@ApiParamIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiParam(desc="用户名")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ApiParam(desc="密码")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws Exception {
		this.password = StringUtils.isBlank(salt) ? password : Passwords.encode(password, salt);
	}

	@ApiParamIgnore
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
