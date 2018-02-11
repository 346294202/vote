package com.leoyon.vote.house.dao;

import java.util.List;

import com.leoyon.vote.house.House;

public interface HouseDao {

	List<House> listByUser(Long uid);

}
