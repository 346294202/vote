package com.leoyon.vote.address;

import wj.flydoc.ApiIgnore;

public class Address {

	private long id;
	private long userId;
	private Double longitude;
	private Double latitude;
	private String address;
	private String contacts;
	private String phones;
	private Boolean def;
	
	@ApiIgnore
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ApiIgnore
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	public Boolean getDef() {
		return def;
	}
	public void setDef(Boolean def) {
		this.def = def;
	}
	
}
