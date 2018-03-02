package com.leoyon.vote.product;

import com.leoyon.vote.FindPagedRequest;

public class ListProductRequest extends FindPagedRequest{

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
	
}
