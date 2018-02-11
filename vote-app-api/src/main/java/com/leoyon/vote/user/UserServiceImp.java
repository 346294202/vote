package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.api.Token;
import com.leoyon.vote.user.dao.UserDao;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private com.leoyon.vote.AppConfig appConfig;

	@Override
	public User add(String mobile, String password) throws Exception {
		
		User user = new User();
		//必须在setPassword之前
		user.setSalt(RandomStringUtils.randomAlphabetic(16));
		user.setMobile(mobile);
		user.setPassword(password);			
		userDao.addUser(user);
		return user;
	}

	@Override
	public Token fetchToken(User user) {		
		return Token.build(user.getId(), appConfig.getTokenExpirSeconds());
	}

	@Override
	public User get(String mobile) {
		return userDao.getUser(mobile);
	}

	@Override
	public User get(Long id){		
		return userDao.getUserById(id);
	}

}
