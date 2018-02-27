package wj.flydoc.spring;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiIgnore;

public class ObjectCtorWalker extends ObjectWalker {
	
	private Constructor<?> ctor;
	
	protected ObjectCtorWalker(Constructor<?> ctor) {
		this.ctor = ctor;
	}

	@Override
	protected Collection<Param> findParams() throws Exception {
		Vector<Param> ret = new Vector<>();
		for(Parameter p:ctor.getParameters()) {
			if(p.getAnnotation(ApiIgnore.class) != null)
				continue;
			
			Class<?> elementClazz = null;
			Type type = p.getParameterizedType();
			ParameterizedType pType = null;
			if(type instanceof ParameterizedType) {
				pType = (ParameterizedType) type;
				Type[] actTypes = pType.getActualTypeArguments();
				if(actTypes != null && actTypes.length > 0 && actTypes[0] instanceof Class<?>) {
					elementClazz = (Class<?>) actTypes[0];	
				}
			}
			
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
			ret.add(new Param(
					name, 
					p.getType(), 
					elementClazz, 
					desc, 
					required, 
					defVal));
		}
		return ret;
	}

}
