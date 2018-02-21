package com.leoyon.vote.user;

import java.util.Date;

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

@RestController
@Scope("prototype")
public class UserController extends AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/basic/user/house", name="查询业主认证结果")
	@ApiDocAnnotation(params={
			"active 有效状态，可选", 
			"superuser 超管状态，可选", 
			"staff 员工状态，可选", 
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
			})
	public JsonResponse find(
			FindUserHouseRequest req
			) {	
		return JsonResponse.sucess(new M<>()
				.put("items", userService.findHouse(req))
				.build());		
	}
	
	@PostMapping(value="/basic/user/{id}/house", name="提交业主认证结果")
	@ApiDocAnnotation(params={
			"active 有效标志，整数，可选", 
			"superuser 是否超级管理员，整数，可选", 
			"staff 是否员工，整数，可选", 
			"email 电邮，字符串，可选，最大128字符"})
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody UserHouse entity
			) throws Exception {
		entity.setOwnerUpdateUid(getLogin(false).getId());
		entity.setOwnerUpdateTime(new Date());
		userService.updateHouse(entity);
		return JsonResponse.sucess();
	}
	
}
