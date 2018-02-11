package com.leoyon.vote.user;

import com.leoyon.vote.api.Token;

public interface SysUserService {

	Token fetchToken(SysUser user);

	SysUser get(String username);

	SysUser add(String username, String password) throws Exception;

	SysUser get(Long id);

}
