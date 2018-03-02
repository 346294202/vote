package com.leoyon.vote;

public class FindPagedRequest {

	protected Integer page;
	protected Integer psize;

	public FindPagedRequest() {
		super();
	}

	public Integer getPage() {
		return page * psize;
	}

	public Integer getPsize() {
		return psize;
	}

}