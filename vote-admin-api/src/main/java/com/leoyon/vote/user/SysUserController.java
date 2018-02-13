package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiDocAnnotation;
import com.leoyon.vote.GeneralController;
import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.util.MapBuilder;

@RestController
@Scope("prototype")
public class SysUserController extends GeneralController{
	
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
			@RequestParam(value = "key", required=false) String key,
			@RequestParam(value = "code") String code
			) throws Exception {
		
		if(StringUtils.isBlank(key)) {
			key = getCookie(VerifyCode.KEY_NAME);
		}
		
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
		
		return JsonResponse.RespSuccess(token);
	}
	
	@GetMapping(value="/sys/user", name="查询系统用户")
	@ApiDocAnnotation(params={
			"active 有效状态，可选", 
			"superuser 超管状态，可选", 
			"staff 员工状态，可选", 
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"})
	public JsonResponse find(
			FindUserRequest req
			) {	
		return JsonResponse.RespSuccess(new MapBuilder<>()
				.put("items", userService.find(req))
				.build());		
	}
	
	@PostMapping(value="/sys/user", name="新增系统用户")
	@ApiDocAnnotation(params={
			"username 用户名，字符串，必须，最大128字符", 
			"password 密码，字符串，必须，最大128字符", 
			"active 有效标志，整数，可选，缺省1", 
			"superuser 是否超级管理员，整数，可选，缺省0", 
			"staff 是否员工，整数，可选，缺省1", 
			"email 电邮，字符串，可选，最大128字符"})
	public JsonResponse add(
			@RequestBody SysUser user
			) throws Exception {
		
		String pswd = user.getPassword();
		user.setSalt(RandomStringUtils.randomAlphabetic(16));
		user.setPassword(pswd);
		userService.add(user);
		
		return JsonResponse.RespSuccess();
	}
	
	@PostMapping(value="/sys/user/{id}", name="修改系统用户")
	@ApiDocAnnotation(params={
			"active 有效标志，整数，可选", 
			"superuser 是否超级管理员，整数，可选", 
			"staff 是否员工，整数，可选", 
			"email 电邮，字符串，可选，最大128字符"})
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody SysUser user
			) throws Exception {
		
		user.setId(id);		
		userService.update(user);
		
		return JsonResponse.RespSuccess();
	}
}
