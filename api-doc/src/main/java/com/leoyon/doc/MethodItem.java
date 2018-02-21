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
	private Collection<MethodParam> params;
	
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public String getMethod() {
		return method;
	}
	public Collection<MethodParam> getParams() {
		return params;
	}
	
	public MethodItem(String name, String path, String method, Collection<MethodParam> params) {
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
				
				String name = null;
				String path = null;
				String requestMethod = null;
				Vector<MethodParam> params = new Vector<>();
				
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
	
	private static void collectCtorParameters(Parameter[] paramsIn, Vector<MethodParam> paramsOut) {
		for(Parameter p:paramsIn) {
			MethodParam param = new MethodParam();
			param.setName(p.getName());
			param.setType(p.getType());
			ApiParam a = p.getAnnotation(ApiParam.class);
			if(a != null) {
				if(!StringUtils.isBlank(a.name()))
					param.setName(a.name());
				param.setDesc(a.desc());
				param.setRequired(a.required());
			}
			paramsOut.add(param);
		}
	}
	
	private static void collectParameters(Parameter[] paramsIn, Vector<MethodParam> paramsOut) {
		for(Parameter p:paramsIn) {
			for(Annotation a:p.getAnnotations()) {
				if(RequestParam.class.isInstance(a)) {
					RequestParam rp = (RequestParam) a;
					MethodParam param = new MethodParam();
					param.setRequired(rp.required());
					param.setName(rp.value());
					if(StringUtils.isBlank(param.getName()))
						param.setName(p.getName());
					param.setDesc(rp.name());
					param.setType(p.getType());
					param.setDefaultValue(rp.defaultValue());
					paramsOut.add(param);
				} else if(RequestBody.class.isInstance(a) 
						&& !p.getType().isPrimitive()
						&& !String.class.equals(p.getType())) {
					collect(p.getType(), paramsOut);
				} else if(ApiParam.class.isInstance(a)) {
					ApiParam rp = (ApiParam) a;
					MethodParam param = new MethodParam();
					param.setRequired(rp.required());
					param.setName(p.getName());
					param.setDesc(rp.desc());
					param.setType(p.getType());
					paramsOut.add(param);
				} else if(PathVariable.class.isInstance(a)) {
					PathVariable rp = (PathVariable) a;
					MethodParam param = new MethodParam();
					param.setRequired(rp.required());
					param.setName(rp.value());
					if(StringUtils.isBlank(param.getName()))
						param.setName(p.getName());
					param.setDesc(rp.name());
					param.setType(p.getType());
					paramsOut.add(param);
				}
				
			}
			
			collectCtor(p.getType(), paramsOut);
				
		}
	}
	
	private static void collectCtor(Class<?> clazz, Vector<MethodParam> paramsOut) {
		for(Constructor<?> c:clazz.getConstructors()) {
			ApiParamCtor a = c.getAnnotation(ApiParamCtor.class);
			if(a != null) {
				collectCtorParameters(c.getParameters(), paramsOut);
				break;
			}
		}
	}
	
	private static void collect(Class<?> clazz, Vector<MethodParam> paramsOut) {
		Vector<String> fieldNames = new Vector<>();
		HashMap<String, MethodParam> params = new HashMap<>();
		
		for(Method m:clazz.getMethods()) {
			ApiParam a = m.getAnnotation(ApiParam.class);
			if(a != null) {
				String name = m.getName().substring(3);
				MethodParam param = new MethodParam();
				param.setName(StringUtils.uncapitalize(name));
				param.setDefaultValue(a.defaultValue());
				param.setType(getParamType(m));
				param.setDesc(a.desc());
				param.setRequired(a.required());
				params.put(name, param);
				
			}
			if(m.getName().startsWith("get") || m.getName().startsWith("set")) {
				String name = m.getName().substring(3);
				if(fieldNames.contains(name) && !params.containsKey(name)) {
					
					MethodParam param = new MethodParam();
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