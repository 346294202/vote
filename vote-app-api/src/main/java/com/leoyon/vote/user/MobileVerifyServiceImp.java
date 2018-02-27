package com.leoyon.vote.user;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.AppConfig;
import com.leoyon.vote.api.VoteException;
import com.leoyon.vote.user.dao.MobileVerifyDao;
import com.leoyon.vote.util.Checks;

@Service
public class MobileVerifyServiceImp implements MobileVerifyService {
	
	@Autowired
	private MobileVerifyDao dao;
	
	@Autowired
	private AppConfig app;

	@Override
	public boolean verify(String mobile, String code) throws VoteException {
		if(StringUtils.isBlank(code)
				|| StringUtils.isBlank(mobile))
			throw new VoteException("参数不能为空");
		return dao.countWithExpire(mobile, code) > 0;
	}
	
	@Autowired
	private MobileSMSService smsService;

	@Override
	public void sendCode(String mobile) throws VoteException {
		if(!Checks.checkMobile(mobile))
			throw new VoteException("错误的手机号");
		String code = RandomStringUtils.randomNumeric(8);
		smsService.sendVericyCode(mobile, code, app.getVerifyCodeExpirSeconds());
		dao.add(mobile, code, new Date(System.currentTimeMillis() + app.getVerifyCodeExpirSeconds()*1000));
	}

}
