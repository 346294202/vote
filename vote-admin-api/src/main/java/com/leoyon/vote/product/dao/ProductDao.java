package com.leoyon.vote.product.dao;

import java.util.Collection;
import java.util.List;

import com.leoyon.vote.product.FindProductRequest;
import com.leoyon.vote.product.Product;
import com.leoyon.vote.product.ProductSpec;

public interface ProductDao {

	List<Product> find(FindProductRequest req);

	void add(Product entity);

	void addPicture(ProductPicture productPicture);

	void addSpec(ProductSpec i);

	void update(Product entity);

	void clearPictures(Long productId);

	void updateSpec(ProductSpec i);

	void delete(Product entity);

	int count(FindProductRequest req);

	void deleteSpecs(Collection<Long> ids);

}
