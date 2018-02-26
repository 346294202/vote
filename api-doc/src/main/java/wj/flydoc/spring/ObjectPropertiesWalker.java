package wj.flydoc.spring;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamIgnore;

public class ObjectPropertiesWalker extends ObjectWalker {
	
	protected Class<?> type;
	
	public ObjectPropertiesWalker(Class<?> type) {
		this.type = type;
	}
	
	@Override
	protected Collection<Param> findParams() throws Exception {
		Vector<Param> ret = new Vector<>();
		BeanInfo info = Introspector.getBeanInfo(type);
		for(PropertyDescriptor p:info.getPropertyDescriptors()) {
			if(p.getReadMethod() == null
					|| p.getWriteMethod() == null)
				continue;
			
			Class<?> clazz = p.getPropertyType();
			if(clazz.getAnnotation(ApiParamIgnore.class) != null)
				continue;
			
			Class<?> elementClazz = null;
			Type type = p.getReadMethod().getGenericReturnType();
			ParameterizedType pType = null;
			if(type instanceof ParameterizedType) {
				pType = (ParameterizedType) type;
				Type[] actTypes = pType.getActualTypeArguments();
				if(actTypes != null && actTypes.length > 0 && actTypes[0] instanceof Class<?>) {
					elementClazz = (Class<?>) actTypes[0];	
				}
			}
			
			ApiParam apiParam = clazz.getAnnotation(ApiParam.class);
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
}
