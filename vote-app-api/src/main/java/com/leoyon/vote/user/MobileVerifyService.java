package com.leoyon.vote.user;

import com.leoyon.vote.api.ResponseException;

public interface MobileVerifyService {

	boolean verify(String mobile, String code) throws ResponseException;

	void sendCode(String mobile) throws ResponseException;

}
