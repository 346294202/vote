package com.leoyon.vote.product;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoyon.vote.product.dao.ProductDao;
import com.leoyon.vote.product.dao.ProductPictureDao;
import com.leoyon.vote.product.dao.ProductSpecDao;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ProductSpecDao productSpecDao;
	
	@Autowired
	private ProductPictureDao productPictureDao;
	
	@Override
	public Collection<Product> list(ListProductRequest rqst) {
		Collection<Product> products = dao.list(rqst);
		products.forEach(i -> {
			i.setPictures(productPictureDao.getPictures(i.getId()));
			i.setSpecs(productSpecDao.getSpecs(i.getId()));
		});
		return products;
	}

}
