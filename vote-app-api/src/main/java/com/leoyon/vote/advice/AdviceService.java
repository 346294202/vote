package com.leoyon.vote.advice;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;

public interface AdviceService {

	Collection<Advice> find(FindPagedRequest rqst);

	void add(Advice entity);

}
