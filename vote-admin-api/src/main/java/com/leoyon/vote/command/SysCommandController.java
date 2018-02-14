package com.leoyon.vote.command;

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
public class SysCommandController extends AuthenticationController {

	@Autowired
	private SysCommandService service;
	
	@GetMapping(value="/sys/command/all", name="获取所有菜单")
	@ApiDocAnnotation(params={})
	public JsonResponse all() {
		return JsonResponse.RespSuccess(new M<>()
				.put("items", service.all())
				.build());		
	}

	@GetMapping(value="/sys/command", name="查询系统菜单")
	@ApiDocAnnotation(params={
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
	})
	public JsonResponse find(FindSysCommandRequest rqst) {
		return JsonResponse.RespSuccess(new M<>()
				.put("items", service.find(rqst))
				.build());		
	}
	
	@PostMapping(value="/sys/command", name="新增系统菜单")
	@ApiDocAnnotation(params={
			"parentId 上级菜单id，整数，必须",
			"name 名称，字符串，必须，最大255字符",
			"url 菜单路径，字符串，必须，最大255字符",
			"icon 图标编码，整数，可选，缺省1",
			"so 排序号，整数，可选，缺省0",
	})
	public JsonResponse add(@RequestBody SysCommand command) {
		service.add(command);
		return JsonResponse.RespSuccess();
	}
	
	@PostMapping(value="/sys/command/{id}", name="修改系统菜单")
	@ApiDocAnnotation(params={
			"parentId 上级菜单id，整数，可选",
			"name 名称，字符串，可选，最大255字符",
			"url 菜单路径，字符串，可选，最大255字符",
			"icon 图标编码，整数，可选，缺省1",
			"so 排序号，整数，可选，缺省0",
	})
	public JsonResponse update(
			@PathVariable Long id,
			@RequestBody SysCommand command) {
		command.setId(id);
		service.update(command);
		return JsonResponse.RespSuccess();
	}
}
