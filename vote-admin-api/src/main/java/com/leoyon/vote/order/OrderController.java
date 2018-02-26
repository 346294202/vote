package com.leoyon.vote.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RestController("订单")
@Scope("prototype")
public class OrderController extends AuthenticationController {
	
	@Autowired
	private OrderService service;

	@GetMapping(value="/basic/order", name="查询订单")
	public JsonResponse find(FindOrderRequest rqst) {
		List<FindOrderResponse> items = service.find(rqst);

		return JsonResponse.sucess(new M<>()
				.put("count", service.count(rqst))
				.put("items", items)
				.build());
	}
}
