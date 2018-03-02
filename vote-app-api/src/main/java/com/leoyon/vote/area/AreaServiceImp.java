package com.leoyon.vote.area;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.area.dao.AreaDao;

@Service
public class AreaServiceImp implements AreaService {
	
	@Autowired
	private AreaDao dao;

	@Override
	public Collection<Area> all() {
		return dao.all();
	}

}
