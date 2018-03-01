package com.leoyon.vote.user;

import com.leoyon.vote.api.Token;

/**
 * 用户相关操作
 * @author WangJiang
 *
 */
public interface UserService {

	/**
	 * 新增用户
	 * @param mobile   手机号，合法的手机号码格式，不能重复
	 * @param password 密码，不能为空
	 * @return 新用户对象
	 * @throws Exception 
	 */
	User add(String mobile, String password) throws Exception;

	/**
	 * 获取Token
	 * @param user 不能为空
	 * @return
	 */
	Token fetchToken(User user);

	/**
	 * 通过账号查询
	 * @param mobile
	 * @return
	 */
	User get(String mobile);

	/**
	 * 通过token获得
	 * @param token
	 * @return
	 */
	User get(Long id);

	void update(UserRealInfo entity);
}
