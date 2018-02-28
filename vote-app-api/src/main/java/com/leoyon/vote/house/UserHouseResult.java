package com.leoyon.vote.house;

import wj.flydoc.ApiIgnore;

public class UserHouseResult {

	private long userId;
	private long houseId;
	private String houseTitle;
	private int ownerStatus;//` tinyint(4) DEFAULT '1' COMMENT '1 待认证，2 认证通过，3 认证驳回',
	private int ownerType;//` tinyint(4) DEFAULT '1' COMMENT '1 户主，2 租客，3 家属',
	private String ownerReason;//` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '驳回原因',
	
	public long getHouseId() {
		return houseId;
	}
	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	public String getHouseTitle() {
		return houseTitle;
	}
	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	public int getOwnerStatus() {
		return ownerStatus;
	}
	public void setOwnerStatus(int ownerStatus) {
		this.ownerStatus = ownerStatus;
	}
	public int getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}
	public String getOwnerReason() {
		return ownerReason;
	}
	public void setOwnerReason(String ownerReason) {
		this.ownerReason = ownerReason;
	}
	
	@ApiIgnore
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
