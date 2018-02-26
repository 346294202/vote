package com.leoyon.vote.area.dao;

import java.util.List;

import com.leoyon.vote.area.Area;
import com.leoyon.vote.area.FindAreaRequest;

public interface AreaDao {

	List<Area> find(FindAreaRequest rqst);

	void add(Area entity);

	void update(Area entity);

	List<Area> all();

	void delete(Area entity);

	int count(FindAreaRequest rqst);

}
