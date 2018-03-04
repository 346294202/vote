package com.leoyon.vote.notice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.notice.dao.NoticeDao;

@Service
public class NoticeServiceImp implements NoticeService {
	
	@Autowired
	private NoticeDao dao;

	@Override
	public Collection<Notice> list(FindPagedRequest rqst) {
		return dao.list(rqst);
	}

}
