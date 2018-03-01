package com.leoyon.vote.product;

public class ListProductRequest {

	private Integer type;
	private Integer page;
	private Integer psize;
	ListProductRequest(Integer type, Integer page, Integer psize) {
		super();
		this.type = type;
		this.page = page;
		this.psize = psize;
	}
	public Integer getType() {
		return type;
	}
	public Integer getPage() {
		return page == null ? 0 : page*getPsize();
	}
	public Integer getPsize() {
		return psize == null ? 20 : psize;
	}
	
}
