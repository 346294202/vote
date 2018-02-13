package com.leoyon.vote.user.dao;

import java.util.List;

import com.leoyon.vote.user.FindUserRequest;
import com.leoyon.vote.user.User;

public interface UserDao {

	List<User> find(FindUserRequest reqst);

	void update(User user);

}
