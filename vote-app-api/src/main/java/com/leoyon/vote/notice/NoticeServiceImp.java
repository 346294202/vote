package com.leoyon.vote.notice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.notice.dao.NoticeDao;

@Service
public class NoticeServiceImp implements NoticeService {
	
	@Autowired
	private NoticeDao dao;

	@Override
	public Collection<Notice> list(FindNoticePagedRequest rqst) {
		return dao.list(rqst);
	}

	@Override
	public void setReaded(Long userId, Long noticeId) {
		dao.setReaded(userId, noticeId);
	}

}
