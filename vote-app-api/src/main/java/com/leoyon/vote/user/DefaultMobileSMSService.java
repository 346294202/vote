package com.leoyon.vote.user;

import org.springframework.stereotype.Service;

import com.leoyon.vote.api.VoteException;

@Service
public class DefaultMobileSMSService implements MobileSMSService {

	@Override
	public void sendVericyCode(String mobile, String code, Integer verifyCodeExpirSeconds) throws VoteException {
		String msg = String.format("【天投物业】{0} (业主注册验证码 {2}秒内有效) 回复T退订", code, verifyCodeExpirSeconds);
		//TODO 发送代码
	}

}
