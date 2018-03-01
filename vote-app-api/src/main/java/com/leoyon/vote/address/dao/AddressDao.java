package com.leoyon.vote.address.dao;

import java.util.Collection;

import com.leoyon.vote.address.Address;

public interface AddressDao {

	Collection<Address> listAddressByUser(Long uid);

	void delete(Address entity);

	void clearDefByUser(long uid);

	void add(Address entity);

	void update(Address entity);

}
