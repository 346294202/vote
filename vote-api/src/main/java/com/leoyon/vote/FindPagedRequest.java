package com.leoyon.vote;

public class FindPagedRequest {

	protected Integer page;
	protected Integer psize;

	public FindPagedRequest() {
		super();
	}

	public Integer getPage() {
		return page == null ? 0 : page*getPsize();
	}
	public Integer getPsize() {
		return psize == null ? 20 : psize;
	}
}