package com.leoyon.vote.product;

import com.leoyon.vote.FindPagedRequest;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindProductRequest extends FindPagedRequest {

	private String q;
	private Integer type;
	
	@ApiParamCtor
	public FindProductRequest(String q,
			@ApiParam(desc="产品类型")
			Integer type, Integer page, Integer psize) {
		super();
		this.q = q;
		this.type = type;
		this.page = page;
		this.psize = psize;
	}
	public String getQ() {
		return q;
	}
	public Integer getType() {
		return type;
	}
	
}
