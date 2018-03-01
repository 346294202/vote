package com.leoyon.vote.product.dao;

import java.util.Collection;

import com.leoyon.vote.product.ListProductRequest;
import com.leoyon.vote.product.Product;

public interface ProductDao {

	Collection<Product> list(ListProductRequest rqst);

}
