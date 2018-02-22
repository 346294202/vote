package com.leoyon.vote.area;

import com.leoyon.doc.ApiParam;
import com.leoyon.doc.ApiParamIgnore;
import com.leoyon.vote.AdminPojo;

public class Area extends AdminPojo {
	private Long id;//	bigint	20	0	0	0	0	0	0		0					-1	0
	private String name;//	varchar	255	0	0	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private String address;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private String developer;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private String service;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private String remark;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	public Long getId() {
		return id;
	}
	@ApiParamIgnore
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
	@ApiParam(desc="地址")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@ApiParam(desc="开发商")
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	@ApiParam(desc="物业公司")
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	@ApiParam(desc="备注")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
