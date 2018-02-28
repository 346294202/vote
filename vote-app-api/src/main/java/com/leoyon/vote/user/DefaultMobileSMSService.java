package com.leoyon.vote.user;

import org.springframework.stereotype.Service;

import com.leoyon.vote.api.ResponseException;

@Service
public class DefaultMobileSMSService implements MobileSMSService {

	@Override
	public void sendVericyCode(String mobile, String code, Integer verifyCodeExpirSeconds) throws ResponseException {
		try {
			JuheSms.sendVerifyCode(mobile, code);
		} catch (ResponseException e) {
			throw e;
		} catch(Exception e) {
			throw new ResponseException(AppUserErrors.SMS_SEND_FAIL);
		}
	}

}
