package com.leoyon.vote.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RestController("业主")
@Scope("prototype")
public class UserController extends AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/basic/user/house", name="查询业主认证结果")
	public JsonResponse find(
			FindUserHouseRequest req
			) {	
		return JsonResponse.sucess(new M<>()
				.put("count", userService.countHouse(req))
				.put("items", userService.findHouse(req))
				.build());		
	}
	
	@PostMapping(value="/basic/user/{id}/house", name="提交业主认证结果")
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
