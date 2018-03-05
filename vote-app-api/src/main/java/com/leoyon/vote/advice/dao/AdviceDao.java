package com.leoyon.vote.advice.dao;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.advice.Advice;
import com.leoyon.vote.picture.Picture;

public interface AdviceDao {

	Collection<Advice> find(FindPagedRequest rqst);

	Collection<Picture> getPictures(Long id);

	void add(Advice entity);

	void addPictures(Advice entity);

}
