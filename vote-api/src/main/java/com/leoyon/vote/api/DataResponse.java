package com.leoyon.vote.api;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

public class DataResponse<T> extends AbstractResponse<T> {

	T data;

	@ApiParamCtor
	public DataResponse(
			@ApiParam(name="code", required=true)
			int code,
			
			@ApiParam(name="error", required=true)
			String error, 
			
			@ApiParam(name="data", required=true)
			T data) {
		super(code, error);
		this.data = data;
	}
	
	public static <T> DataResponse<T> sucess(T data) {
		return new DataResponse<T>(1, "", data);
	}
	
	@Override
	public T getData() {
		return data;
	}

}
