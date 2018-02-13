package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.AppConfig;

@Service
public class VerifyServiceImp implements VerifyService {
	
	@Autowired
	private AppConfig appConfig;
	
	@Override
	public VerifyCode generate() {
		return VerifyCode.encode(RandomStringUtils.randomAlphanumeric(4), 1000*appConfig.getVerifyCodeExpirSeconds());
	}

	@Override
	public boolean verify(String key, String code) {		
		return VerifyCode.verify(key, code);
	}

}
