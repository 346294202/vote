package com.leoyon.vote.address;

import java.util.Collection;

public interface AddressService {

	Collection<Address> listAddressByUser(Long id);

	void delete(Address entity);

	void add(Address entity);

	void update(Address entity);

}
