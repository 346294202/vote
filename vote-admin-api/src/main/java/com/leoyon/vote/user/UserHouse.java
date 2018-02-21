package com.leoyon.vote.user;

import java.util.Date;

public class UserHouse {

	private Long userId;
	private Long houseId;
	private Integer ownerStatus;// ` tinyint(4) DEFAULT '1' COMMENT '1 待认证，2 认证通过，3 认证驳回',
	private Integer ownerType;// ` tinyint(4) DEFAULT '1' COMMENT '1 户主，2 租客，3 家属',
	private String ownerReason;// ` varchar(256) DEFAULT NULL COMMENT '驳回原因',
	private Long ownerUpdateUid;// ` bigint(20) DEFAULT NULL,
	private Date ownerUpdateTime;// ` timestamp NULL DEFAULT NULL,
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
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
	public Long getOwnerUpdateUid() {
		return ownerUpdateUid;
	}
	public void setOwnerUpdateUid(Long ownerUpdateUid) {
		this.ownerUpdateUid = ownerUpdateUid;
	}
	public Date getOwnerUpdateTime() {
		return ownerUpdateTime;
	}
	public void setOwnerUpdateTime(Date ownerUpdateTime) {
		this.ownerUpdateTime = ownerUpdateTime;
	}



}
