package com.leoyon.vote.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.leoyon.vote.product.dao.ProductDao;
import com.leoyon.vote.product.dao.ProductPicture;
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
	public List<Product> find(FindProductRequest req) {
		List<Product> ret = dao.find(req);
		ret.forEach(i -> {
			i.setPictures(productPictureDao.getPictures(i.getId()));
			i.setSpecs(productSpecDao.getSpecs(i.getId()));
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
		
		if(entity.getPictures() != null) {
			dao.clearPictures(entity.getId());
			addPictures(entity);
		}
		
		if(entity.getSpecsToDelete() != null) {
			dao.deleteSpecs(entity.getSpecsToDelete());
		}
		
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

	@Override
	public int count(FindProductRequest req) {
		return dao.count(req);
	}

}
