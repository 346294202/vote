package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyServiceImp implements VerifyService {
	
	@Autowired
	private com.leoyon.vote.user.dao.VerifyCodeDao verifyCodeDao;

	@Override
	public VerifyCode generate() {
		VerifyCode vc = new VerifyCode();
		vc.setKey(RandomStringUtils.randomAlphanumeric(128));
		vc.setCode(RandomStringUtils.randomAlphanumeric(4));
		verifyCodeDao.add(vc);
		return vc;
	}

	@Override
	public boolean verify(String key, String code) {
		VerifyCode vc = verifyCodeDao.get(key);
		verifyCodeDao.delete(key);		
		return vc != null && vc.getCode().equalsIgnoreCase(code);
	}

}
