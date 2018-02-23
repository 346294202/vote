package com.leoyon.vote.user;

import java.util.List;

public interface UserService {

	List<User> find(FindUserRequest req);

	List<FindUserHouseResponse> findHouse(FindUserHouseRequest req);

	void updateHouse(UserHouse entity);

	int countHouse(FindUserHouseRequest req);

	
}
