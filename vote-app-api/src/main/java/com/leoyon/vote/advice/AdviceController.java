package com.leoyon.vote.advice;

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

@RestController("投诉")
@Scope("prototype")
public class AdviceController extends AuthenticationController {
	
	@Autowired
	private AdviceService adviceService;

	@GetMapping(value="/advice", name="获得投诉")
	public ListResponse<Advice> list(FindPagedRequest rqst) throws ResponseException {
		rqst.setUserId(getLogin(false).getId());
		return ListResponse.success(adviceService.find(rqst));
	}
	
	@PostMapping(value="/advice", name="提交投诉")
	public DefauleResponse add(@RequestBody Advice entity) throws ResponseException {
		entity.setUserId(getLogin(false).getId());
		adviceService.add(entity);
		return DefauleResponse.sucess();
	}
}
