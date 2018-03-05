package com.leoyon.vote.notice;

import com.leoyon.vote.FindPagedRequest;

public class FindNoticePagedRequest extends FindPagedRequest {

	private long userId;

	FindNoticePagedRequest(long userId, Integer page, Integer psize) {
		super(page, psize);
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}
	
}
