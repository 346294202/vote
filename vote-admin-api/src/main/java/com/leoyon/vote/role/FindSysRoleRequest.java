package com.leoyon.vote.role;

import com.leoyon.vote.FindPagedRequest;

import wj.flydoc.ApiParamCtor;

public class FindSysRoleRequest extends FindPagedRequest {
	
	private String q;
	private Integer active;
	
	@ApiParamCtor
	public FindSysRoleRequest(String q, Integer page, Integer psize, Integer active) {
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

	public void setQ(String q) {
		this.q = q;
	}	
	
}
