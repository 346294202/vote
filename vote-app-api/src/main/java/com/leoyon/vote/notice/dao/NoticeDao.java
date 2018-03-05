package com.leoyon.vote.notice.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.leoyon.vote.notice.FindNoticePagedRequest;
import com.leoyon.vote.notice.Notice;

public interface NoticeDao {

	Collection<Notice> list(FindNoticePagedRequest rqst);

	void setReaded(@Param(value="uid") Long userId, @Param(value="nid") Long noticeId);

}
