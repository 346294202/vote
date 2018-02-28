package wj.flydoc.spring;

import java.lang.reflect.Parameter;

import org.springframework.web.bind.annotation.RequestBody;

import wj.flydoc.ApiParam;

public class SpringMVCRequestBody extends SpringMVCParameter {
	
	private Parameter rawParam;
	private RequestBody annotation;
	private ApiParam apiParam;
	
	public SpringMVCRequestBody(Parameter rawParam, RequestBody annotation, ApiParam apiParam) {
		super();
		this.rawParam = rawParam;
		this.annotation = annotation;
		this.apiParam = apiParam;
	}

	@Override
	public String getDefVal() {
		return null;
	}

	@Override
	public String getRequired() {
		return null;
	}

	@Override
	public String getType() {
		return Formats.formatParameter(rawParam);
	}

	@Override
	public String getDesc() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
	
}
