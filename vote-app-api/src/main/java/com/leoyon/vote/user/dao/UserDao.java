package com.leoyon.vote.user.dao;

import com.leoyon.vote.user.User;

public interface UserDao {

	Long addUser(User user);

	User getUser(String mobile);

	User getUserById(Long id);
	
}
