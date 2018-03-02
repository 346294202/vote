package com.leoyon.vote.order;

import java.util.Date;

import com.leoyon.vote.FindPagedRequest;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindOrderRequest extends FindPagedRequest {

	private Integer type;
	private Integer orderStatus;
	
	@ApiParamCtor
	public FindOrderRequest(Integer type, Integer orderStatus, Integer page, Integer psize) {
		super();
		this.type = type;
		this.orderStatus = orderStatus;
		this.page = page;
		this.psize = psize;
	}

	public Integer getType() {
		return type;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	
}
