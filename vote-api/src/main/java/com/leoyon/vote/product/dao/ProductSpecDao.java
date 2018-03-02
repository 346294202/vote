package com.leoyon.vote.product.dao;

import java.util.Collection;

import com.leoyon.vote.product.ProductSpec;

public interface ProductSpecDao {

	Collection<ProductSpec> getSpecs(Long productId);

	ProductSpec get(long id);
}
