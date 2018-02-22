package com.leoyon.vote.command;

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
public class SysCommandController extends AuthenticationController {

	@Autowired
	private SysCommandService service;
	
	@GetMapping(value="/sys/command/all", name="获取所有菜单")
	public JsonResponse all() {
		return JsonResponse.sucess(new M<>()
				.put("items", service.all())
				.build());		
	}

	@GetMapping(value="/sys/command", name="查询系统菜单")
	public JsonResponse find(FindSysCommandRequest rqst) {
		return JsonResponse.sucess(new M<>()
				.put("items", service.find(rqst))
				.build());		
	}
	
	@PostMapping(value="/sys/command", name="新增系统菜单")
	public JsonResponse add(@RequestBody SysCommand entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		service.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/sys/command/{id}", name="修改系统菜单")
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody SysCommand entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		service.update(entity);
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/sys/command/{id}", name="删除系统菜单")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		SysCommand entity = new SysCommand();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		service.delete(entity);
		return JsonResponse.sucess();
	}
}
