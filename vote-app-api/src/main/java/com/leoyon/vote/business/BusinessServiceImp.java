package com.leoyon.vote.business;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.business.dao.BusinessDao;

@Service
public class BusinessServiceImp implements BusinessService{
	
	@Autowired
	private BusinessDao dao;

	@Override
	public Collection<Business> find(FindBizRequest rqst) {
		Collection<Business> ret = dao.find(rqst);
		ret.forEach(i -> {
			i.setPictures(dao.getPictures(i.getId()));
		});
		return ret;
	}

}
