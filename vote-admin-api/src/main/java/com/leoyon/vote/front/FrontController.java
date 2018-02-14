package com.leoyon.vote.front;

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
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.util.M;

@RestController
@Scope("prototype")
public class FrontController extends AuthenticationController {
	
	@Autowired
	private com.leoyon.vote.command.SysCommandService commandService;

	@GetMapping(value={"/front"}, name="首页配置")
	public JsonResponse getFrontConf() throws ResponseException {
		
		SysUser user = getLogin(false);
		
		List<SysCommand> list = commandService.listByUser(user);
		
		return JsonResponse.RespSuccess(new M<>()
				.put("menus", Menu.build(list)).build());
	}
}
