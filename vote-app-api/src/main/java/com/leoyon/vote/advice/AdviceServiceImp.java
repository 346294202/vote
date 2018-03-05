package com.leoyon.vote.advice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.advice.dao.AdviceDao;

@Service
public class AdviceServiceImp implements AdviceService {
	
	@Autowired
	private AdviceDao dao;

	@Override
	public Collection<Advice> find(FindPagedRequest rqst) {
		Collection<Advice> ret = dao.find(rqst);
		ret.forEach(i -> {
			i.setPictures(dao.getPictures(i.getId()));
		});
		return ret;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void add(Advice entity) {
		dao.add(entity);
		dao.addPictures(entity);
	}

}
