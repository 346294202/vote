package wj.flydoc.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Vector;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import wj.flydoc.ApiMethod;
import wj.flydoc.ApiMethodParameter;
import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;
import wj.flydoc.ApiParamIgnore;

public abstract class SpringMVCMethod implements ApiMethod {
	
	private Method rawMethod;
	private String rootPath;

	protected SpringMVCMethod(Method rawMethod, String rootPath) {
		this.rawMethod = rawMethod;
		this.rootPath = rootPath;
	}

	@Override
	public String getPath() {
		return rootPath + getMethodPath();
	}

	protected String getMethodPath() {
		return "";
	}

	@Override
	public Iterable<ApiMethodParameter> getParameters() {
		Vector<ApiMethodParameter> ret = new Vector<>();
		
		for(Parameter p:rawMethod.getParameters()) {
			if(p.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			
			ApiMethodParameter amp = findApiMethodParameter(p);
			
			if(amp != null) {
				ret.add(amp);
			}
		}
		return ret;
	}

	private ApiMethodParameter findApiMethodParameter(Parameter rawParam) {
		if(rawParam.getAnnotation(RequestParam.class) != null)
			return new SpringMVCRequestParam(rawParam, rawParam.getAnnotation(RequestParam.class));
		if(rawParam.getAnnotation(RequestBody.class) != null)
			return new SpringMVCRequestBody(rawParam, rawParam.getAnnotation(RequestBody.class));
		if(rawParam.getAnnotation(PathVariable.class) != null)
			return new SpringMVCPathVariable(rawParam, rawParam.getAnnotation(PathVariable.class));
		if(rawParam.getAnnotation(ApiParam.class) != null)
			return new SpringMVCPathApiParam(rawParam, rawParam.getAnnotation(ApiParam.class));
		for(Constructor<?> c:rawParam.getClass().getConstructors()) {
			if(c.getAnnotation(ApiParamCtor.class) != null) {
				return new SpringMVCApiParamCtor(rawParam, c);
			}
		}
		return null;
	}

}
