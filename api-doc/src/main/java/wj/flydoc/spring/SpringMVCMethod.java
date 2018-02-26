package wj.flydoc.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
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
			
			collectApiMethodParameter(p, ret);
		}
		return ret;
	}

	private void collectApiMethodParameter(Parameter rawParam, List<ApiMethodParameter> ret) {
		if(rawParam.getAnnotation(RequestParam.class) != null)
			ret.add(new SpringMVCRequestParam(rawParam, rawParam.getAnnotation(RequestParam.class), rawParam.getAnnotation(ApiParam.class)));
		else if(rawParam.getAnnotation(RequestBody.class) != null)
			ret.add(new SpringMVCRequestBody(rawParam, rawParam.getAnnotation(RequestBody.class), rawParam.getAnnotation(ApiParam.class)));
		else if(rawParam.getAnnotation(PathVariable.class) != null)
			ret.add(new SpringMVCPathVariable(rawParam, rawParam.getAnnotation(PathVariable.class), rawParam.getAnnotation(ApiParam.class)));
		else if(rawParam.getAnnotation(ApiParam.class) != null)
			ret.add(new SpringMVCApiParam(rawParam, rawParam.getAnnotation(ApiParam.class)));
		else {
			for(Constructor<?> c:rawParam.getType().getConstructors()) {
				if(c.getAnnotation(ApiParamCtor.class) != null) {
					collectApiMethodParameter(c, ret);
				}
			}
		}
	}

	private void collectApiMethodParameter(Constructor<?> ctor, List<ApiMethodParameter> ret) {
		for(Parameter p:ctor.getParameters()) {
			if(p.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			
			//TODO 将来处理list类型
//			Class<?> elementClazz = null;
//			Type type = p.getParameterizedType();
//			ParameterizedType pType = null;
//			if(type instanceof ParameterizedType) {
//				pType = (ParameterizedType) type;
//				Type[] actTypes = pType.getActualTypeArguments();
//				if(actTypes != null && actTypes.length > 0 && actTypes[0] instanceof Class<?>) {
//					elementClazz = (Class<?>) actTypes[0];	
//				}
//			}
			
			String name = p.getName();
			String desc = null;
			Boolean required = null;
			String defVal = null;
			ApiParam a = p.getAnnotation(ApiParam.class);
			if(a != null) {
				if(!StringUtils.isBlank(a.name()))
					name = a.name();
				if(!StringUtils.isBlank(a.desc()))
					desc = a.desc();
				if(!StringUtils.isBlank(a.defaultValue()))
					defVal = a.defaultValue();
				required = a.required();
			}
			
			ret.add(new SimpleApiMethodParameter(
					name,
					Formats.format(p.getType()),
					desc,
					required != null ? Formats.formatRequired(required) : null,
					defVal != null ? Formats.formatDefVal(defVal) : null
					));

		}
	}

}
