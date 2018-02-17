package com.leoyon.vote.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiDocAnnotation;
import com.leoyon.vote.GeneralController;
import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;

@RestController
@Scope("prototype")
public class LoginController extends GeneralController {
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private VerifyService verifyService;

	@PostMapping(value="/login", name="登录,no token")
	@ApiDocAnnotation(params={
			"username:用户名，字符串，必须，最大128字符", 
			"password：密码，字符串，必须，最大128字符", 
			"key 验证码key", 
			"code 验证码"})
	public JsonResponse login(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "key") String key,
			@RequestParam(value = "code") String code
			) throws Exception {
				
		LOG.info(key+": "+code);
		
		if(!verifyService.verify(key, code)) {
			throw new ResponseException(Error.VARIFY_FAILED);
		}
		
		SysUser user = userService.get(username);
		
		if(user == null) {
			throw new ResponseException(Error.USER_LOGIN_FAIL_NOT_EXSISTED);
		}
		
		if(!user.matchPassword(password)) {
			throw new ResponseException(Error.USER_LOGIN_FAIL);
		}
		
		Token token = userService.fetchToken(user);
		
		return JsonResponse.sucess(token);
	}
	
}
