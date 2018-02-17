package com.leoyon.vote.role;

import com.leoyon.vote.AdminPojo;

public class SysRole extends AdminPojo{
	
	private Long id;
	private String name;
	private Integer so;
	private String remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSo() {
		return so;
	}
	public void setSo(Integer so) {
		this.so = so;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
