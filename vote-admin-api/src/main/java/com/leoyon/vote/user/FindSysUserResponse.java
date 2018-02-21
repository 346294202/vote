package com.leoyon.vote.user;

import java.util.Collection;

import com.leoyon.vote.role.SysRole;

public class FindSysUserResponse extends SysUser {

	private Collection<SysRole> roles;

	public Collection<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<SysRole> roles) {
		this.roles = roles;
	}
	
}
