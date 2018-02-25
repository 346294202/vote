package com.leoyon.vote.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.api.VerifyException;

@RestController("登录注册")
@Scope("prototype")
public class LoginRegisterController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MobileVerifyService mobileVerifyService;

	@PostMapping(value="/register", name="注册")
	public JsonResponse register(
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
