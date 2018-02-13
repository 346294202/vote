package com.leoyon.vote.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.doc.ApiDocAnnotation;
import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;

@RestController
@Scope("prototype")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MobileVerifyService mobileVerifyService;

	@PostMapping(value="/register", name="注册")
	@ApiDocAnnotation(params={
			"mobile:手机号，字符串，必须，最大13字符 ",
			"password：密码，字符串，必须，最大128字符",
			"code:验证码，字符串，必须，最大6字符",
	})
	public JsonResponse register(
			@RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "code") String code) throws Exception {
		
		if(!mobileVerifyService.verify(mobile, code)) {
			throw new ResponseException(Error.VARIFY_FAILED);
		}
		
		try{
			User user = userService.add(mobile, password);
			Token token = userService.fetchToken(user);			
			return JsonResponse.RespSuccess(token);
		}catch(org.springframework.dao.DuplicateKeyException e) {
			throw new ResponseException(Error.USER_REGISTER_FAIL_DUPLICATED);
		}
	}
	
	@PostMapping(value="/login", name="登录")
	@ApiDocAnnotation(params={
			"mobile:手机号，字符串，必须，最大13字符 ",
			"password：密码，字符串，必须，最大128字符",
	})
	public JsonResponse login(
			@RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "password") String password) throws Exception {
		
		User user = userService.get(mobile);
		
		if(user == null) {
			throw new ResponseException(Error.USER_LOGIN_FAIL_NOT_EXSISTED);
		}
		
		if(!user.matchPassword(password)) {
			throw new ResponseException(Error.USER_LOGIN_FAIL);
		}
		
		Token token = userService.fetchToken(user);
		
		return JsonResponse.RespSuccess(token);
	}
}
