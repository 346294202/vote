package com.leoyon.vote.notice;

import java.util.Collection;

public interface NoticeService {

	Collection<Notice> list(FindNoticePagedRequest rqst);

	void setReaded(Long userId, Long noticeId);

}
