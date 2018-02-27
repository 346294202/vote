package com.leoyon.vote.user;

import java.util.Collection;

import wj.flydoc.ApiParamCtor;

public class FindSysUserResult {

	private int count;
	private Collection<com.leoyon.vote.user.FindSysUserResponse> items;
	
	@ApiParamCtor
	public FindSysUserResult(int count, Collection<FindSysUserResponse> items) {
		super();
		this.count = count;
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public Collection<com.leoyon.vote.user.FindSysUserResponse> getItems() {
		return items;
	}
	
}
