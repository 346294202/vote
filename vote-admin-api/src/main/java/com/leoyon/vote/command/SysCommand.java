package com.leoyon.vote.command;

public class SysCommand {
	
	private long id;//	bigint	20	0	0	-1	0	0	0		0					-1	0
	private long parentId;//	bigint	20	0	-1	0	0	0	0		0	上级命令id				0	0
	private String name;//	varchar	255	0	0	0	0	0	0		0	名称	utf8mb4	utf8mb4_unicode_ci		0	0
	private String url;//	varchar	255	0	-1	0	0	0	0		0		utf8mb4	utf8mb4_unicode_ci		0	0
	private Integer icon;//	int	11	0	-1	0	0	0	0	1	0	图标编码：1，2，3，4，5				0	0
	private Integer so;
	
	public Integer getSo() {
		return so;
	}
	public void setSo(Integer so) {
		this.so = so;
	}
	public long getId() {
		return id;
	}
	public SysCommand setId(long id) {
		this.id = id;
		return this;
	}
	public long getParentId() {
		return parentId;
	}
	public SysCommand setParentId(long parentId) {
		this.parentId = parentId;
		return this;
	}
	public String getName() {
		return name;
	}
	public SysCommand setName(String name) {
		this.name = name;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public SysCommand setUrl(String url) {
		this.url = url;
		return this;
	}
	public Integer getIcon() {
		return icon;
	}
	public SysCommand setIcon(Integer icon) {
		this.icon = icon;
		return this;
	}

}
