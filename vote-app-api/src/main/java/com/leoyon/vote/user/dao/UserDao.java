package com.leoyon.vote.user.dao;

import java.util.List;

import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserHouse;
import com.leoyon.vote.user.UserRealInfo;

public interface UserDao {

	Long addUser(User user);

	User getUser(String mobile);

	User getUserById(Long id);

	void updateUserReal(UserRealInfo entity);

	List<UserHouse> listHouse(User user);

	void setHouse(UserHouse entity);
	
}
