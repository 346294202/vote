package com.leoyon.vote.user;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.AppConfig;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.user.dao.MobileVerifyDao;

@Service
public class MobileVerifyServiceImp implements MobileVerifyService {
	
	@Autowired
	private MobileVerifyDao dao;
	
	@Autowired
	private AppConfig app;

	@Override
	public boolean verify(String mobile, String code) throws ResponseException {
		return dao.countWithExpire(mobile, code) > 0;
	}
	
	@Autowired
	private MobileSMSService smsService;

	@Override
	public void sendCode(String mobile) throws ResponseException {
		String code = RandomStringUtils.randomNumeric(8);
		smsService.sendVericyCode(mobile, code, app.getVerifyCodeExpirSeconds());
		dao.add(mobile, code, new Date(System.currentTimeMillis() + app.getVerifyCodeExpirSeconds()*1000));
	}

}
