package com.leoyon.vote.user.dao;

import org.apache.ibatis.annotations.Param;

public interface MobileVerifyDao {

	int countWithExpire(@Param(value = "mobile") String mobile, @Param(value = "code") String code);

}
