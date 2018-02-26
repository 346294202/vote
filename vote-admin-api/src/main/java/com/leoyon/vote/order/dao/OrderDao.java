package com.leoyon.vote.order.dao;

import java.util.List;

import com.leoyon.vote.order.FindOrderRequest;
import com.leoyon.vote.order.FindOrderResponse;

public interface OrderDao {

	List<FindOrderResponse> find(FindOrderRequest rqst);

	int count(FindOrderRequest rqst);

}
