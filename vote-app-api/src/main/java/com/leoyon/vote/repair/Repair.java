package com.leoyon.vote.repair;

import java.util.Collection;
import java.util.Date;

import com.leoyon.vote.picture.Picture;

import wj.flydoc.ApiIgnore;

public class Repair {

	private Long id;
	private Long userId;
	private Long houseId;
	private String address;
	private String content;
	private Date dateCreate;
	private Integer status;
	private Boolean outside;
	private String phone;
	private String houseTitle;
	private Integer houseType;
	
	private Collection<Picture> pictures;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ApiIgnore
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Collection<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Boolean getOutside() {
		return outside;
	}
	public void setOutside(Boolean outside) {
		this.outside = outside;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ApiIgnore
	public String getHouseTitle() {
		return houseTitle;
	}
	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	@ApiIgnore
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	
	
}
