package com.leoyon.vote.user;

import java.util.Date;

import com.leoyon.doc.ApiParamCtor;

public class FindUserHouseRequest {
	
	private String realName;
	private String mobile;
	private Date dateCreateStart;
	private Date dateCreateEnd;
	private Integer ownerStatus;
	private Integer page;
	private Integer psize;
	
	@ApiParamCtor
	public FindUserHouseRequest(String realName, String mobile, Date dateCreateStart, Date dateCreateEnd,
			Integer ownerStatus, Integer page, Integer psize) {
		super();
		this.realName = realName;
		this.mobile = mobile;
		this.dateCreateStart = dateCreateStart;
		this.dateCreateEnd = dateCreateEnd;
		this.ownerStatus = ownerStatus;
		this.page = page;
		this.psize = psize;
	}
	public String getRealName() {
		return realName;
	}
	public String getMobile() {
		return mobile;
	}
	public Date getDateCreateStart() {
		return dateCreateStart;
	}
	public Date getDateCreateEnd() {
		return dateCreateEnd;
	}
	public Integer getOwnerStatus() {
		return ownerStatus;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getPsize() {
		return psize;
	}
	
}
