package com.leoyon.vote.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiParam;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.role.SysRole;
import com.leoyon.vote.util.M;
import com.leoyon.vote.util.Parses;

@RestController
@Scope("prototype")
public class SysUserController extends AuthenticationController {
	
	@Autowired
	private SysUserService userService;
	
	@GetMapping(value="/sys/user", name="查询系统用户")
	public JsonResponse find(
			FindSysUserRequest req
			) {	
		return JsonResponse.sucess(new M<>()
				.put("items", userService.find(req))
				.build());		
	}
	
	@PostMapping(value="/sys/user", name="新增系统用户")
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
	public JsonResponse setUserRoles(
			@PathVariable(value="id") Long id,
			@ApiParam(desc="格式'id,id,...'")
			@RequestParam("ids") String ids) throws Exception {
		sysUserRoleService.setUserRoles(id, Parses.parseList(ids, Long.class, ","));
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/sys/user/{id}", name="删除系统用户")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		SysUser entity = new SysUser();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		userService.delete(entity);
		return JsonResponse.sucess();
	}
}
