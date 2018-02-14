package com.leoyon.vote.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.user.User;
import com.leoyon.vote.util.M;

@RestController
@Scope("prototype")
public class HouseController extends AuthenticationController {
	
    @Autowired
    private HouseService houseService;

	@GetMapping(value="/house", name="获取用户房屋")
	public JsonResponse list() throws ResponseException {
		
		User user = getLogin(false);
		
		List<House> list = houseService.listByOwner(user);
		
		return JsonResponse.RespSuccess(new M<>().put("items", list));
	}
}
