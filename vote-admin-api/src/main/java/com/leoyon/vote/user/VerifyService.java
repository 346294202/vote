package com.leoyon.vote.user;

/**
 * 验证码服务
 * @author WangJiang
 *
 */
public interface VerifyService {
	
	VerifyCode generate();

	boolean verify(String key, String code);

}
