package com.leoyon.vote.house;

import java.util.List;

import com.leoyon.vote.api.ResponseException;

public interface HouseService {

	List<House> find(FindHouseRequest reqst);

	void add(House house) throws ResponseException;

	void update(House house) throws ResponseException;

	void delete(House entity);

	int count(FindHouseRequest reqst);

}
