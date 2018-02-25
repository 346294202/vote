package com.leoyon.vote.user.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface MobileVerifyDao {

	int countWithExpire(@Param(value = "mobile") String mobile, @Param(value = "code") String code);

	void add(@Param(value = "mobile") String mobile, @Param(value = "code") String code, @Param(value = "expire") Date expire);

}
