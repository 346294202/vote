package com.leoyon.vote.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.util.M;

@RestController
@Scope("prototype")
public class HouseController extends AuthenticationController {
	
	@Autowired
	private HouseService houseService;

	@GetMapping(value="/basic/house", name="查询房屋")
	public JsonResponse find(FindHouseRequest reqst) {
		return JsonResponse.sucess(new M<>()
				.put("items", houseService.find(reqst))
				.build());	
	}
	
	@PostMapping(value="/basic/house", name="新增房屋")
	public JsonResponse add(@RequestBody House entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		houseService.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/basic/house/{id}", name="修改房屋")
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody House entity
			) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		houseService.update(entity);
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/basic/house/{id}", name="删除房屋")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		House entity = new House();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		houseService.delete(entity);
		return JsonResponse.sucess();
	}
}
