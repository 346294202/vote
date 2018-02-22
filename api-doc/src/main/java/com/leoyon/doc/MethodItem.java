package com.leoyon.doc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class MethodItem {
	private String name;
	private String path;
	private String method;
	private Collection<AbstractMethodParam> params;
	
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public String getMethod() {
		return method;
	}
	public Collection<AbstractMethodParam> getParams() {
		return params;
	}
	
	public MethodItem(String name, String path, String method, Collection<AbstractMethodParam> params) {
		super();
		this.name = name;
		this.path = path;
		this.method = method;
		this.params = params;
	}
	
	public static List<MethodItem> generate(Collection<Object> objs) {
		
		Vector<MethodItem> methods = new Vector<>();
		
		for(Object o:objs) {
			
			for(Method m:o.getClass().getMethods()) {
				if(m.getAnnotation(ApiParamIgnore.class) != null)
					continue;
				
				String name = null;
				String path = null;
				String requestMethod = null;
				Vector<AbstractMethodParam> params = new Vector<>();
				
				for(Annotation a:m.getAnnotations()) {					
					if(GetMapping.class.isInstance(a)) {			
						GetMapping mapping = (GetMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("GET");
					} else if(PostMapping.class.isInstance(a)) {
						PostMapping mapping = (PostMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("POST");
					} else if(DeleteMapping.class.isInstance(a)) {
						DeleteMapping mapping = (DeleteMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("DELETE");
					}
				}
				
				Parameter[] paramsIn = m.getParameters();
				collectParameters(paramsIn, params);
				if(name != null) {
					methods.add(new MethodItem(name, path, requestMethod, params));
				}
			}
		}
		
		return methods;
	}
	
	private static void collectCtorParameters(Parameter[] paramsIn, Vector<AbstractMethodParam> paramsOut) {
		for(Parameter p:paramsIn) {
			if(p.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			AbstractMethodParam param = new SimpleMethodParam();
			param.setName(p.getName());
			param.setType(p.getType());
			ApiParam a = p.getAnnotation(ApiParam.class);
			if(a != null) {
				if(!StringUtils.isBlank(a.name()))
					param.setName(a.name());
				if(a.max() > 0)
					param.setMaxLength(a.max());
				if(a.min() > 0)
					param.setMinLength(a.min());
				param.setDesc(a.desc());
				param.setRequired(a.required());
			}
			paramsOut.add(param);
		}
	}
	
	private static void collectParameters(Parameter[] paramsIn, Vector<AbstractMethodParam> paramsOut) {
		for(Parameter p:paramsIn) {
			if(p.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			{
				RequestBody a = p.getAnnotation(RequestBody.class);
				if(a != null) {
					Vector<AbstractMethodParam> jsonParams = new Vector<>();
					collect(p.getType(), jsonParams);
					JsonMethodParam param = new JsonMethodParam();
					param.setParams(jsonParams);
					paramsOut.add(param);
					continue;
				}
			}
			
			AbstractMethodParam param = null;
			{
				RequestParam rp = p.getAnnotation(RequestParam.class);
				if(rp != null) {
					param = new SimpleMethodParam();
					param.setRequired(rp.required());
					param.setName(StringUtils.isBlank(rp.value()) ? rp.name() : rp.value());
					
					param.setDefaultValue(rp.defaultValue());
				}
			}

			if(param == null){
				PathVariable rp = p.getAnnotation(PathVariable.class);
				if(rp != null) {
					param = new SimpleMethodParam();
					param.setRequired(rp.required());
					param.setName(StringUtils.isBlank(rp.value()) ? rp.name() : rp.value());
					param.setType(p.getType());
				}
			}
			
			{
				ApiParam rp = p.getAnnotation(ApiParam.class);
				if(rp != null) {
					if(param == null) {
						param = new SimpleMethodParam();
						param.setRequired(rp.required());
						if(rp.max() > 0)
							param.setMaxLength(rp.max());
						if(rp.min() > 0)
							param.setMinLength(rp.min());
					}
					param.setDesc(rp.desc());
				}
			}
			
			if(param == null) {
				collectCtor(p.getType(), paramsOut);
			} else {
				if(StringUtils.isBlank(param.getName())) {
					param.setName(p.getName());
				}
				param.setType(p.getType());
				paramsOut.add(param);
			}
			
		}
	}
	
	private static void collectCtor(Class<?> clazz, Vector<AbstractMethodParam> paramsOut) {
		for(Constructor<?> c:clazz.getConstructors()) {
			if(c.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			ApiParamCtor a = c.getAnnotation(ApiParamCtor.class);
			if(a != null) {
				collectCtorParameters(c.getParameters(), paramsOut);
				break;
			}
		}
	}
	
	private static void collect(Class<?> clazz, Vector<AbstractMethodParam> paramsOut) {
		Vector<String> fieldNames = new Vector<>();
		HashMap<String, SimpleMethodParam> params = new HashMap<>();
		
		for(Method m:clazz.getMethods()) {
			if(m.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			ApiParam a = m.getAnnotation(ApiParam.class);
			if(a != null) {
				String name = m.getName().substring(3);
				SimpleMethodParam param = new SimpleMethodParam();
				param.setName(StringUtils.uncapitalize(name));
				param.setDefaultValue(a.defaultValue());
				param.setType(getParamType(m));
				param.setDesc(a.desc());
				param.setRequired(a.required());
				if(a.max() > 0)
					param.setMaxLength(a.max());
				if(a.min() > 0)
					param.setMinLength(a.min());
				params.put(name, param);
				
			}
			if(m.getName().startsWith("get") || m.getName().startsWith("set")) {
				String name = m.getName().substring(3);
				if(fieldNames.contains(name) && !params.containsKey(name)) {
					
					SimpleMethodParam param = new SimpleMethodParam();
					param.setName(StringUtils.uncapitalize(name));
					param.setType(getParamType(m));
					params.put(name, param);
				} else
					fieldNames.add(name);
				
			}
		}
		
		paramsOut.addAll(params.values());
		
	}
	private static Class<?> getParamType(Method m) {
		if(m.getName().startsWith("get"))
			return m.getReturnType();
		if(m.getName().startsWith("set")) {
			return m.getParameters()[0].getType();
		}
		return null;
	}
}