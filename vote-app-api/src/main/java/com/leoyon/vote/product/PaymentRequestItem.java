package com.leoyon.vote.product;

import com.leoyon.vote.address.Address;

import wj.flydoc.ApiParam;

public class PaymentRequestItem {

	private long productId;
	private long productSpecId;
	private int count;
	private String remark;	
	private Address address; 

	public Address getAddress() {
		return address;
	}

	@ApiParam(desc="地址", required=true)
	public void setAddress(Address address) {
		this.address = address;
	}

	public long getProductSpecId() {
		return productSpecId;
	}
	
	@ApiParam(desc="产品规格ID", required=true)
	public void setProductSpecId(long productSpecId) {
		this.productSpecId = productSpecId;
	}
	
	@ApiParam(desc="数量", required=true)
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	@ApiParam(desc="产品ID", required=true)
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	@ApiParam(desc="用户说明")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
