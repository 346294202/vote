package com.leoyon.vote.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.command.Menu;
import com.leoyon.vote.command.SysCommand;
import com.leoyon.vote.role.SysRole;
import com.leoyon.vote.role.SysRoleCommandService;
import com.leoyon.vote.util.M;

@RestController("登录用户配置")
@Scope("prototype")
public class ProfileController extends AuthenticationController {
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysRoleCommandService sysRoleCommandService;

	@GetMapping(value="/profile/menu", name="获得登录用户菜单")
	public JsonResponse getUserMenus() throws ResponseException {
		SysUser user = getLogin(false);
		List<SysRole> roles = sysUserRoleService.getUserRoles(user.getId());
		List<SysCommand> commands = sysRoleCommandService.collectCommands(roles);
		return JsonResponse.sucess(M.map().put("items", Menu.build(commands)).build());
	}
	
	@GetMapping(value="/profile/info", name="获得登录用户信息")
	public JsonResponse getUserInfo() throws ResponseException {
		SysUser user = getLogin(false);
		return JsonResponse.sucess(M.map()
				.put("username", user.getUsername())
				.put("realName", user.getRealName())
				.put("depart", user.getDepart())
				.put("id", user.getId())
				.build());
	}

}
