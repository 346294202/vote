package com.leoyon.vote.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.user.dao.UserDao;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> find(FindUserRequest reqst) {
		return userDao.find(reqst);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

}
