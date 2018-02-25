package com.leoyon.vote.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.user.dao.MobileVerifyDao;

@Service
public class MobileVerifyServiceImp implements MobileVerifyService {
	
	@Autowired
	private MobileVerifyDao dao;

	@Override
	public boolean verify(String mobile, String code) throws Exception {
		if(StringUtils.isBlank(code)
				|| StringUtils.isBlank(mobile))
			throw new Exception("参数不能为空");
		return dao.countWithExpire(mobile, code) > 0;
	}

}
