package com.leoyon.vote.house;

import com.leoyon.doc.ApiParamCtor;

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
	public FindHouseRequest(Long areaId, Integer building, Integer unit, Integer number, Integer page, Integer psize,
			Integer houseType, Integer houseStatus, String ownerName, String ownerMobile) {
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
