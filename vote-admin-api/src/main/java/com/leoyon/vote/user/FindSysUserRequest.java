package com.leoyon.vote.user;

import com.leoyon.vote.FindPagedRequest;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindSysUserRequest extends FindPagedRequest {
	
	private String q;
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

	public void setQ(String q) {
		this.q = q;
	}
	
}
