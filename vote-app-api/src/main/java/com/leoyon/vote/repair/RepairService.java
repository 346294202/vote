package com.leoyon.vote.repair;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;

public interface RepairService {

	Collection<Repair> find(FindPagedRequest rqst);

	void add(Repair entity);
	
}
