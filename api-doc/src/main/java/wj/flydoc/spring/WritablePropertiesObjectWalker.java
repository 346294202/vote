package wj.flydoc.spring;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiIgnore;
import wj.flydoc.ApiParam;

public class WritablePropertiesObjectWalker extends ObjectWalker {
	
	protected Class<?> type;
	
	public WritablePropertiesObjectWalker(Class<?> type) {
		this.type = type;
	}
	
	@Override
	protected Collection<Param> findParams() throws Exception {
		Vector<Param> ret = new Vector<>();
		BeanInfo info = Introspector.getBeanInfo(type);
		for(PropertyDescriptor p:info.getPropertyDescriptors()) {
			if(p.getWriteMethod() == null)
				continue;
			
			if(p.getReadMethod().getAnnotation(ApiIgnore.class) != null
					|| (p.getWriteMethod() != null 
						&& p.getWriteMethod().getAnnotation(ApiIgnore.class) != null))
				continue;
			
			Class<?> clazz = p.getPropertyType();
			
			if(Class.class.equals(clazz))
				continue;
			
			Type type = p.getWriteMethod().getParameters()[0].getParameterizedType();
			Class<?> elementClazz = tryGetElementClass(type);
			
			ApiParam apiParam = clazz.getAnnotation(ApiParam.class);
			if(apiParam == null) {
				apiParam = p.getReadMethod().getAnnotation(ApiParam.class);
			}
			
			if(apiParam == null && p.getWriteMethod() != null) {
				apiParam = p.getWriteMethod().getAnnotation(ApiParam.class);
			}
			
			String name = p.getName();
			String desc = apiParam != null ? (StringUtils.isBlank(apiParam.desc()) ? null : apiParam.desc()) : null;
			Boolean required = (apiParam != null ? apiParam.required() : null);
			String defVal = apiParam != null ? (StringUtils.isBlank(apiParam.defaultValue()) ? null : apiParam.defaultValue()) : null;
			
			ret.add(new Param(name,
					clazz,
					elementClazz,
					desc,
					required,
					defVal));
		}
		return ret;
	}

	public Class<?> tryGetElementClass(Type type) {
		Class<?> elementClazz = null;			
		ParameterizedType pType = null;
		if(type instanceof ParameterizedType) {
			pType = (ParameterizedType) type;
			Type[] actTypes = pType.getActualTypeArguments();
			if(actTypes != null && actTypes.length > 0 && actTypes[0] instanceof Class<?>) {
				elementClazz = (Class<?>) actTypes[0];	
			}
		}
		return elementClazz;
	}
}
