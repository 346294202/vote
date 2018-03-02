package com.leoyon.vote.address;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.address.dao.AddressDao;

@Service
public class AddressServiceImp implements AddressService {
	
	@Autowired
	private AddressDao dao;

	@Override
	public Collection<Address> listAddressByUser(Long uid) {
		return dao.listAddressByUser(uid);
	}

	@Override
	public void delete(Address entity) {
		dao.delete(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void add(Address entity) {
		if(entity.getDef() != null && entity.getDef()) {
			dao.clearDefByUser(entity.getUserId());
		}
		dao.add(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void update(Address entity) {
		if(entity.getDef() != null && entity.getDef()) {
			dao.clearDefByUser(entity.getUserId());
		}
		dao.update(entity);
	}

}
