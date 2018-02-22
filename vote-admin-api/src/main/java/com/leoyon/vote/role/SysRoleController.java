package com.leoyon.vote.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.doc.ApiParam;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
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
	public JsonResponse find(FindSysRoleRequest rqst) {
		return JsonResponse.sucess(new M<>()
				.put("items", sysRoleService.find(rqst))
				.build());		
	}
	
	@PostMapping(value="/sys/role", name="新增系统角色")
	public JsonResponse add(@RequestBody SysRole entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		sysRoleService.add(entity);
		return JsonResponse.sucess();
	}
	
	@PostMapping(value="/sys/role/{id}", name="修改系统角色")
	public JsonResponse update(
			@PathVariable(value="id") Long id,
			@RequestBody SysRole entity) throws ResponseException {
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		sysRoleService.update(entity);
		return JsonResponse.sucess();
	}
	
	@Autowired
	private SysRoleCommandService sysRoleCommandService;
	
	@GetMapping(value="/sys/role/{id}/command", name="获得角色绑定的菜单")
	public JsonResponse getRoleCommands(@PathVariable(value="id") Long id) {
		return JsonResponse.sucess(new M<>()
				.put("ids", sysRoleCommandService.getRoleCommands(id))
				.build());
	}
	
	@PostMapping(value="/sys/role/{id}/command", name="修改角色菜单")
	public JsonResponse setRoleCommands(
			@PathVariable(value="id") Long id,
			@ApiParam(desc="格式'id,id,...'")
			@RequestParam(value="ids", required=true) String ids) throws Exception {
		sysRoleCommandService.setRoleCommands(id, Parses.parseList(ids, Long.class, ","));
		return JsonResponse.sucess();
	}
	
	@DeleteMapping(value="/sys/role/{id}", name="删除角色")
	public JsonResponse delete(
			@PathVariable(value="id") Long id) throws ResponseException {
		SysRole entity = new SysRole();
		entity.setUpdateUid(getLogin(false).getId());
		entity.setId(id);
		sysRoleService.delete(entity);
		return JsonResponse.sucess();
	}
}
