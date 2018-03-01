package com.leoyon.vote.product;

import java.util.Collection;

public interface ProductService {

	Collection<Product> list(ListProductRequest listProductRequest);

}
