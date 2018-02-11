package com.leoyon.vote.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;

@RestController
@Scope("prototype")
public class UserController {

	@Autowired
	private SysUserService userService;

	@PostMapping("/login")
	public JsonResponse login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) throws Exception {
		
		SysUser user = userService.get(username);
		
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
