package com.leoyon.vote;

import java.util.Date;

import com.leoyon.doc.ApiParamIgnore;

/**
 * 基础管理对象
 * @author wj
 *
 */
public abstract class AbstractResource<T extends AbstractResource<?>> {

	private Date dateCreate;
	private Date updateTime;
	private Long updateUid;
	private Long id;
	private Boolean delete;
	
	@ApiParamIgnore
	public Date getDateCreate() {
		return dateCreate;
	}
	public T setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
		return (T) this;
	}
	@ApiParamIgnore
	public Date getUpdateTime() {
		return updateTime;
	}
	public T setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return (T) this;
	}
	@ApiParamIgnore
	public Long getUpdateUid() {
		return updateUid;
	}
	public T setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
		return (T) this;
	}
	public Long getId() {
		return id;
	}
	@ApiParamIgnore
	public T setId(Long id) {
		this.id = id;
		return (T) this;
	}
	public Boolean getDelete() {
		return delete;
	}
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	
	
}
