package com.leoyon.vote.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.order.dao.OrderDao;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDao dao;

	@Override
	public List<FindOrderResponse> find(FindOrderRequest rqst) {
		return dao.find(rqst);
	}

	@Override
	public int count(FindOrderRequest rqst) {
		return dao.count(rqst);
	}

}
