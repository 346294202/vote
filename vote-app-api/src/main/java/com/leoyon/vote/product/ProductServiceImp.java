package com.leoyon.vote.product;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.order.Order;
import com.leoyon.vote.order.dao.OrderDao;
import com.leoyon.vote.payment.Payment;
import com.leoyon.vote.payment.dao.PaymentDao;
import com.leoyon.vote.product.dao.ProductDao;
import com.leoyon.vote.product.dao.ProductPictureDao;
import com.leoyon.vote.product.dao.ProductSpecDao;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ProductSpecDao productSpecDao;
	
	@Autowired
	private ProductPictureDao productPictureDao;
	
	@Autowired
	private PaymentDao paymentDao; 
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Collection<Product> list(ListProductRequest rqst) {
		Collection<Product> products = dao.list(rqst);
		products.forEach(i -> {
			i.setPictures(productPictureDao.getPictures(i.getId()));
			i.setSpecs(productSpecDao.getSpecs(i.getId()));
		});
		return products;
	}
	
	@Autowired
	private PayService payService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public Payment addPayment(PaymentRequest rqst) {		
		Payment payment = new Payment();
		payment.setId(rqst.buildPaymentId());
		payment.setChannel(rqst.getChannel());
		Vector<Order> orders = new Vector<>();
		rqst.getItems().forEach(i -> {
			ProductSpec spec = productSpecDao.get(i.getProductSpecId());
			
			Order order = new Order();
			order.setPaymentId(payment.getId());
			order.setAddress(i.getAddress().getAddress());
			order.setCount(i.getCount());
			order.setPrice(spec.getPrice());
			order.setProductId(i.getProductId());
			order.setProductSpecId(i.getProductSpecId());
			order.setUserRemark(i.getRemark());
			order.setUserId(rqst.getUserId());
			order.setOrderStatue(1);//未支付
			orders.add(order);			
		});
		payment.setAmount(orders.stream().mapToDouble(i -> i.getPrice()*i.getCount()).sum());
		// TODO 设置支付过期时间
//		payment.setExpire(new Date(System.currentTimeMillis() + 过期毫秒));
		String payData = payService.submit(payment);
		payment.setData(payData);
		payment.setStatus(1);//未支付
		paymentDao.add(payment);
		orderDao.addAll(orders);
		return payment;
	}

}
