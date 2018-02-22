package com.leoyon.doc;

import org.apache.commons.lang3.StringUtils;

public class SimpleMethodParam extends AbstractMethodParam {


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name+":");
		if(!StringUtils.isBlank(desc)) {
			sb.append(desc+",");
		}
		if(type != null) {
			sb.append(getTypeName() + ",");
		}
		sb.append((required ? "必须" : "可选")+",");
		if(maxLength != null) {
			sb.append("最大"+maxLength+",");
		}
		if(minLength != null) {
			sb.append("最小"+minLength + ",");
		}
		if(defaultValue != null) {
			sb.append("缺省"+defaultValue + ",");
		}
		
		return sb.toString();
	}

}
