package com.leoyon.vote.repair;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.repair.dao.RepairDao;

@Service
public class RepairServiceImp implements RepairService {
	
	@Autowired
	private RepairDao dao;

	@Override
	public Collection<Repair> find(FindPagedRequest rqst) {
		Collection<Repair> ret = dao.find(rqst);
		ret.forEach(i -> {
			i.setPictures(dao.getPictures(i.getId()));
		});
		return ret;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void add(Repair entity) {
		dao.add(entity);
		dao.addPictures(entity);
	}
	
}
