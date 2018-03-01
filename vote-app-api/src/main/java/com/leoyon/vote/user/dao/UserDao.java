package com.leoyon.vote.user.dao;

import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserRealInfo;

public interface UserDao {

	Long addUser(User user);

	User getUser(String mobile);

	User getUserById(Long id);

	void updateUserReal(UserRealInfo entity);
	
}
