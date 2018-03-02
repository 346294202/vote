package com.leoyon.vote.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.DataResponse;
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
	private HouseService houseService;
	
	@GetMapping(value="/house/{areaId}/{building}/{unit}/{number}", name="匹配房屋")
	public DataResponse<House> listHouse(
			@PathVariable("areaId") long areaId,
			@PathVariable("building") int building,
			@PathVariable("unit") int unit,
			@PathVariable("number") int number
			) throws ResponseException {
		House entity = new House();
		entity.setAreaId(areaId);
		entity.setBuilding(building);
		entity.setUnit(unit);
		entity.setNumber(number);
		
		House ret = houseService.matchHouse(entity);
		if(ret == null) {
			throw new ResponseException("没有匹配的房屋");
		}
		return DataResponse.sucess(ret);
	}
	
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
