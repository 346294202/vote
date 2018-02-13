package com.leoyon.vote.user;

import java.util.List;

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
	public SysUser get(Long id){
		return userDao.getUserById(id);
	}

	@Override
	public List<SysUser> find(FindSysUserRequest req) {
		if(req.getPsize() < 1)
			req.setPsize(appConfig.getPageSize());
		req.setPage(req.getPage()*req.getPsize());
		return userDao.findUser(req);
	}

	@Override
	public void add(SysUser user) {
		userDao.addUser(user);		
	}

	@Override
	public void update(SysUser user) {
		userDao.updateUser(user);
	}


}
