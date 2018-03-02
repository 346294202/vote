package com.leoyon.vote.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.house.dao.HouseDao;

@Service
public class HouseServiceImp implements HouseService {
	
	@Autowired
	private HouseDao dao;

	@Override
	public House matchHouse(House entity) {
		return dao.matchHouse(entity);
	}

}
