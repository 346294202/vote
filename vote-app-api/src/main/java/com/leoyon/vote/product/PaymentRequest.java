package com.leoyon.vote.product;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.leoyon.vote.util.Dates;

import wj.flydoc.ApiIgnore;
import wj.flydoc.ApiParam;

public class PaymentRequest {
	
	private long userId;
	private Integer channel;
	

	private Collection<PaymentRequestItem> items;

	public Collection<PaymentRequestItem> getItems() {
		return items;
	}

	public void setItems(Collection<PaymentRequestItem> items) {
		this.items = items;
	}

	@ApiParam(desc="支付渠道，1 支付宝，2 微信", required=true)
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	@ApiIgnore
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String buildPaymentId() {
		return String.format("%1d%09d%s%s"
				, channel
				, userId
				, Dates.format(new Date(), "yyyyMMddHHmmss")
				, RandomStringUtils.randomNumeric(10));
	}
}
