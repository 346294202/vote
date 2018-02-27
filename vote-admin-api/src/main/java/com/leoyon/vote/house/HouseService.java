package com.leoyon.vote.house;

import java.util.List;

import com.leoyon.vote.api.VoteException;

public interface HouseService {

	List<House> find(FindHouseRequest reqst);

	void add(House house) throws VoteException;

	void update(House house) throws VoteException;

	void delete(House entity);

	int count(FindHouseRequest reqst);

}
