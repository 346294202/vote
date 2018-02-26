package com.leoyon.vote.role;

import com.leoyon.vote.AbstractResource;

import wj.flydoc.ApiParam;

public class SysRole extends AbstractResource<SysRole> {
	
	private String name;
	private Integer so;
	private String remark;
	
	@ApiParam(desc="名称")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ApiParam(desc="排序号")
	public Integer getSo() {
		return so;
	}
	public void setSo(Integer so) {
		this.so = so;
	}
	@ApiParam(desc="备注")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
