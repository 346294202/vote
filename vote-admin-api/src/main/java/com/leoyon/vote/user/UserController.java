package com.leoyon.vote.user;


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

	@GetMapping(value="/user", name="查询用户")
	@ApiDocAnnotation(params={
			"active 有效状态，可选", 
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
	})
	public JsonResponse find(FindUserRequest reqst) {
		return JsonResponse.RespSuccess(new M<>()
				.put("items", userService.find(reqst))
				.build());		
	}
	
	@PostMapping(value="/user/{id}", name="修改用户")
	@ApiDocAnnotation(params={
			"active 有效标志，整数，可选", 
	})
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody User user) {
		user.setId(id);
		userService.update(user);
		return JsonResponse.RespSuccess();
	}
	
}
