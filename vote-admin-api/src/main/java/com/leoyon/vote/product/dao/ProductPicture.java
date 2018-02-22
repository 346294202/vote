package com.leoyon.vote.product.dao;

public class ProductPicture {

	private Long pictureId;
	private Long productId;
	public ProductPicture(Long pictureId, Long productId) {
		super();
		this.pictureId = pictureId;
		this.productId = productId;
	}
	public Long getPictureId() {
		return pictureId;
	}
	public Long getProductId() {
		return productId;
	}
	
}
