package com.leoyon.vote.user;

import org.springframework.stereotype.Service;

@Service
public class MobileVerifyServiceImp implements MobileVerifyService {

	@Override
	public boolean verify(String mobile, String code) {
		// TODO 等待使用正式的短信平台
		return true;
	}

}
