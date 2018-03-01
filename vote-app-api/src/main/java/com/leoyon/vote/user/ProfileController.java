package com.leoyon.vote.user;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;

@RestController("用户信息")
@Scope("prototype")
public class ProfileController extends AuthenticationController{

	@GetMapping(value="/profile/info	", name="获得基本信息")
	public JsonResponse getUserInfo() throws ResponseException {
		User user = getLogin(false);
		return JsonResponse.sucess(user);
	}
	
	@PostMapping(value="/profile/info/real", name="修改实名信息")
	public JsonResponse setUserInfo(@RequestBody UserRealInfo entity) throws ResponseException {
		User user = getLogin(false);
		entity.setId(user.getId());
		userService.update(entity);
		return JsonResponse.sucess();
	}
}
