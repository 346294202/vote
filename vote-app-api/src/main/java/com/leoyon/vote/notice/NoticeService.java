package com.leoyon.vote.notice;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;

public interface NoticeService {

	Collection<Notice> list(FindPagedRequest rqst);

}
