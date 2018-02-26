package wj.flydoc.spring;

import java.lang.reflect.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import wj.flydoc.ApiParam;

public class SpringMVCPathVariable extends SpringMVCValueParameter {
	
	private PathVariable annotation;
	
	public SpringMVCPathVariable(Parameter rawParam, PathVariable annotation, ApiParam apiParam) {
		super(rawParam, apiParam);
		this.annotation = annotation;
	}
	
	@Override
	protected String getSubName() {
		if(!StringUtils.isBlank(annotation.name()))
			return annotation.name();
		if(!StringUtils.isBlank(annotation.value()))
			return annotation.value();
		return null;
	}

	@Override
	protected boolean getSubRequired() {
		return annotation.required();
	}
	
}
