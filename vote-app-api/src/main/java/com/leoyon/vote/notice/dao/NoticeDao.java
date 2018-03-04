package com.leoyon.vote.notice.dao;

import java.util.Collection;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.notice.Notice;

public interface NoticeDao {

	Collection<Notice> list(FindPagedRequest rqst);

}
