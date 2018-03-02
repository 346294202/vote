package com.leoyon.vote.order;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.order.dao.OrderDao;
import com.leoyon.vote.product.dao.ProductPictureDao;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDao dao;
	
	@Autowired
	private ProductPictureDao productPictureDao; 

	@Override
	public Collection<Order> find(FindOrderRequest rqst) {
		Collection<Order> orders = dao.find(rqst);
		orders.forEach(i -> {
			i.setPictures(productPictureDao.getPictures(i.getProductId()));
		});
		return orders;
	}

}
