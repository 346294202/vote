package com.leoyon.vote.product.dao;

import java.util.Collection;

import com.leoyon.vote.picture.Picture;

public interface ProductPictureDao {

	Collection<Picture> getPictures(Long productId);
}
