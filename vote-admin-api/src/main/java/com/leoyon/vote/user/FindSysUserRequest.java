package com.leoyon.vote.user;

public class FindSysUserRequest {
	
	private String q;
	private Integer page;
	private Integer psize;
	private Integer active;
	private Integer superuser;
	private Integer staff;
	
	public FindSysUserRequest(String q, Integer page, Integer psize, Integer active, Integer superuser, Integer staff) {
		super();
		this.q = q;
		this.page = page;
		this.psize = psize;
		this.active = active;
		this.superuser = superuser;
		this.staff = staff;
	}

	public Integer getSuperuser() {
		return superuser;
	}

	public void setSuperuser(Integer superuser) {
		this.superuser = superuser;
	}

	public Integer getStaff() {
		return staff;
	}

	public void setStaff(Integer staff) {
		this.staff = staff;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getQ() {
		return q;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}
	
	
}
