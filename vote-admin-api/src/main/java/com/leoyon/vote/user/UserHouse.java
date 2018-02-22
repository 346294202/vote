package com.leoyon.vote.user;

import java.util.Date;

import com.leoyon.doc.ApiParam;
import com.leoyon.doc.ApiParamIgnore;

public class UserHouse {

	private Long userId;
	private Long houseId;
	private Integer ownerStatus;// ` tinyint(4) DEFAULT '1' COMMENT '1 待认证，2 认证通过，3 认证驳回',
	private Integer ownerType;// ` tinyint(4) DEFAULT '1' COMMENT '1 户主，2 租客，3 家属',
	private String ownerReason;// ` varchar(256) DEFAULT NULL COMMENT '驳回原因',
	private Long ownerUpdateUid;// ` bigint(20) DEFAULT NULL,
	private Date ownerUpdateTime;// ` timestamp NULL DEFAULT NULL,
	
	@ApiParam(desc="业主id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@ApiParam(desc="房屋id")
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	@ApiParam(desc="认证状态，1 待认证，2 认证通过，3 认证驳回")
	public Integer getOwnerStatus() {
		return ownerStatus;
	}
	public void setOwnerStatus(Integer ownerStatus) {
		this.ownerStatus = ownerStatus;
	}
	public Integer getOwnerType() {
		return ownerType;
	}
	@ApiParam(desc="业主类型，1 户主，2 租客，3 家属")
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}
	
	@ApiParam(desc="驳回原因")
	public String getOwnerReason() {
		return ownerReason;
	}
	public void setOwnerReason(String ownerReason) {
		this.ownerReason = ownerReason;
	}
	
	@ApiParamIgnore
	public Long getOwnerUpdateUid() {
		return ownerUpdateUid;
	}
	public void setOwnerUpdateUid(Long ownerUpdateUid) {
		this.ownerUpdateUid = ownerUpdateUid;
	}
	@ApiParamIgnore
	public Date getOwnerUpdateTime() {
		return ownerUpdateTime;
	}
	public void setOwnerUpdateTime(Date ownerUpdateTime) {
		this.ownerUpdateTime = ownerUpdateTime;
	}



}
