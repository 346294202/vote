package com.leoyon.vote.user.dao;

import com.leoyon.vote.user.VerifyCode;

public interface VerifyCodeDao {

	void add(VerifyCode vc);

	VerifyCode get(String key);

	void delete(String key);

}
