package com.leoyon.vote.order.dao;

import java.util.Collection;

import com.leoyon.vote.order.FindOrderRequest;
import com.leoyon.vote.order.Order;

public interface OrderDao {

	void addAll(Collection<Order> orders);

	Collection<Order> find(FindOrderRequest rqst);

}
