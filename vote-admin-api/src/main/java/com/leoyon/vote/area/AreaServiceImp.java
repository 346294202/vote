package com.leoyon.vote.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.area.dao.AreaDao;

@Service
public class AreaServiceImp implements AreaService {
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> find(FindAreaRequest rqst) {
		return areaDao.find(rqst);
	}

	@Override
	public void add(Area entity) {
		areaDao.add(entity);
	}

	@Override
	public void update(Area entity) {
		areaDao.update(entity);
	}

	@Override
	public List<Area> all() {
		return areaDao.all();
	}

	@Override
	public void delete(Area entity) {
		areaDao.delete(entity);
	}

	@Override
	public int count(FindAreaRequest rqst) {
		return areaDao.count(rqst);
	}

}
