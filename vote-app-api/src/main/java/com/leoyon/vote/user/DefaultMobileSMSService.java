package com.leoyon.vote.user;

import org.springframework.stereotype.Service;

import com.leoyon.vote.api.VoteException;

@Service
public class DefaultMobileSMSService implements MobileSMSService {

	@Override
	public void sendVericyCode(String mobile, String code, Integer verifyCodeExpirSeconds) throws VoteException {
		try {
			JuheSms.sendVerifyCode(mobile, code);
		} catch (VoteException e) {
			throw e;
		} catch(Exception e) {
			throw new VoteException("短信发送失败");
		}
	}

}
