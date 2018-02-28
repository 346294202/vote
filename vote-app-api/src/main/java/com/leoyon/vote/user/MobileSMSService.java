package com.leoyon.vote.user;

import com.leoyon.vote.api.ResponseException;

public interface MobileSMSService {

	void sendVericyCode(String mobile, String code, Integer verifyCodeExpirSeconds) throws ResponseException;

}
