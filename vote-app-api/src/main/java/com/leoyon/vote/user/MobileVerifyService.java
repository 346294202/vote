package com.leoyon.vote.user;

public interface MobileVerifyService {

	boolean verify(String mobile, String code) throws Exception;

}
