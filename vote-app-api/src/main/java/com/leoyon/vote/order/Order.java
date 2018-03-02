package com.leoyon.vote.order;

import java.util.Date;

public class Order {
	
	private long id;
	private String paymentId;
	private long productId;
	private long productSpecId;
	private int count;
	private double price;
	private long userId;
	private String address;
	private Date orderTime;
	private int orderStatue;
	private String userRemark;
	private String replay;
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getProductSpecId() {
		return productSpecId;
	}
	public void setProductSpecId(long productSpecId) {
		this.productSpecId = productSpecId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getOrderStatue() {
		return orderStatue;
	}
	public void setOrderStatue(int orderStatue) {
		this.orderStatue = orderStatue;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
