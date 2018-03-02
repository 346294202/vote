package com.leoyon.vote.order;

import java.util.Collection;

public interface OrderService {

	Collection<Order> find(FindOrderRequest rqst);

}
