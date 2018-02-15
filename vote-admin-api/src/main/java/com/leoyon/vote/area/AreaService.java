package com.leoyon.vote.area;

import java.util.List;

public interface AreaService {

	List<Area> find(FindAreaRequest rqst);

	void add(Area entity);

	void update(Area entity);

	List<Area> all();

}
