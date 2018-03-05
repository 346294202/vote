package com.leoyon.vote.repair;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.FindPagedRequest;
import com.leoyon.vote.api.DefauleResponse;
import com.leoyon.vote.api.ListResponse;
import com.leoyon.vote.api.ResponseException;

@RestController("维修")
@Scope("prototype")
public class RepairController extends AuthenticationController {
	
	@Autowired
	private RepairService repairService;

	@GetMapping(value="/repair", name="获得报修")
	public ListResponse<Repair> list(FindPagedRequest rqst) throws ResponseException {
		rqst.setUserId(getLogin(false).getId());
		return ListResponse.success(repairService.find(rqst));
	}
	
	@PostMapping(value="/repair", name="提交报修")
	public DefauleResponse add(@RequestBody Repair entity) throws ResponseException {
		entity.setUserId(getLogin(false).getId());
		repairService.add(entity);
		return DefauleResponse.sucess();
	}
}
