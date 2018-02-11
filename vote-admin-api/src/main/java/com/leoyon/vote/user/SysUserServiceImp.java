package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.api.Token;

@Service
public class SysUserServiceImp implements SysUserService {
	
	@Autowired
	private com.leoyon.vote.user.dao.SysUserDao userDao;
	
	@Autowired
	private com.leoyon.vote.AppConfig appConfig;

	@Override
	public Token fetchToken(SysUser user) {
		return Token.build(user.getId(), appConfig.getTokenExpirSeconds());
	}

	@Override
	public SysUser get(String username) {
		return userDao.getUser(username);
	}

	@Override
	public SysUser add(String username, String password) throws Exception {
		SysUser user = new SysUser();
		//必须在setPassword之前
		user.setSalt(RandomStringUtils.randomAlphabetic(16));
		user.setUsername(username);
		user.setPassword(password);			
		userDao.addUser(user);
		return user;
	}

	@Override
	public SysUser get(Long id){		
		return userDao.getUserById(id);
	}


}
