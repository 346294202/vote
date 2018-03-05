package com.leoyon.vote.business;

import com.leoyon.vote.FindAroundRequest;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class FindBizRequest extends FindAroundRequest {

	private Integer type;
	
	@ApiParamCtor
	public FindBizRequest(
			@ApiParam(desc="POI类型，1 商业，2 政务", required=true)
			Integer type, 
			@ApiParam(desc="经度，百度坐标", required=true)
			Double lng, 
			@ApiParam(desc="纬度，百度坐标", required=true)
			Double lat, 
			Integer page, Integer psize) {
		super(lng, lat, page, psize);
		this.type = type;
	}

	@ApiParam(required=true)
	public Integer getType() {
		return type;
	}

}
