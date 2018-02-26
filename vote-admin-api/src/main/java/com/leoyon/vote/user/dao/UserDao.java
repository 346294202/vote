package com.leoyon.vote.user.dao;

import java.util.List;

import com.leoyon.vote.user.FindUserHouseRequest;
import com.leoyon.vote.user.FindUserHouseResponse;
import com.leoyon.vote.user.FindUserRequest;
import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserHouse;

public interface UserDao {

	List<User> find(FindUserRequest reqst);

	void update(User user);

	List<FindUserHouseResponse> findHouse(FindUserHouseRequest req);

	void updateHouse(UserHouse entity);

	int countHouse(FindUserHouseRequest req);

}
