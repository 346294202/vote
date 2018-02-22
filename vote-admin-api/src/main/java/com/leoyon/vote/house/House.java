package com.leoyon.vote.house;

import com.leoyon.doc.ApiParam;
import com.leoyon.vote.AbstractResource;

public class House extends AbstractResource<House> {

	private Long areaId;
	private Integer building;
	private Integer unit;
	private Integer number;
	private Integer houseType;
	private Integer houseStatus;
	private String remark;
	private Float constructionArea;
	private Float netArea;
	private String ownerName;
	private String ownerPhone;
	
	@ApiParam(desc="户主")
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@ApiParam(desc="户主电话")
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	@ApiParam(desc="建筑面积")
	public Float getConstructionArea() {
		return constructionArea;
	}
	public void setConstructionArea(Float constructionArea) {
		this.constructionArea = constructionArea;
	}
	@ApiParam(desc="实用面积")
	public Float getNetArea() {
		return netArea;
	}
	public void setNetArea(Float netArea) {
		this.netArea = netArea;
	}
	
	@ApiParam(desc="小区id")
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	@ApiParam(desc="楼栋号")
	public Integer getBuilding() {
		return building;
	}
	public void setBuilding(Integer building) {
		this.building = building;
	}
	@ApiParam(desc="单元")
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	@ApiParam(desc="房号")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@ApiParam(desc="房屋类型")
	public Integer getHouseType() {
		return houseType;
	}
	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}
	@ApiParam(desc="房屋状态")
	public Integer getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(Integer houseStatus) {
		this.houseStatus = houseStatus;
	}
	@ApiParam(desc="备注")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
