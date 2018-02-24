package com.leoyon.vote.order;

import java.util.List;

public interface OrderService {

	List<FindOrderResponse> find(FindOrderRequest rqst);

	int count(FindOrderRequest rqst);

}
