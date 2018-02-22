package com.leoyon.vote.house;

import java.util.List;

public interface HouseService {

	List<House> find(FindHouseRequest reqst);

	void add(House house);

	void update(House house);

	void delete(House entity);

}
