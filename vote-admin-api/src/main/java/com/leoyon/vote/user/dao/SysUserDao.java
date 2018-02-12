package com.leoyon.vote.user.dao;

import java.util.List;

import com.leoyon.vote.user.FindUserRequest;
import com.leoyon.vote.user.SysUser;

public interface SysUserDao {

	SysUser getUser(String username);

	void addUser(SysUser user);
	
	SysUser getUserById(Long id);

	List<SysUser> findUser(FindUserRequest request);

	void updateUser(SysUser user);
}
