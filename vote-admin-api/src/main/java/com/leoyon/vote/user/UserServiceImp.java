package com.leoyon.vote.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.user.dao.UserDao;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> find(FindUserRequest req) {
		return userDao.find(req);
	}

	@Override
	public List<FindUserHouseResponse> findHouse(FindUserHouseRequest req) {
		return userDao.findHouse(req);
	}

	@Override
	public void updateHouse(UserHouse entity) {
		userDao.updateHouse(entity);
	}

	@Override
	public int countHouse(FindUserHouseRequest req) {
		return userDao.countHouse(req);
	}

}
