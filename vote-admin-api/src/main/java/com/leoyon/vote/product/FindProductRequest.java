package com.leoyon.vote.product;

import com.leoyon.doc.ApiParam;
import com.leoyon.doc.ApiParamCtor;

public class FindProductRequest {

	private String q;
	private Integer type;
	private Integer page;
	private Integer psize;
	
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
	public Integer getPage() {
		return page;
	}
	public Integer getPsize() {
		return psize;
	}
	
}
