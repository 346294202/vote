package com.leoyon.vote.user.dao;

import java.util.List;

import com.leoyon.vote.user.FindSysUserRequest;
import com.leoyon.vote.user.SysUser;

public interface SysUserDao {

	SysUser getUser(String username);

	void addUser(SysUser user);
	
	SysUser getUserById(Long id);

	List<com.leoyon.vote.user.FindSysUserResponse> findUser(FindSysUserRequest request);

	void updateUser(SysUser user);

	void delete(SysUser entity);

	int count(FindSysUserRequest req);
}
