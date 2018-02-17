package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiDocAnnotation;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;
import com.leoyon.vote.util.Parses;

@RestController
@Scope("prototype")
public class SysUserController extends AuthenticationController {
	
	@Autowired
	private SysUserService userService;
	
	@GetMapping(value="/sys/user", name="查询系统用户")
	@ApiDocAnnotation(params={
			"active 有效状态，可选", 
			"superuser 超管状态，可选", 
			"staff 员工状态，可选", 
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
			})
	public JsonResponse find(
			FindSysUserRequest req
			) {	
		return JsonResponse.sucess(new M<>()
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
			@RequestBody SysUser entity
			) throws Exception {
		entity.setUpdateUid(getLogin(false).getId());
		String pswd = entity.getPassword();
		entity.setSalt(RandomStringUtils.randomAlphabetic(16));
		entity.setPassword(pswd);
		userService.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/sys/user/{id}", name="修改系统用户")
	@ApiDocAnnotation(params={
			"active 有效标志，整数，可选", 
			"superuser 是否超级管理员，整数，可选", 
			"staff 是否员工，整数，可选", 
			"email 电邮，字符串，可选，最大128字符"})
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody SysUser entity
			) throws Exception {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);		
		userService.update(entity);
		
		return JsonResponse.sucess();
	}
	
	@Autowired
	private SysUserRoleService sysUserRoleService; 
	
	@GetMapping(value="/sys/user/{id}/role", name="获得用户角色")
	public JsonResponse getUserRoles(@PathVariable(value="id") Long id) {
		return JsonResponse.sucess(new M<>()
				.put("items", sysUserRoleService.getUserRoles(id))
				.build());	
	}
	
	@PostMapping(value="/sys/user/{id}/role", name="修改用户角色")
	@ApiDocAnnotation(params={
			"角色id数组，字符串，格式为'id,id,id'，必须"
	})
	public JsonResponse setUserRoles(
			@PathVariable Long id, 
			@RequestBody String ids) throws Exception {
		sysUserRoleService.setUserRoles(id, Parses.parseList(ids, Long.class, ","));
		return JsonResponse.sucess();
	}
}
