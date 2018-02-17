package com.leoyon.vote.area;

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
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
