package com.leoyon.vote.notice;

import com.leoyon.vote.FindPagedRequest;

public class FindNoticePagedRequest extends FindPagedRequest {

	FindNoticePagedRequest(long userId, Integer page, Integer psize) {
		super(page, psize);
		setUserId(userId);
	}

}
