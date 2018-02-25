package com.leoyon.vote.user;

import com.leoyon.vote.api.VoteException;

public interface MobileVerifyService {

	boolean verify(String mobile, String code) throws VoteException;

	void sendCode(String mobile) throws VoteException;

}
