package com.leoyon.vote.area.dao;

import java.util.List;

import com.leoyon.vote.AbstractResource;
import com.leoyon.vote.area.Area;
import com.leoyon.vote.area.FindAreaRequest;

public interface AreaDao {

	List<Area> find(FindAreaRequest rqst);

	void add(AbstractResource entity);

	void update(AbstractResource entity);

	List<Area> all();

}
