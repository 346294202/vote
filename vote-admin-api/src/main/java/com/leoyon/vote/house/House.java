package com.leoyon.vote.house;

public class House {

	private Long id;
	private Long areaId;
	private Integer building;
	private Integer unit;
	private Integer number;
	private Integer houseType;
	private Integer houseStatus;
	private String remark;
	private Float constructionArea;
	private Float netArea;
	
	public Float getConstructionArea() {
		return constructionArea;
	}
	public void setConstructionArea(Float constructionArea) {
		this.constructionArea = constructionArea;
	}
	public Float getNetArea() {
		return netArea;
	}
	public void setNetArea(Float netArea) {
		this.netArea = netArea;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
