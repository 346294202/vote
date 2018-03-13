package com.leoyon.vote;

import wj.flydoc.ApiParamCtor;

public class FindPagedRequest {

	private Long userId;
	protected Integer page;
	protected Integer psize;

	public FindPagedRequest() {
		super();
	}

	@ApiParamCtor
	public FindPagedRequest(Integer page, Integer psize) {
		super();
		this.page = page;
		this.psize = psize;
	}

	public Integer getPage() {
		return page == null ? 0 : page*getPsize();
	}
	public Integer getPsize() {
		return psize == null ? 20 : psize;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}