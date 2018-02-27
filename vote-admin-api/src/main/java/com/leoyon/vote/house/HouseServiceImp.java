package com.leoyon.vote.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.api.VoteException;

@Service
public class HouseServiceImp implements HouseService{
	
	@Autowired
	private com.leoyon.vote.house.dao.HouseDao houseDao;

	@Override
	public List<House> find(FindHouseRequest reqst) {
		return houseDao.find(reqst);
	}

	@Override
	public void add(House house) throws VoteException {
		if(houseDao.checkAdd(house) > 0) {
			throw new VoteException("重复的房屋");
		}
		houseDao.add(house);
	}

	@Override
	public void update(House house) throws VoteException {
		if(houseDao.checkUpdate(house) > 0) {
			throw new VoteException("重复的房屋");
		}
		houseDao.update(house);
	}

	@Override
	public void delete(House entity) {
		houseDao.delete(entity);
	}

	@Override
	public int count(FindHouseRequest reqst) {
		return houseDao.count(reqst);
	}

}
