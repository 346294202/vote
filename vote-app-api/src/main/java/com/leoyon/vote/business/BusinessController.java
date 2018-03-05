package com.leoyon.vote.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.ListResponse;

@RestController("周边")
@Scope("prototype")
public class BusinessController extends AuthenticationController {
	
	@Autowired
	private BusinessService businessService; 

	@GetMapping(value="/business", name="获得周边")
	public ListResponse<Business> find(FindBizRequest rqst) {
		return ListResponse.success(businessService.find(rqst));
	}
}
