package com.leoyon.vote.user;

import java.util.Date;

public class FindUserHouseResponse extends User {
	
	private Long userId;
	private String mobile;
	private Date dateCreate;
	private String realName;
	private String email;
	private Integer gender;
	private Date birthday;
	
	private Long houseId;
	private String areaName;
	private String houseText;
	
	private Integer ownerStatus;// ` tinyint(4) DEFAULT '1' COMMENT '1 待认证，2 认证通过，3 认证驳回',
	private Integer ownerType;// ` tinyint(4) DEFAULT '1' COMMENT '1 户主，2 租客，3 家属',
	private String ownerReason;// ` varchar(256) DEFAULT NULL COMMENT '驳回原因',
	private String ownerUpdateUname;// ` bigint(20) DEFAULT NULL,
	private Date ownerUpdateTime;// ` timestamp NULL DEFAULT NULL,
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getHouseText() {
		return houseText;
	}
	public void setHouseText(String houseText) {
		this.houseText = houseText;
	}
	public Integer getOwnerStatus() {
		return ownerStatus;
	}
	public void setOwnerStatus(Integer ownerStatus) {
		this.ownerStatus = ownerStatus;
	}
	public Integer getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}
	public String getOwnerReason() {
		return ownerReason;
	}
	public void setOwnerReason(String ownerReason) {
		this.ownerReason = ownerReason;
	}
	public String getOwnerUpdateUname() {
		return ownerUpdateUname;
	}
	public void setOwnerUpdateUname(String ownerUpdateUname) {
		this.ownerUpdateUname = ownerUpdateUname;
	}
	public Date getOwnerUpdateTime() {
		return ownerUpdateTime;
	}
	public void setOwnerUpdateTime(Date ownerUpdateTime) {
		this.ownerUpdateTime = ownerUpdateTime;
	}
	
	
}
