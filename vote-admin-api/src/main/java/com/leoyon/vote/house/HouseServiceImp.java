package com.leoyon.vote.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImp implements HouseService{
	
	@Autowired
	private com.leoyon.vote.house.dao.HouseDao houseDao;

	@Override
	public List<House> find(FindHouseRequest reqst) {
		return houseDao.find(reqst);
	}

	@Override
	public void add(House house) {
		houseDao.add(house);
	}

	@Override
	public void update(House house) {
		houseDao.update(house);
	}

}
