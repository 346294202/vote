package com.leoyon.vote.product;

import java.util.Collection;

import com.leoyon.vote.picture.Picture;

public class Product extends BaseProduct {
	
	private Collection<Long> specsToDelete;

	private Collection<Picture> pictures;
	private Collection<ProductSpec> specs;
	public Collection<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<Picture> images) {
		this.pictures = images;
	}
	public Collection<ProductSpec> getSpecs() {
		return specs;
	}
	public void setSpecs(Collection<ProductSpec> specs) {
		this.specs = specs;
	}
	public Collection<Long> getSpecsToDelete() {
		return specsToDelete;
	}
	public void setSpecsToDelete(Collection<Long> specsToDelete) {
		this.specsToDelete = specsToDelete;
	}
	
}
