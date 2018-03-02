package com.leoyon.vote.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.ListResponse;

@RestController("订单")
@Scope("prototype")
public class OrderController extends AuthenticationController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping(value="/order", name="查询订单")
	public ListResponse<Order> find(FindOrderRequest rqst) {
		return ListResponse.success(orderService.find(rqst));
	}
}
