package com.leoyon.vote.role;

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
import com.leoyon.vote.util.Parses;

@RestController
@Scope("prototype")
public class SysRoleController extends AuthenticationController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@GetMapping(value="/sys/role/all", name="获取全部角色")
	public JsonResponse all() {
		return JsonResponse.sucess(new M<>()
				.put("items", sysRoleService.all())
				.build());		
	}

	@GetMapping(value="/sys/role", name="查询系统角色")
	@ApiDocAnnotation(params={
			"q 模糊查询，字符串，可选", 
			"page 页数，整数，可选，缺省0", 
			"psize 每页个数，整数，可选，缺省20"
	})
	public JsonResponse find(FindSysRoleRequest rqst) {
		return JsonResponse.sucess(new M<>()
				.put("items", sysRoleService.find(rqst))
				.build());		
	}
	
	@PostMapping(value="/sys/role", name="新增系统角色")
	@ApiDocAnnotation(params={
			"name 角色名称，字符串，必须，最大255字符",
			"so 排序号，整数，可选，缺省0",
			"remark 备注，字符串，可选，最大255字符",
	})
	public JsonResponse add(@RequestBody SysRole role) {
		sysRoleService.add(role);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/sys/role/{id}", name="修改系统角色")
	@ApiDocAnnotation(params={
			"name 角色名称，字符串，可选，最大255字符",
			"so 排序号，整数，可选，缺省0",
			"remark 备注，字符串，可选，最大255字符",
	})
	public JsonResponse update(
			@PathVariable Long id,
			@RequestBody SysRole role) {
		role.setId(id);
		sysRoleService.update(role);
		return JsonResponse.sucess();
	}
	
	@Autowired
	private SysRoleCommandService sysRoleCommandService;
	
	@GetMapping(value="/sys/role/{id}/command", name="获得角色绑定的菜单")
	@ApiDocAnnotation(params={})
	public JsonResponse getRoleCommands(@PathVariable Long id) {
		return JsonResponse.sucess(new M<>()
				.put("ids", sysRoleCommandService.getRoleCommands(id))
				.build());
	}
	
	@PostMapping(value="/sys/role/{id}/command", name="修改角色菜单")
	@ApiDocAnnotation(params={
			"菜单id数组，字符串，格式为'id,id,id'，必须"
	})
	public JsonResponse setRoleCommands(
			@PathVariable Long id, 
			@RequestBody String ids) throws Exception {
		sysRoleCommandService.setRoleCommands(id, Parses.parseList(ids, Long.class, ","));
		return JsonResponse.sucess();
	}
}
