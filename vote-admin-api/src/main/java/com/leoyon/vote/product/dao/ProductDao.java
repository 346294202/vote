package com.leoyon.vote.product.dao;

import java.util.Collection;
import java.util.List;

import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.product.FindProductRequest;
import com.leoyon.vote.product.Product;
import com.leoyon.vote.product.ProductSpec;

public interface ProductDao {

	List<Product> find(FindProductRequest req);

	Collection<Picture> getPictures(Long productId);

	Collection<ProductSpec> getSpecs(Long productId);

	void add(Product entity);

	void addPicture(ProductPicture productPicture);

	void addSpec(ProductSpec i);

	void update(Product entity);

	void clearPictures(Long productId);

	void updateSpec(ProductSpec i);

}