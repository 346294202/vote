package com.leoyon.vote.house;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindHouseRequest {
		
	private Long areaId;
	private Integer building;
	private Integer unit;
	private Integer number;
	private Integer page;
	private Integer psize;
	private Integer houseType;
	private Integer houseStatus;
	private String ownerName;
	private String ownerMobile;

	@ApiParamCtor
	public FindHouseRequest(
			@ApiParam(desc="小区id")
			Long areaId, 
			@ApiParam(desc="楼号")
			Integer building, 
			@ApiParam(desc="单元")
			Integer unit, 
			@ApiParam(desc="房号")
			Integer number, 
			Integer page, 
			Integer psize,
			@ApiParam(desc="房屋类型")
			Integer houseType, 
			@ApiParam(desc="房屋状态")
			Integer houseStatus, 
			@ApiParam(desc="户主姓名")
			String ownerName, 
			@ApiParam(desc="户主电话")
			String ownerMobile) {
		super();
		this.areaId = areaId;
		this.building = building;
		this.unit = unit;
		this.number = number;
		this.page = page;
		this.psize = psize;
		this.houseType = houseType;
		this.houseStatus = houseStatus;
		this.ownerName = ownerName;
		this.ownerMobile = ownerMobile;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Integer getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
