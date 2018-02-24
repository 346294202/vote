package com.leoyon.vote.user;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindSysUserRequest {
	
	private String q;
	private Integer page;
	private Integer psize;
	private Integer active;
	
	@ApiParamCtor
	public FindSysUserRequest(
			@ApiParam(desc="模糊查询")
			String q, 
			@ApiParam(desc="页码")
			Integer page,
			@ApiParam(desc="页大小")
			Integer psize, 
			@ApiParam(desc="用户有效性")
			Integer active) {
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
