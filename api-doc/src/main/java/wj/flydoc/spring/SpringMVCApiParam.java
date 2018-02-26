package wj.flydoc.spring;

import java.lang.reflect.Parameter;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiParam;

public class SpringMVCApiParam extends SpringMVCParameter {
	
	private Parameter rawParam;
	private ApiParam annotation;

	public SpringMVCApiParam(Parameter rawParam, ApiParam annotation) {
		super();
		this.rawParam = rawParam;
		this.annotation = annotation;
	}

	@Override
	public String getDefVal() {
		return StringUtils.isBlank(annotation.defaultValue()) ? 
				null : Formats.formatDefVal(annotation.defaultValue());
	}

	@Override
	public String getRequired() {
		return Formats.formatRequired(annotation.required());
	}

	@Override
	public String getType() {
		return Formats.format(rawParam.getType());
	}

	@Override
	public String getDesc() {
		return StringUtils.isBlank(annotation.desc()) ? 
				null : annotation.desc();
	}

	@Override
	public String getName() {
		return StringUtils.isBlank(annotation.name()) ? 
				rawParam.getName() : annotation.desc();
	}

}
