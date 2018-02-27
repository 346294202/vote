package com.leoyon.vote.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.api.VerifyException;
import com.leoyon.vote.util.Checks;
import com.leoyon.vote.validation.MobileNumber;

@RestController("登录注册")
@Scope("prototype")
public class LoginRegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MobileVerifyService mobileVerifyService;
	
	@PostMapping(value="/verify-code/{mobile}", name="发送短信验证码")
	public JsonResponse sendVerifyCode(
			@Valid
			@MobileNumber(message="错误的手机号")
			@PathVariable("mobile") String mobile) throws ResponseException {
		if(!Checks.checkMobile(mobile)) {
			throw new ResponseException(Error.VALID_EXCEPT.getValue(), "错误的手机号");
		}
		mobileVerifyService.sendCode(mobile);
		return JsonResponse.sucess();
	}

	@PostMapping(value="/register", name="注册")
	public JsonResponse register(
			@Valid
			@RequestBody RegisterRequest rqst
			) throws Exception {
		
		if(!mobileVerifyService.verify(rqst.getMobile(), rqst.getCode())) {
			throw new VerifyException();
		}
		
		try{
			User user = userService.add(rqst.getMobile(), rqst.getPassword());
			Token token = userService.fetchToken(user);			
			return JsonResponse.sucess(token);
		}catch(org.springframework.dao.DuplicateKeyException e) {
			throw new ResponseException(Error.USER_REGISTER_FAIL_DUPLICATED);
		}
	}
	
	@PostMapping(value="/login", name="登录")
	public JsonResponse login(
			@Valid
			@RequestBody LoginRequest rqst
			) throws Exception {
		
		User user = userService.get(rqst.getMobile());
		
		if(user == null) {
			throw new ResponseException(Error.USER_LOGIN_FAIL_NOT_EXSISTED);
		}
		
		if(!user.matchPassword(rqst.getPassword())) {
			throw new ResponseException(Error.USER_LOGIN_FAIL);
		}
		
		Token token = userService.fetchToken(user);
		
		return JsonResponse.sucess(token);
	}
}
