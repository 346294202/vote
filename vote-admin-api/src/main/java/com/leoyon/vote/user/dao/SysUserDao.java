package com.leoyon.vote.user.dao;

import com.leoyon.vote.user.SysUser;

public interface SysUserDao {

	SysUser getUser(String username);

	void addUser(SysUser user);
	
	SysUser getUserById(Long id);
}
