package com.leoyon.vote.user;

import java.util.List;

import com.leoyon.vote.api.Token;

public interface SysUserService {

	Token fetchToken(SysUser user);

	SysUser get(String username);

//	SysUser add(String username, String password) throws Exception;

	SysUser get(Long id);

	List<SysUser> find(FindSysUserRequest req);

	void add(SysUser user);

	void update(SysUser user);


}
