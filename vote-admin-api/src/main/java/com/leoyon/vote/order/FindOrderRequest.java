package com.leoyon.vote.order;

import java.util.Date;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindOrderRequest {

	private Integer type;
	private Date createTimeStart;
	private Date createTimeEnd;
	private Integer orderStatus;
	private String userRealName;
	private String userMobile;
	private Long areaId;
	private Integer houseBuilding;
	private Integer houseUnit;
	private Integer houseNumber;
	private Integer page;
	private Integer psize;
	
	@ApiParamCtor
	public FindOrderRequest(
			@ApiParam(desc="产品类型")
			Integer type, 
			@ApiParam(desc="下单时间起")
			Date createTimeStart, 
			@ApiParam(desc="下单时间止")
			Date createTimeEnd, 
			@ApiParam(desc="订单状态")
			Integer orderStatus,
			@ApiParam(desc="业主姓名")
			String userRealName, 
			@ApiParam(desc="业主手机")
			String userMobile, 
			@ApiParam(desc="小区id")
			Long areaId, 
			@ApiParam(desc="楼号")
			Integer houseBuilding, 
			@ApiParam(desc="单元号")
			Integer houseUnit,
			@ApiParam(desc="房号")
			Integer houseNumber, 
			Integer page, 
			Integer psize) {
		super();
		this.type = type;
		this.createTimeStart = createTimeStart;
		this.createTimeEnd = createTimeEnd;
		this.orderStatus = orderStatus;
		this.userRealName = userRealName;
		this.userMobile = userMobile;
		this.areaId = areaId;
		this.houseBuilding = houseBuilding;
		this.houseUnit = houseUnit;
		this.houseNumber = houseNumber;
		this.page = page;
		this.psize = psize;
	}
		
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}


	public String getUserRealName() {
		return userRealName;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public Long getAreaId() {
		return areaId;
	}


	public Integer getHouseBuilding() {
		return houseBuilding;
	}


	public Integer getHouseUnit() {
		return houseUnit;
	}


	public Integer getHouseNumber() {
		return houseNumber;
	}

	public Integer getType() {
		return type;
	}
	public Integer getPage() {
		return page;
	}
	public Integer getPsize() {
		return psize;
	}
	
}
