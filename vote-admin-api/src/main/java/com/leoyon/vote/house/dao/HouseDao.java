package com.leoyon.vote.house.dao;

import java.util.List;

import com.leoyon.vote.house.FindHouseRequest;
import com.leoyon.vote.house.House;

public interface HouseDao {

	List<House> find(FindHouseRequest reqst);

	void add(House house);

	void update(House house);

	void delete(House entity);

	int count(FindHouseRequest reqst);

}
