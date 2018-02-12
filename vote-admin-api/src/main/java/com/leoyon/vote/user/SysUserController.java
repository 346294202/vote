package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.GeneralController;
import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.util.MapBuilder;

@RestController
@Scope("prototype")
@RequestMapping("sys")
public class SysUserController extends GeneralController{

	@Autowired
	private SysUserService userService;
	
	@Autowired
	private VerifyService verifyService;

	@PostMapping("/login")
	public JsonResponse login(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "key") String key,
			@RequestParam(value = "code") String code
			) throws Exception {
				
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
		
		return JsonResponse.RespSuccess(token);
	}
	
	@GetMapping("/user")
	public JsonResponse find(
			FindUserRequest req
			) {	
		return JsonResponse.RespSuccess(new MapBuilder<>()
				.put("items", userService.find(req))
				.build());		
	}
	
	@PostMapping("/user")
	public JsonResponse add(
			@RequestBody SysUser user
			) throws Exception {
		
		String pswd = user.getPassword();
		user.setSalt(RandomStringUtils.randomAlphabetic(16));
		user.setPassword(pswd);
		userService.add(user);
		
		return JsonResponse.RespSuccess();
	}
	
	@PostMapping("/user/{id}")
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody SysUser user
			) throws Exception {
		
		user.setId(id);		
		userService.update(user);
		
		return JsonResponse.RespSuccess();
	}
}
