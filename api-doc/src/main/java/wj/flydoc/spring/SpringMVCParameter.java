package wj.flydoc.spring;

import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import wj.flydoc.ApiMethodParameter;

public abstract class SpringMVCParameter implements ApiMethodParameter {

	@Override
	public String toString() {
		String name = getName();
		String type = getType();
		String desc = getDesc();
		String required = getRequired();
		String defVal = getDefVal();
		Vector<String> parts = new Vector<>();
		if(!StringUtils.isBlank(name))
			parts.add(name);
		if(!StringUtils.isBlank(type))
			parts.add(type);
		if(!StringUtils.isBlank(desc))
			parts.add(desc);
		if(!StringUtils.isBlank(required))
			parts.add(required);
		if(!StringUtils.isBlank(defVal))
			parts.add(defVal);
		return String.join(",", parts);
	}
}
