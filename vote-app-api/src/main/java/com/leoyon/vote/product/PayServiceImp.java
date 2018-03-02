package com.leoyon.vote.product;

import org.springframework.stereotype.Service;

import com.leoyon.vote.payment.Payment;

@Service
public class PayServiceImp implements PayService {

	@Override
	public String submit(Payment payment) {
		// TODO 通过channel获取支付数据
		return "not implemented";
	}

}
