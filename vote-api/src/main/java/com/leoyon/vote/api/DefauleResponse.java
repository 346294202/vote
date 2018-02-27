package com.leoyon.vote.api;

import java.util.Collections;
import java.util.Map;

public class DefauleResponse extends AbstractResponse<Map<String, Object>> {

	public DefauleResponse(int code, String error) {
		super(code, error);
	}

	@Override
	public Map<String, Object> getData() {
		return Collections.emptyMap();
	}

	public static DefauleResponse wrap(int code, String error) {
		return new DefauleResponse(code, error);
	}
	
	public static DefauleResponse sucess() {
		return wrap(1, "");
	}
}
