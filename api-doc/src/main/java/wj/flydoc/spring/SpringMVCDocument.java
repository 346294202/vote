package wj.flydoc.spring;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wj.flydoc.ApiDocument;
import wj.flydoc.ApiGroup;
import wj.flydoc.ApiMethod;
import wj.flydoc.ApiIgnore;

public class SpringMVCDocument implements ApiDocument {
	
	private List<Object> controllers;
	
	public SpringMVCDocument(List<Object> controllers) {
		super();
		this.controllers = controllers;
	}

	@Override
	public Iterable<ApiGroup> getGroups() {
		Vector<ApiGroup> ret = new Vector<>();
		
		for(Object c:controllers) {
			Class<?> clazz = c.getClass();
			if(clazz.getAnnotation(ApiIgnore.class) != null)
				continue;
			
			String name = null;
			String rootPath = "";
			if(clazz.getAnnotation(RequestMapping.class) != null) {
				RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
				if(requestMapping.path().length > 0)
					rootPath = requestMapping.path()[0];//TODO 目前只处理第一个路径
				if(!StringUtils.isBlank(requestMapping.name()))
						name = requestMapping.name();
			}
			
			Vector<ApiMethod> methods = new Vector<>();
			
			for(Method m:clazz.getMethods()) {
				if(m.getAnnotation(ApiIgnore.class) != null)
					continue;
				
				ApiMethod am = findMethod(m, rootPath);
				
				if(am == null)
					continue;
				methods.add(am);				
			}
			
			if(clazz.getAnnotation(RestController.class) != null) {
				String value = clazz.getAnnotation(RestController.class).value();
				if(!StringUtils.isBlank(value))
					name = value;
			}
			
			if(clazz.getAnnotation(Controller.class) != null) {
				String value = clazz.getAnnotation(Controller.class).value();
				if(!StringUtils.isBlank(value))
					name = value;
			}
			
			if(StringUtils.isBlank(name))
				name = clazz.getSimpleName();
			
			if(!methods.isEmpty()) {
				ret.add(new SpringMVCGroup(name, methods));
			}
		}
		return ret;
	}

	private ApiMethod findMethod(Method m, String rootPath) {
		if(m.getAnnotation(RequestMapping.class) != null)
			return new SpringMVCMethodRequestMapping(m, rootPath, m.getAnnotation(RequestMapping.class));
		if(m.getAnnotation(GetMapping.class) != null)
			return new SpringMVCMethodGetMapping(m, rootPath, m.getAnnotation(GetMapping.class));
		if(m.getAnnotation(PostMapping.class) != null)
			return new SpringMVCMethodPostMapping(m, rootPath, m.getAnnotation(PostMapping.class));
		if(m.getAnnotation(PutMapping.class) != null)
			return new SpringMVCMethodPutMapping(m, rootPath, m.getAnnotation(PutMapping.class));
		if(m.getAnnotation(DeleteMapping.class) != null)
			return new SpringMVCMethodDeleteMapping(m, rootPath, m.getAnnotation(DeleteMapping.class));
		if(m.getAnnotation(PatchMapping.class) != null)
			return new SpringMVCMethodPatchMapping(m, rootPath, m.getAnnotation(DeleteMapping.class));
		return null;
	}

	public Collection<ApiMethod> methods() {
		Vector<ApiMethod> ret = new Vector<>();
		getGroups().forEach(i -> {
			i.getMethods().forEach(m -> {
				ret.add(m);
			});
		});
		return ret;
	}

}
