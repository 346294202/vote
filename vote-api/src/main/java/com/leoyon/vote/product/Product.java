package com.leoyon.vote.product;

import java.util.Collection;

import com.leoyon.vote.picture.Picture;

public class Product {

	private long id;
	private String name;
	private String desc;
	private String priceDesc;
	private String so;
	private Integer type;
	private Integer subType;
	private String remark;
	private Collection<Picture> pictures;
	private Collection<ProductSpec> specs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSubType() {
		return subType;
	}
	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Collection<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}
	public Collection<ProductSpec> getSpecs() {
		return specs;
	}
	public void setSpecs(Collection<ProductSpec> specs) {
		this.specs = specs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
