package com.leoyon.vote.product;

import java.util.List;

public interface ProductService {

	List<Product> find(FindProductRequest req);

	void add(Product entity);

	void update(Product entity);

}
