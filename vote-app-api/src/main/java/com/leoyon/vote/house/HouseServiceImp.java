package com.leoyon.vote.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.user.User;

@Service
public class HouseServiceImp implements HouseService{
	
	@Autowired
	private com.leoyon.vote.house.dao.HouseDao houseDao;

	@Override
	public List<House> listByOwner(User user) {
		return houseDao.listByUser(user.getId());
	}

}
