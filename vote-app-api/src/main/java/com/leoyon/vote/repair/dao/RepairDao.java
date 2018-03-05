package com.leoyon.vote.repair.dao;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.repair.Repair;

public interface RepairDao {

	Collection<Repair> find(FindPagedRequest rqst);

	Collection<Picture> getPictures(Long id);

	void add(Repair entity);

	void addPictures(Repair entity);

}
