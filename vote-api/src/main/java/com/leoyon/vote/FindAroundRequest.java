package com.leoyon.vote;

import wj.flydoc.ApiParam;

public class FindAroundRequest extends FindPagedRequest {
	private Double lng;
	private Double lat;
	
	protected FindAroundRequest(Double lng, Double lat, Integer page, Integer psize) {
		super(page, psize);
		this.lng = lng;
		this.lat = lat;
	}
	
	@ApiParam(required=true)
	public Double getLng() {
		return lng;
	}

	@ApiParam(required=true)
	public Double getLat() {
		return lat;
	}
	
}