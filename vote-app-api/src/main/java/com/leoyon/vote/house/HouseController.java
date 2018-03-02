package com.leoyon.vote.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.DefauleResponse;
import com.leoyon.vote.api.ListResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.area.Area;
import com.leoyon.vote.area.AreaService;
import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserHouse;
import com.leoyon.vote.user.UserService;

@RestController("用户房屋")
@Scope("prototype")
public class HouseController extends AuthenticationController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value="/house", name="获得绑定")
	public ListResponse<UserHouse> listHouse() throws ResponseException {
		User user = getLogin(false);
		return ListResponse.success(userService.listHouse(user));
	}
	
	@PostMapping(value="/house", name="提交绑定")
	public DefauleResponse setHouse(@RequestBody UserHouse entity) throws ResponseException {
		User user = getLogin(false);
		entity.setUserId(user.getId());
		userService.setHouse(entity);
		return DefauleResponse.sucess();
	}
	
	@Autowired
	private AreaService areaService;
	
	@GetMapping(value="/area/all", name="获得所有小区")
	public ListResponse<Area> getAllArea() {
		return ListResponse.success(areaService.all());
	}
}
