package com.leoyon.vote.area;

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
public class AreaController extends AuthenticationController {
	
	@Autowired
	private AreaService areaService;
	
	@GetMapping(value="/basic/area/all", name="查询小区")
	public JsonResponse all() {
		return JsonResponse.sucess(new M<>()
				.put("items", areaService.all())
				.build());		
	}

	@GetMapping(value="/basic/area", name="查询小区")
	public JsonResponse find(FindAreaRequest rqst) {
		return JsonResponse.sucess(new M<>()
				.put("count", areaService.count(rqst))
				.put("items", areaService.find(rqst))
				.build());		
	}
	
	@PostMapping(value="/basic/area", name="新增小区")
	public JsonResponse add(@RequestBody Area entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		areaService.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/basic/area/{id}", name="修改小区")
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody Area entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		areaService.update(entity);
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/basic/area/{id}", name="删除小区")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		Area entity = new Area();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		areaService.delete(entity);
		return JsonResponse.sucess();
	}
}
