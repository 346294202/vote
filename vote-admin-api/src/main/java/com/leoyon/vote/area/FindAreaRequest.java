package com.leoyon.vote.area;

import com.leoyon.doc.ApiParamCtor;

public class FindAreaRequest {
	
	private String q;
	private Integer page;
	private Integer psize;
	private Integer active;
	
	@ApiParamCtor
	public FindAreaRequest(String q, Integer page, Integer psize, Integer active) {
		super();
		this.q = q;
		this.page = page;
		this.psize = psize;
		this.active = active;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getQ() {
		return q;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}
	
	
}
