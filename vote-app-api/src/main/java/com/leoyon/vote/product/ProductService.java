package com.leoyon.vote.product;

import java.util.Collection;

import com.leoyon.vote.payment.Payment;

public interface ProductService {

	Collection<Product> list(ListProductRequest listProductRequest);

	Payment addPayment(PaymentRequest rqst);

	ProductSpec getSpec(long productSpecId);

}
