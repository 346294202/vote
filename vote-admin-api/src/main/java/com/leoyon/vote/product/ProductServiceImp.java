package com.leoyon.vote.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.product.dao.ProductDao;
import com.leoyon.vote.product.dao.ProductPicture;

@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductDao dao;

	@Override
	public List<Product> find(FindProductRequest req) {
		List<Product> ret = dao.find(req);
		ret.forEach(i -> {
			i.setPictures(dao.getPictures(i.getId()));
			i.setSpecs(dao.getSpecs(i.getId()));
		});
		return ret;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void add(Product entity) {
		dao.add(entity);
		
		addPictures(entity);
		
		if(entity.getSpecs() != null) {
			entity.getSpecs().forEach(i -> {
				i.setProductId(entity.getId());
				dao.addSpec(i);
			});
		}
	}

	private void addPictures(Product entity) {
		if(entity.getPictures() != null) {
			entity.getPictures().forEach(i -> {
				dao.addPicture(new ProductPicture(i.getId(), entity.getId()));
			});
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void update(Product entity) {
		dao.update(entity);
		dao.clearPictures(entity.getId());
		addPictures(entity);
		if(entity.getSpecs() != null) {
			entity.getSpecs().forEach(i -> {
				if(i.getId() == null) {
					i.setProductId(entity.getId());
					dao.addSpec(i);
				} else {
					dao.updateSpec(i);
				}
			});
		}
	}

	@Override
	public void delete(Product entity) {
		dao.delete(entity);
	}

}
