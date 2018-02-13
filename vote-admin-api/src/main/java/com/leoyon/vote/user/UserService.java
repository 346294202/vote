package com.leoyon.vote.user;

import java.util.List;

public interface UserService {

	List<User> find(FindUserRequest reqst);

	void update(User user);

}
