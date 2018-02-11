package com.leoyon.vote.house;

import java.util.List;

import com.leoyon.vote.user.User;

public interface HouseService {

	List<House> listByOwner(User user);

}
