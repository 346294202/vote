package com.leoyon.vote.user;

import com.leoyon.vote.api.VoteException;

public interface MobileSMSService {

	void sendVericyCode(String mobile, String code, Integer verifyCodeExpirSeconds) throws VoteException;

}
