package com.leoyon.vote.advice;

import java.util.Collection;
import java.util.Date;

import com.leoyon.vote.picture.Picture;

import wj.flydoc.ApiIgnore;
import wj.flydoc.ApiParam;

public class Advice {
	private Long id;
	private Long userId;
	private String phone;
	private String content;
	private Date dateCreate;
	private Integer status;
	private Integer type;
	private Collection<Picture> pictures;
	
	@ApiParam(desc="建议id，提交时忽略")
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
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ApiParam(desc="日期，提交时忽略")
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	@ApiParam(desc="状态，提交时忽略")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@ApiParam(desc="1 投诉，2 建议")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Collection<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(Collection<Picture> pictures) {
		this.pictures = pictures;
	}
	
}
