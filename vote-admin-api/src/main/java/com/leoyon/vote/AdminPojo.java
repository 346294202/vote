package com.leoyon.vote;

import java.util.Date;

/**
 * 基础管理对象
 * @author wj
 *
 */
public class AdminPojo {

	private Date dateCreate;
	private Date updateTime;
	private Long updateUid;
	
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}
	
	
}
