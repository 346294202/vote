package com.leoyon.vote.role;

import com.leoyon.doc.ApiParam;
import com.leoyon.doc.ApiParamIgnore;
import com.leoyon.vote.AdminPojo;

public class SysRole extends AdminPojo{
	
	private Long id;
	private String name;
	private Integer so;
	private String remark;
	
	@ApiParamIgnore
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
